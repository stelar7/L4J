package my.api.stelar7.usewith.lol.dto.staticdata.summoners;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Image;
import my.api.stelar7.usewith.lol.dto.staticdata.general.LevelTip;
import my.api.stelar7.usewith.lol.dto.staticdata.general.SpellVars;

@Getter
@ToString
public class SummonerSpell
{
    List<Double>       cooldown;
    String             cooldownBurn;
    List<Integer>      cost;
    String             costBurn;
    String             costType;
    String             description;
    List<List<Double>> effect;
    List<String>       effectBurn;
    int                id;
    Image              image;
    String             key;
    LevelTip           leveltip;
    int                maxrank;
    List<String>       modes;
    String             name;
    Object             range;
    String             rangeBurn;
    String             resource;
    String             sanitizedDescription;
    String             sanitizedTooltip;
    int                summonerLevel;
    String             tooltip;
    List<SpellVars>    vars;
}
