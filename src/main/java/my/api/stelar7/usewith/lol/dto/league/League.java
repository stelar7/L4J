package my.api.stelar7.usewith.lol.dto.league;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class League
{
    List<LeagueEntry> entries;
    String            name;
    String            queue;
    String            tier;
    String            participantId;
}
