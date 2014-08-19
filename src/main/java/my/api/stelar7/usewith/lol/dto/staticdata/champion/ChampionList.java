package my.api.stelar7.usewith.lol.dto.staticdata.champion;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChampionList
{
    Map<String, Champion> data;
    Map<String, String>   keys;
    String                format;
    String                type;
    String                version;
}
