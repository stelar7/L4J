package no.stelar7.api.l4j.dto.matchlist;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MatchReference
{
    long   champion;
    String lane;
    long   matchId;
    String platformId;
    String queue;
    String role;
    String season;
    long   timestamp;
}
