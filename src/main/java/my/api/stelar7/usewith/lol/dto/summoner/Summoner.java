package my.api.stelar7.usewith.lol.dto.summoner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.DataCall;
import my.api.stelar7.usewith.lol.basic.Server;
import my.api.stelar7.usewith.lol.basic.URLEndpoint;
import my.api.stelar7.usewith.lol.dto.game.RecentGames;
import my.api.stelar7.usewith.lol.dto.general.Season;
import my.api.stelar7.usewith.lol.dto.general.SubType;
import my.api.stelar7.usewith.lol.dto.league.League;
import my.api.stelar7.usewith.lol.dto.masteries.MasteryPage;
import my.api.stelar7.usewith.lol.dto.matchhistory.PlayerHistory;
import my.api.stelar7.usewith.lol.dto.runes.RunePage;
import my.api.stelar7.usewith.lol.dto.stats.RankedStats;
import my.api.stelar7.usewith.lol.dto.stats.StatSummary;
import my.api.stelar7.usewith.lol.dto.team.Team;

import org.codehaus.jackson.JsonNode;

@Getter
@ToString
public class Summoner
{
    Long   id;
    String name;
    int    profileIconId;
    Long   revisionDate;
    Long   summonerLevel;
    Server region;

    public List<League> getFullLeague()
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER_FULL);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<MasteryPage> getMasteryPages()
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_MASTERIES);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            return L4J.getMapper().convertValue(L4J.getMapper().readTree(json).get("" + this.id).get("pages"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, MasteryPage.class));
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the players matchhistory (max 15 games)
     *
     * @param champids
     *            list of champion IDs used for fetching games (can be null)
     * @param rankedQueues
     *            list of ranked queues used for fetching games. Non-ranked queues will be ignored (can be null)
     * @param beginIndex
     *            the begin index used for fetching (can be null)
     * @param endIndex
     *            the end index used for fetching (can be null)
     *
     * @return PlayerHistory from the player
     */
    public PlayerHistory getMatchHistory(final List<Integer> champids, final List<SubType> rankedQueues, final Integer beginIndex, final Integer endIndex)
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.MATCH_HISTORY);
            call.setData(Arrays.asList(this.id));
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (champids != null)
                    {
                        this.put("championIds", champids.toString().substring(1, champids.toString().length() - 1));
                    }
                    if (rankedQueues != null)
                    {
                        this.put("rankedQueues", rankedQueues.toString().substring(1, rankedQueues.toString().length() - 1));
                    }
                    if (beginIndex != null)
                    {
                        this.put("beginIndex", beginIndex);
                    }
                    if (endIndex != null)
                    {
                        this.put("endIndex", endIndex);
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            if (json.equals("{}")) { return null; }
            return L4J.getMapper().readValue(json, PlayerHistory.class);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public RankedStats getRankedStats(final Season season)
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.RANKED_STATS);
            call.setData(Arrays.asList(this.id));
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (season != null)
                    {
                        put("season", season.name().toLowerCase());
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            return L4J.getMapper().readValue(json, RankedStats.class);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public RecentGames getRecentGames()
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.RECENT_GAMES);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            return L4J.getMapper().readValue(json, RecentGames.class);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<RunePage> getRunePages()
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_RUNES);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("" + this.id).get("pages"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, RunePage.class));
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
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            return L4J.getMapper().convertValue(L4J.getMapper().readTree(json).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<StatSummary> getStatSummary(final Season season)
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STAT_SUMMARY);
            call.setData(Arrays.asList(this.id));
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (season != null)
                    {
                        put("season", season.name().toLowerCase());
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("playerStatSummaries"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, StatSummary.class));
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Team> getTeams()
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.TEAM_BY_SUMMONER);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError()) { throw call.getErrorData(); }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, Team.class));
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}