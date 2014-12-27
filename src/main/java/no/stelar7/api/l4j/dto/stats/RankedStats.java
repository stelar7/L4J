package no.stelar7.api.l4j.dto.stats;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RankedStats
{
    List<ChampionStats> champions;
    Long                modifyDate;
    String              modifyDateStr;
    Long                summonerId;

}