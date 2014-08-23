package my.api.stelar7.usewith.lol.dto.summoner;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.CacheData;
import my.api.stelar7.usewith.lol.basic.DataCall;
import my.api.stelar7.usewith.lol.basic.URLEndpoint;
import my.api.stelar7.usewith.lol.dto.game.Game;
import my.api.stelar7.usewith.lol.dto.game.RecentGames;
import my.api.stelar7.usewith.lol.dto.league.League;
import my.api.stelar7.usewith.lol.dto.masteries.MasteryPage;
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

    public League getFullLeague()
    {
        final League test = CacheData.getLeaguesFull().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER_FULL);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<League> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
            CacheData.getLeaguesFull().put(this.id, pages.get(0));
            return pages.get(0);
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

    public League getSelfLeague()
    {
        final League test = CacheData.getLeaguesSelf().get(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<League> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
            CacheData.getLeaguesSelf().put(this.id, pages.get(0));
            return pages.get(0);
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