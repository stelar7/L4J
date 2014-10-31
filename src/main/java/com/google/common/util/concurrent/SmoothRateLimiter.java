/*
 * Copyright (C) 2012 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.util.concurrent;

import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.TimeUnit;

abstract class SmoothRateLimiter extends RateLimiter
{
    static final class SmoothBursty extends SmoothRateLimiter
    {
        final double maxBurstSeconds;

        SmoothBursty(final SleepingStopwatch stopwatch, final double maxBurstSeconds)
        {
            super(stopwatch);
            this.maxBurstSeconds = maxBurstSeconds;
        }

        @Override
        void doSetRate(final double permitsPerSecond, final double stableIntervalMicros)
        {
            final double oldMaxPermits = this.maxPermits;
            this.maxPermits = this.maxBurstSeconds * permitsPerSecond;
            if (oldMaxPermits == Double.POSITIVE_INFINITY)
            {
                this.storedPermits = this.maxPermits;
            } else
            {
                this.storedPermits = (oldMaxPermits == 0.0) ? 0.0 // initial state
                        : (this.storedPermits * this.maxPermits) / oldMaxPermits;
            }
        }

        @Override
        long storedPermitsToWaitTime(final double storedPermits, final double permitsToTake)
        {
            return 0L;
        }
    }

    static final class SmoothWarmingUp extends SmoothRateLimiter
    {
        private final long warmupPeriodMicros;
        private double     slope;
        private double     halfPermits;

        SmoothWarmingUp(final SleepingStopwatch stopwatch, final long warmupPeriod, final TimeUnit timeUnit)
        {
            super(stopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(warmupPeriod);
        }

        @Override
        void doSetRate(final double permitsPerSecond, final double stableIntervalMicros)
        {
            final double oldMaxPermits = this.maxPermits;
            this.maxPermits = this.warmupPeriodMicros / stableIntervalMicros;
            this.halfPermits = this.maxPermits / 2.0;
            final double coldIntervalMicros = stableIntervalMicros * 3.0;
            this.slope = (coldIntervalMicros - stableIntervalMicros) / this.halfPermits;
            if (oldMaxPermits == Double.POSITIVE_INFINITY)
            {
                this.storedPermits = 0.0;
            } else
            {
                this.storedPermits = (oldMaxPermits == 0.0) ? this.maxPermits // initial state is cold
                        : (this.storedPermits * this.maxPermits) / oldMaxPermits;
            }
        }

        private double permitsToTime(final double permits)
        {
            return this.stableIntervalMicros + (permits * this.slope);
        }

        @Override
        long storedPermitsToWaitTime(final double storedPermits, double permitsToTake)
        {
            final double availablePermitsAboveHalf = storedPermits - this.halfPermits;
            long micros = 0;
            if (availablePermitsAboveHalf > 0.0)
            {
                final double permitsAboveHalfToTake = Math.min(availablePermitsAboveHalf, permitsToTake);
                micros = (long) ((permitsAboveHalfToTake * (this.permitsToTime(availablePermitsAboveHalf) + this.permitsToTime(availablePermitsAboveHalf - permitsAboveHalfToTake))) / 2.0);
                permitsToTake -= permitsAboveHalfToTake;
            }
            micros += (this.stableIntervalMicros * permitsToTake);
            return micros;
        }
    }

    double       storedPermits;
    double       maxPermits;
    double       stableIntervalMicros;
    private long nextFreeTicketMicros = 0L;

    private SmoothRateLimiter(final SleepingStopwatch stopwatch)
    {
        super(stopwatch);
    }

    @Override
    final double doGetRate()
    {
        return SECONDS.toMicros(1L) / this.stableIntervalMicros;
    }

    abstract void doSetRate(final double permitsPerSecond, final double stableIntervalMicros);

    @Override
    final void doSetRate(final double permitsPerSecond, final long nowMicros)
    {
        this.resync(nowMicros);
        final double stableIntervalMicros = SECONDS.toMicros(1L) / permitsPerSecond;
        this.stableIntervalMicros = stableIntervalMicros;
        this.doSetRate(permitsPerSecond, stableIntervalMicros);
    }

    @Override
    final long queryEarliestAvailable(final long nowMicros)
    {
        return this.nextFreeTicketMicros;
    }

    @Override
    final long reserveEarliestAvailable(final int requiredPermits, final long nowMicros)
    {
        this.resync(nowMicros);
        final long returnValue = this.nextFreeTicketMicros;
        final double storedPermitsToSpend = Math.min(requiredPermits, this.storedPermits);
        final double freshPermits = requiredPermits - storedPermitsToSpend;

        final long waitMicros = this.storedPermitsToWaitTime(this.storedPermits, storedPermitsToSpend) + (long) (freshPermits * this.stableIntervalMicros);

        this.nextFreeTicketMicros = this.nextFreeTicketMicros + waitMicros;
        this.storedPermits -= storedPermitsToSpend;
        return returnValue;
    }

    private void resync(final long nowMicros)
    {
        if (nowMicros > this.nextFreeTicketMicros)
        {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + ((nowMicros - this.nextFreeTicketMicros) / this.stableIntervalMicros));
            this.nextFreeTicketMicros = nowMicros;
        }
    }

    abstract long storedPermitsToWaitTime(final double storedPermits, final double permitsToTake);
}