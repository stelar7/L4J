package my.api.stelar7.usewith.lol.dto.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.DataCall;
import my.api.stelar7.usewith.lol.basic.URLEndpoint;
import my.api.stelar7.usewith.lol.dto.match.MatchDetail;

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

    public MatchDetail getFullMatch(final boolean timeline)
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.MATCH);
            call.setData(Arrays.asList(this.gameId));
            call.setUrlParams(new HashMap<String, Object>()
                    {
                {
                    this.put("includeTimeline", timeline);
                }
                    });
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            return L4J.getMapper().readValue(json, MatchDetail.class);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
