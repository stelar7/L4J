package no.stelar7.api.l4j.dto.match;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ParticipantIdentity
{
    int    participantId;
    Player player;
}
