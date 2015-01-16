package no.stelar7.api.l4j.dto.match;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BannedChampion implements Serializable
{
    int championId;
    int pickTurn;
    int teamId;
}
