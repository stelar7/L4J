package no.stelar7.api.l4j.dto.match;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ParticipantFrame
{
    int      currentGold;
    int      jungleMinionsKilled;
    int      level;
    int      minionsKilled;
    int      participantId;
    Position position;
    int      totalGold;
    int      xp;
}
