package my.api.stelar7.usewith.lol.dto.team;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Roster
{
    List<TeamMemberInfo> memberList;
    Long                 ownerId;

}
