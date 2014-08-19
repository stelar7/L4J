package my.api.stelar7.usewith.lol.dto.team;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.CacheHolder;
import my.api.stelar7.usewith.lol.basic.DataCall;
import my.api.stelar7.usewith.lol.basic.URLEndpoint;
import my.api.stelar7.usewith.lol.dto.league.League;

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

    public League getFullLeague()
    {
        final League test = CacheHolder.getLeaguesFull().getIfPresent(this.fullId);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_TEAM_FULL);
            call.setData(Arrays.asList("" + this.fullId));
            call.setVerbose(true);
            final List<League> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.fullId), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
            CacheHolder.getLeaguesFull().put(this.fullId, pages.get(0));
            return pages.get(0);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public League getSelfLeague()
    {
        final League test = CacheHolder.getLeaguesSelf().getIfPresent(this.fullId);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_TEAM);
            call.setData(Arrays.asList("" + this.fullId));
            call.setVerbose(true);
            final List<League> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.fullId), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
            CacheHolder.getLeaguesSelf().put(this.fullId, pages.get(0));
            return pages.get(0);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
