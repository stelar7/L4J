package no.stelar7.api.l4j.dto.match;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MatchMastery implements Serializable
{
    long masteryId;
    long rank;
}
