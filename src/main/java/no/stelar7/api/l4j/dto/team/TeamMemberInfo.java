package no.stelar7.api.l4j.dto.team;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeamMemberInfo
{
    Long   inviteDate;
    Long   joinDate;
    Long   playerId;
    String status;

}