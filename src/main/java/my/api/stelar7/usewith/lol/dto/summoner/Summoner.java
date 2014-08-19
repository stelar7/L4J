package my.api.stelar7.usewith.lol.dto.summoner;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.CacheHolder;
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
        final League test = CacheHolder.getLeaguesFull().getIfPresent(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER_FULL);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<League> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
            CacheHolder.getLeaguesFull().put(this.id, pages.get(0));
            return pages.get(0);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<MasteryPage> getMasteryPages()
    {
        final List<MasteryPage> test = CacheHolder.getMasteryPages().getIfPresent(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_MASTERIES);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<MasteryPage> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.id).get("pages"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, MasteryPage.class));
            CacheHolder.getMasteryPages().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public RankedStats getRankedStats()
    {
        final RankedStats test = CacheHolder.getRankedStats().getIfPresent(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.RANKED_STATS);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final RankedStats pages = L4J.getMapper().readValue(call.doCall(), RankedStats.class);
            CacheHolder.getRankedStats().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Game> getRecentGames()
    {
        final List<Game> test = CacheHolder.getGames().getIfPresent(this.id).getGames();
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.RECENT_GAMES);
            call.setData(Arrays.asList(this.id));
            final RecentGames games = L4J.getMapper().readValue(call.doCall(), RecentGames.class);
            CacheHolder.getGames().put(this.id, games);
            return games.getGames();
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<RunePage> getRunePages()
    {
        final List<RunePage> test = CacheHolder.getRunePages().getIfPresent(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_RUNES);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<RunePage> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.id).get("pages"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, RunePage.class));
            CacheHolder.getRunePages().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public League getSelfLeague()
    {
        final League test = CacheHolder.getLeaguesSelf().getIfPresent(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<League> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
            CacheHolder.getLeaguesSelf().put(this.id, pages.get(0));
            return pages.get(0);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<StatSummary> getStatSummary()
    {
        final List<StatSummary> test = CacheHolder.getStatSummary().getIfPresent(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STAT_SUMMARY);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<StatSummary> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("playerStatSummaries"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, StatSummary.class));
            CacheHolder.getStatSummary().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Team> getTeams()
    {
        final List<Team> test = CacheHolder.getTeamList().getIfPresent(this.id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.TEAM_BY_SUMMONER);
            call.setData(Arrays.asList(this.id));
            call.setVerbose(true);
            final List<Team> pages = L4J.getMapper().convertValue(L4J.getMapper().readTree(call.doCall()).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, Team.class));
            CacheHolder.getTeamList().put(this.id, pages);
            return pages;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}