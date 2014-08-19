package my.api.stelar7.usewith.lol.dto.team;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeamStatDetail
{
    int    averageGamesPlayed;
    int    losses;
    int    maxRating;
    int    rating;
    int    seedRating;
    TeamId teamId;
    String teamStatType;
    int    wins;

}