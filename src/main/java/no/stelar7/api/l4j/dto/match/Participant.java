package no.stelar7.api.l4j.dto.match;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Participant implements Serializable
{
    int                 championId;
    String              highestAchievedSeasonTier;
    List<MatchMastery>  masteries;
    int                 participantId;
    List<MatchRune>     runes;
    int                 spell1Id;
    int                 spell2Id;
    ParticipantStats    stats;
    int                 teamId;
    ParticipantTimeline timeline;
}
