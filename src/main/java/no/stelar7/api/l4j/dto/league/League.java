package no.stelar7.api.l4j.dto.league;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class League
{
    List<LeagueEntry> entries;
    String            name;
    String            participantId;
    String            queue;
    String            tier;
}
