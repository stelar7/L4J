package my.api.stelar7.usewith.lol.dto.staticdata.rune;

import java.util.Map;

import my.api.stelar7.usewith.lol.dto.staticdata.general.BasicData;
import lombok.Getter;
import lombok.ToString;
@Getter
@ToString
public class RuneList
{
    BasicData         basic;
    Map<String, Rune> data;
    String            type;
    String            version;
}
