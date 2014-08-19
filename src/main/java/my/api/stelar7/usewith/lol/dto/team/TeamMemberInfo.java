package my.api.stelar7.usewith.lol.dto.team;

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