package test;

import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.Server;
import my.api.stelar7.usewith.lol.dto.match.Frame;

import org.junit.Test;

public class SummonerTest
{

    L4J lib = new L4J("THIS IS MY API KEY");

    @Test
    public void test1()
    {
        L4J.setRegion(Server.NA);
        for (Frame f : lib.getMatch(1500895503).getTimeline().getFrames())
        {
            for (String p : f.getParticipantFrames().keySet())
            {
                if (p.equals("10"))
                {
                    System.out.println(f.getTimestamp() / 1000 + ": " + f.getParticipantFrames().get(p).getPosition());
                }
            }
        }
    }
}
