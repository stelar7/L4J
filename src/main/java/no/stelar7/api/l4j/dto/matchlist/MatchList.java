package no.stelar7.api.l4j.dto.matchlist;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MatchList
{
    int                  endIndex;
    int                  startIndex;
    int                  totalGames;
    List<MatchReference> matches;
}
