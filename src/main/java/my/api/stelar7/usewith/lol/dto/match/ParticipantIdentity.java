package my.api.stelar7.usewith.lol.dto.match;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ParticipantIdentity
{
    int    participantId;
    Player player;
}