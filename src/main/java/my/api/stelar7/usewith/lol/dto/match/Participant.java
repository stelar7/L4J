package my.api.stelar7.usewith.lol.dto.match;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Participant
{
    int                 championId;
    int                 participantId;
    int                 spell1Id;
    int                 spell2Id;
    ParticipantStats    stats;
    int                 teamId;
    ParticipantTimeline timeline;
}
