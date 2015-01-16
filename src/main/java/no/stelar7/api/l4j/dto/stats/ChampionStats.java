package no.stelar7.api.l4j.dto.stats;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChampionStats implements Serializable
{
    int             id;
    AggregatedStats stats;

}