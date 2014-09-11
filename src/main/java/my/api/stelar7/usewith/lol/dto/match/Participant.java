package my.api.stelar7.usewith.lol.dto.match;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Participant
{
    int                 championId;
    List<MatchMastery>  masteries;
    int                 participantId;
    List<MatchRune>     runes;
    int                 spell1Id;
    int                 spell2Id;
    ParticipantStats    stats;
    int                 teamId;
    ParticipantTimeline timeline;
}
