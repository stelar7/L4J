package my.api.stelar7.usewith.lol.dto.summoner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.CacheData;
import my.api.stelar7.usewith.lol.basic.DataCall;
import my.api.stelar7.usewith.lol.basic.URLEndpoint;
import my.api.stelar7.usewith.lol.dto.game.Game;
import my.api.stelar7.usewith.lol.dto.game.RecentGames;
import my.api.stelar7.usewith.lol.dto.general.SubType;
import my.api.stelar7.usewith.lol.dto.league.League;
import my.api.stelar7.usewith.lol.dto.masteries.MasteryPage;
import my.api.stelar7.usewith.lol.dto.matchhistory.PlayerHistory;
import my.api.stelar7.usewith.lol.dto.runes.RunePage;
import my.api.stelar7.usewith.lol.dto.stats.RankedStats;
import my.api.stelar7.usewith.lol.dto.stats.StatSummary;
import my.api.stelar7.usewith.lol.dto.team.Team;

@Getter
@ToString
public class Summoner
{
    Long   id;
    String name;
    int    profileIconId;
    Long   revisionDate;
    Long   summonerLevel;

    public List<League> getFullLeague()
    {
        final List<League> test = CacheData.getLeaguesFull().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER_FULL);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<League> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
            CacheData.getLeaguesFull().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<MasteryPage> getMasteryPages()
    {
        final List<MasteryPage> test = CacheData.getMasteryPages().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_MASTERIES);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final String json = call.doCall();
            if (json == null) { return null; }
            final List<MasteryPage> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(json).get("" + this.id).get("pages"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, MasteryPage.class));
            CacheData.getMasteryPages().put(this.id, pages);
            return pages;
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
     *            list of champion IDs to use for fetching games (can be null)
     * @param rankedQueues
     *            list of ranked queues yo use for fetching games. Non-ranked queues will be ignored (can be null)
     * @param beginIndex
     *            the begin index to use for fetching (can be null)
     * @param endIndex
     *            the end index to use for fetching (can be null)
     *
     * @return PlayerHistory from the player
     */
    public PlayerHistory getMatchHistory(final List<Integer> champids, final List<SubType> rankedQueues, final Integer beginIndex, final Integer endIndex)
    {
        final PlayerHistory test = CacheData.getPlayerHistory().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.MATCH_HISTORY);
            call.setVerbose(true);
            call.setData(Arrays.asList(this.id));
            call.setUrlParams(new HashMap<String, Object>()
                    {
                {
                    if (champids != null)
                    {
                        this.put("championIds", champids.toString().substring(1, champids.toString().length() - 2));
                    }
                    if (rankedQueues != null)
                    {
                        this.put("rankedQueues", rankedQueues.toString().substring(1, rankedQueues.toString().length() - 2));
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
            if (json.equals("{}")) { return null; }
            final PlayerHistory match = L4J.getMapper().readValue(json, PlayerHistory.class);
            if ((champids == null) && (champids == null) && (champids == null) && (champids == null))
            {
                CacheData.getPlayerHistory().put(this.id, match);
            }
            return match;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public RankedStats getRankedStats()
    {
        final RankedStats test = CacheData.getRankedStats().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.RANKED_STATS);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final RankedStats pages = L4J.getMapper().readValue(call.doCall(), RankedStats.class);
            CacheData.getRankedStats().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Game> getRecentGames()
    {
        final RecentGames test = CacheData.getGames().get(this.id);
        if (test != null) { return test.getGames(); }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.RECENT_GAMES);
            call.setData(Arrays.asList(this.id));
            final RecentGames games = L4J.getMapper().readValue(call.doCall(), RecentGames.class);
            CacheData.getGames().put(this.id, games);
            return games.getGames();
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<RunePage> getRunePages()
    {
        final List<RunePage> test = CacheData.getRunePages().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_RUNES);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final String json = call.doCall();
            if (json == null) { return null; }
            final List<RunePage> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(json).get("" + this.id).get("pages"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, RunePage.class));
            CacheData.getRunePages().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<League> getSelfLeague()
    {
        final List<League> test = CacheData.getLeaguesSelf().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<League> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
            CacheData.getLeaguesSelf().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<StatSummary> getStatSummary()
    {
        final List<StatSummary> test = CacheData.getStatSummary().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STAT_SUMMARY);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<StatSummary> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("playerStatSummaries"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, StatSummary.class));
            CacheData.getStatSummary().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Team> getTeams()
    {
        final List<Team> test = CacheData.getTeamList().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.TEAM_BY_SUMMONER);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final String json = call.doCall();
            if (json == null) { return null; }
            final List<Team> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(json).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, Team.class));
            CacheData.getTeamList().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}