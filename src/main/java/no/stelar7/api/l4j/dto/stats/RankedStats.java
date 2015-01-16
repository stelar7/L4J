package no.stelar7.api.l4j.dto.stats;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RankedStats implements Serializable
{
    List<ChampionStats> champions;
    Long                modifyDate;
    String              modifyDateStr;
    Long                summonerId;

}