package my.api.stelar7.usewith.lol.dto.runes;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RunePage
{
    boolean        current;
    Long           id;
    String         name;
    List<RuneSlot> slots;

}