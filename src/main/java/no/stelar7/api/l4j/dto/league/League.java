package no.stelar7.api.l4j.dto.league;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
public class League implements Serializable
{

    @Getter
    List<LeagueEntry>    entries;
    @Getter
    String               name;
    @Getter
    String               participantId;
    @Getter
    String               tier;
    
    String               queue;
    
    public LeagueType getQueue() {
        return LeagueType.valueOf(queue);
    }
}
