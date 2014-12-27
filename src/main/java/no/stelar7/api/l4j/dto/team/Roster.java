package no.stelar7.api.l4j.dto.team;

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
