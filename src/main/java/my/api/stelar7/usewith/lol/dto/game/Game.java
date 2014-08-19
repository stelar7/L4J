package my.api.stelar7.usewith.lol.dto.game;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Game
{
    int          championId;
    Long         createDate;
    List<Player> fellowPlayers;
    Long         gameId;
    String       gameMode;
    String       gameType;
    boolean      invalid;
    int          ipEarned;
    int          level;
    int          mapId;
    int          spell1;
    int          spell2;
    RawStats     stats;
    String       subType;
    int          teamId;
}
