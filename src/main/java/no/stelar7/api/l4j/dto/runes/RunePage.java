package no.stelar7.api.l4j.dto.runes;

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