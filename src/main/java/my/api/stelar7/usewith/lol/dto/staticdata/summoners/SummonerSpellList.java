package my.api.stelar7.usewith.lol.dto.staticdata.summoners;

import java.util.Map;
import lombok.Getter;
import lombok.ToString;
@Getter
@ToString
public class SummonerSpellList
{
    Map<String, SummonerSpell> data;
    String                     type;
    String                     version;
}
