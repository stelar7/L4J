package no.stelar7.api.l4j.dto.champion;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChampionList implements Serializable
{
    List<Champion> champions;

}
