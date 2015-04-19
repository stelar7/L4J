package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.staticdata.general.Image;

@Getter
@ToString
public class Champion implements Serializable
{
    List<String>        allytips;
    String              blurb;
    List<String>        enemytips;
    int                 id;
    Image               image;
    Info                info;
    String              key;
    String              lore;
    String              name;
    String              partype;
    Passive             passive;
    List<Recommended>   recommended;
    List<Skin>          skins;
    List<ChampionSpell> spells;
    Stats               stats;
    List<String>        tags;
    String              title;

}
