package my.api.stelar7.usewith.lol;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import my.api.stelar7.usewith.lol.basic.*;
import my.api.stelar7.usewith.lol.dto.champion.Champion;
import my.api.stelar7.usewith.lol.dto.champion.ChampionList;
import my.api.stelar7.usewith.lol.dto.league.League;
import my.api.stelar7.usewith.lol.dto.league.LeagueType;
import my.api.stelar7.usewith.lol.dto.match.MatchDetail;
import my.api.stelar7.usewith.lol.dto.matchhistory.PlayerHistory;
import my.api.stelar7.usewith.lol.dto.summoner.Summoner;
import my.api.stelar7.usewith.lol.dto.team.Team;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.util.concurrent.RateLimiter;

public class L4J
{

    @Getter
    @Setter
    static String                     APIKey;
    @Getter
    @Setter
    static RateLimiter                rateLimiter = RateLimiter.create(7.0 / 10.0);
    @Getter
    @Setter
    static Server                     region      = Server.EUW;
    @Getter
    private static final ObjectMapper mapper      = new ObjectMapper();
    @Getter
    private final StaticCaller        staticData  = new StaticCaller();

    /**
     *
     * @param key
     *            Your development API key
     */
    public L4J(final String key)
    {
        this(key, 7.0D / 10.0D, Server.EUW);
    }

    /**
     *
     * @param key
     *            Your development API key
     * @param requestsOverTime
     *            Your rate limit
     * @param server
     *            Your regional endpoint
     */
    public L4J(final String key, final double requestsOverTime, final Server server)
    {
        this(key, RateLimiter.create(requestsOverTime), server);
    }

    /**
     *
     * @param key
     *            Your development API key
     * @param limiter
     *            The rate limiter to use
     * @param server
     *            Your regional endpoint
     */
    public L4J(final String key, final RateLimiter limiter, final Server server)
    {
        L4J.APIKey = key;
        L4J.rateLimiter = limiter;
        L4J.region = Server.EUW;
        L4J.mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    /**
     * Gets the current Challenger League
     *
     * @param type
     *            The type of league to find (5x5,3x3,SOLOQ)
     * @return the League
     */
    public League getChallengerLeague(final LeagueType type)
    {
        final League test = CacheHolder.getChallengerLeague().getIfPresent(type);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.CHALLENGER_LEAGUE);
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    this.put("type", type);
                }
            });
            call.setVerbose(true);
            final League league = L4J.getMapper().readValue(call.doCall(), League.class);
            CacheHolder.getChallengerLeague().put(type, league);
            return league;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a list of all Champions avalible in the game
     *
     * @return list of all Champions avalible in the game
     */
    public List<Champion> getChampionList()
    {
        if (CacheHolder.getChampionList().getIfPresent(0) != null) { return CacheHolder.getChampionList().getIfPresent(0).getChampions(); }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.CHAMPION_LIST);
            final ChampionList all = L4J.mapper.readValue(call.doCall(), ChampionList.class);
            CacheHolder.getChampionList().put(0, all);
            return all.getChampions();
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a match from its id
     *
     * @param id
     *            The id of the match to find
     * @return MatchDetail of the match
     */
    public MatchDetail getMatch(final long id)
    {
        final MatchDetail test = CacheHolder.getMatchDetails().getIfPresent(id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.MATCH);
            call.setVerbose(true);
            call.setData(Arrays.asList(id));
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    this.put("includeTimeline", true);
                }
            });
            final MatchDetail match = L4J.getMapper().readValue(call.doCall(), MatchDetail.class);
            CacheHolder.getMatchDetails().put(id, match);
            return match;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a players match history from their id
     *
     * @param id
     *            The id of the player to find history from
     * @return PlayerHistory from the player
     */
    public PlayerHistory getMatchHistory(final long id)
    {
        final PlayerHistory test = CacheHolder.getPlayerHistory().getIfPresent(id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.MATCH_HISTORY);
            call.setVerbose(true);
            call.setData(Arrays.asList(id));
            final PlayerHistory match = L4J.getMapper().readValue(call.doCall(), PlayerHistory.class);
            CacheHolder.getPlayerHistory().put(id, match);
            return match;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a list of Summoners from their name
     *
     * @return list of of Summoners from their name
     */
    public List<Summoner> getSummonersByName(final String... names)
    {
        final List<String> copy = new LinkedList<>(Arrays.asList(names));
        final List<Summoner> summoners = new ArrayList<>();
        for (final String s : names)
        {
            final Summoner test = CacheHolder.getSummoners().getIfPresent(s.toLowerCase().replaceAll(" ", ""));
            if (test != null)
            {
                summoners.add(test);
                copy.remove(s);
            }
        }
        if (copy.isEmpty()) { return summoners; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_BY_NAME);
            call.setData(copy);
            call.setVerbose(true);
            final JsonNode node = L4J.mapper.readTree(call.doCall());
            for (final String s : copy)
            {
                summoners.add(L4J.mapper.readValue(node.get(s.toLowerCase().replaceAll(" ", "")), Summoner.class));
            }
            summoners.forEach(s -> CacheHolder.getSummoners().put(s.getName().toLowerCase().replaceAll(" ", ""), s));
            return summoners;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets Teams from their ids (TEAM-XXXXXX-XXXX-XXXX-XXXX-XXXXXXXX)
     *
     * @param id
     *            A arrays of id's to get teams from
     * @return a list of Teams
     */
    public List<Team> getTeamsByID(final String... id)
    {
        final List<String> copy = new LinkedList<>(Arrays.asList(id));
        final List<Team> teams = new ArrayList<>();
        for (final String s : id)
        {
            final Team test = CacheHolder.getTeams().getIfPresent(s);
            if (test != null)
            {
                teams.add(test);
                copy.remove(s);
            }
        }
        if (copy.isEmpty()) { return teams; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.TEAM_BY_ID);
            call.setData(copy);
            final JsonNode node = L4J.mapper.readTree(call.doCall());
            for (final String s : copy)
            {
                teams.add(L4J.mapper.readValue(node.get(s), Team.class));
            }
            teams.forEach(s -> CacheHolder.getTeams().put(s.getFullId(), s));
            return teams;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
