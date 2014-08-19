package my.api.stelar7.usewith.lol.dto.stats;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StatSummary
{
    AggregatedStats aggregatedStats;
    int             lossses;
    Long            modifyDate;
    String          playerStatSummaryType;
    int             wins;
}