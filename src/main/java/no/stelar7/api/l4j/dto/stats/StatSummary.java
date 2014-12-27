package no.stelar7.api.l4j.dto.stats;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StatSummary
{
    AggregatedStats aggregatedStats;
    int             losses;
    Long            modifyDate;
    String          playerStatSummaryType;
    int             wins;
}