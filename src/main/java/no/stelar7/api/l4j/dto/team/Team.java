package no.stelar7.api.l4j.dto.team;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.DataCall;
import no.stelar7.api.l4j.basic.URLEndpoint;
import no.stelar7.api.l4j.dto.league.League;

import com.fasterxml.jackson.databind.JsonNode;

@Getter
@ToString
public class Team implements Serializable
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
