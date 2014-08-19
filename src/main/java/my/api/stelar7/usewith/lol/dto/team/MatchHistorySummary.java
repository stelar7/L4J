package my.api.stelar7.usewith.lol.dto.team;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MatchHistorySummary
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