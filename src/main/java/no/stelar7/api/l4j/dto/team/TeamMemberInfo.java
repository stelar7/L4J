package no.stelar7.api.l4j.dto.team;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeamMemberInfo implements Serializable
{
    Long   inviteDate;
    Long   joinDate;
    Long   playerId;
    String status;

}