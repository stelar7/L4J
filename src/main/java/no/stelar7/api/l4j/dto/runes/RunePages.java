package no.stelar7.api.l4j.dto.runes;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RunePages implements Serializable
{
    List<RunePage> pages;
    Long           summonerId;

}
