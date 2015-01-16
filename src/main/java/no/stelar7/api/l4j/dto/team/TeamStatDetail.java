package no.stelar7.api.l4j.dto.team;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeamStatDetail implements Serializable
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