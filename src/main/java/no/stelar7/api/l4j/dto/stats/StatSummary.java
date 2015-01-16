package no.stelar7.api.l4j.dto.stats;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StatSummary implements Serializable
{
    AggregatedStats aggregatedStats;
    int             losses;
    Long            modifyDate;
    String          playerStatSummaryType;
    int             wins;
}