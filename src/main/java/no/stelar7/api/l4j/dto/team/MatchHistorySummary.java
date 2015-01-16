package no.stelar7.api.l4j.dto.team;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MatchHistorySummary implements Serializable
{
    int     assists;
    Long    date;
    int     deaths;
    Long    gameId;
    String  gameMode;
    boolean invalid;
    int     kills;
    int     mapId;
    int     opposingTeamKills;
    String  opposingTeamName;
    boolean win;

}