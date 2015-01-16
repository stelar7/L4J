package no.stelar7.api.l4j.dto.match;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MatchRune implements Serializable
{
    long rank;
    long runeId;
}
