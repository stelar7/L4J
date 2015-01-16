package no.stelar7.api.l4j.dto.runes;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RuneSlot implements Serializable
{
    int runeSlotId;
    int runeId;

}