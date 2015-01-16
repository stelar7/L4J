package no.stelar7.api.l4j.dto.team;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeamStat implements Serializable
{
    TeamId              teamId;
    Set<TeamStatDetail> teamStatDetails;

}