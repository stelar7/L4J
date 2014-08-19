package my.api.stelar7.usewith.lol.dto.stats;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChampionStats
{
    int             id;
    AggregatedStats stats;

}