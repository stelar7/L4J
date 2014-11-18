package my.api.stelar7.usewith.lol.dto.staticdata.champion;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Image;

@Getter
@ToString
public class Champion
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
