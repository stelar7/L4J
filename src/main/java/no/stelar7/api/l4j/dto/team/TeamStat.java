package no.stelar7.api.l4j.dto.team;

import java.util.Set;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeamStat
{
    TeamId              teamId;
    Set<TeamStatDetail> teamStatDetails;

}