package no.stelar7.api.l4j.dto.match;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BannedChampion
{
    int championId;
    int pickTurn;
    int teamId;
}
