package my.api.stelar7.usewith.lol.dto.team;

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