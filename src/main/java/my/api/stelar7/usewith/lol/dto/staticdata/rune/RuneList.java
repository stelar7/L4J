package my.api.stelar7.usewith.lol.dto.staticdata.rune;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.staticdata.general.BasicData;

@Getter
@ToString
public class RuneList
{
    BasicData         basic;
    Map<String, Rune> data;
    String            type;
    String            version;
}
