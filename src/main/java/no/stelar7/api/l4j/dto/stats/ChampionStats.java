package no.stelar7.api.l4j.dto.stats;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChampionStats
{
    int             id;
    AggregatedStats stats;

}