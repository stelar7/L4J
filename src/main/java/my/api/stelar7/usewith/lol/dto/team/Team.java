package my.api.stelar7.usewith.lol.dto.team;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.DataCall;
import my.api.stelar7.usewith.lol.basic.URLEndpoint;
import my.api.stelar7.usewith.lol.dto.league.League;

import org.codehaus.jackson.JsonNode;

@Getter
@ToString
public class Team
{
    Long                      createDate;
    String                    fullId;
    Long                      lastGameDate;
    Long                      lastJoinDate;
    Long                      lastJoinedRankedTeamQueueDate;
    List<MatchHistorySummary> matchHistory;
    Long                      modifyDate;
    String                    name;
    Roster                    roster;
    Long                      secondLastJoinDate;
    String                    status;
    String                    tag;
    List<TeamStatDetail>      teamStatDetails;
    Long                      thirdLastJoinDate;

    public List<League> getFullLeague()
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_TEAM_FULL);
            call.setData(Arrays.asList("" + this.fullId));
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("" + this.fullId), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<League> getSelfLeague()
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_TEAM);
            call.setData(Arrays.asList("" + this.fullId));
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("" + this.fullId), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
