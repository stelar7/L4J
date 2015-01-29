package no.stelar7.api.l4j.dto.game;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.DataCall;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.basic.URLEndpoint;
import no.stelar7.api.l4j.dto.match.MatchDetail;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Game  implements Serializable
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

    public MatchDetail getFullMatch(final boolean timeline) throws LibraryException
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
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
