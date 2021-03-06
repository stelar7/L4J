package no.stelar7.api.l4j.dto.team;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Roster implements Serializable
{
    List<TeamMemberInfo> memberList;
    Long                 ownerId;

}
