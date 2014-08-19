package my.api.stelar7.usewith.lol.dto.runes;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RunePages
{
    List<RunePage> pages;
    Long           summonerId;

}
