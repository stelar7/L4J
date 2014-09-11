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
    static String                       APIKey;
    @Getter
    @Setter
    static HashMap<Server, RateLimiter> rateLimiter = new HashMap<>();
    @Getter
    @Setter
    static Server                       region      = Server.EUW;
    @Getter
    private static final ObjectMapper   mapper      = new ObjectMapper();
    @Getter
    private final StaticCaller          staticData  = new StaticCaller();

    /**
     *
     * @param key
     *            Your development API key
     */
    public L4J(final String key)
    {
        this(key, 7.0 / 10.0, L4J.region);
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
    public L4J(final String key, final double rate, final Server server)
    {
        L4J.APIKey = key;
        L4J.region = server;
        for (Server s : Server.values()) {
            rateLimiter.put(s, RateLimiter.create(rate));
        }
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
        final League test = CacheData.getChallengerLeague().get(type);
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
            final String json = call.doCall();
            if (call.isError()) { throw new LibraryException(LibraryException.lastError); }
            final League league = L4J.getMapper().readValue(json, League.class);
            CacheData.getChallengerLeague().put(type, league);
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
        if (CacheData.getChampionList().get(0) != null) { return CacheData.getChampionList().get(0).getChampions(); }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.CHAMPION_LIST);
            final String json = call.doCall();
            if (call.isError()) { throw new LibraryException(LibraryException.lastError); }
            final ChampionList all = L4J.mapper.readValue(json, ChampionList.class);
            CacheData.getChampionList().put(0, all);
            return all.getChampions();
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a list of leagues from summoner IDs
     *
     * @return list of leagues from summoner IDs
     */
    public HashMap<Long, List<League>> getLeagueBySummoners(final boolean full, final Long... names)
    {
        final List<Long> copy = new LinkedList<>(Arrays.asList(names));
        final HashMap<Long, List<League>> summoners = new HashMap<>();
        for (final Long s : names)
        {
            final List<League> test = full ? CacheData.getLeaguesFull().get(s) : CacheData.getLeaguesSelf().get(s);
            if (test != null)
            {
                summoners.put(s, test);
                copy.remove(s);
            }
        }
        if (copy.isEmpty()) { return summoners; }
        while (copy.size() > 10)
        {
            final List<Long> remove = copy.subList(0, 10 > copy.size() ? copy.size() : 10);
            copy.removeAll(remove);
            summoners.putAll(this.getLeagueBySummoners(full, remove.toArray(new Long[10])));
        }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(full ? URLEndpoint.LEAGUE_BY_SUMMONER_FULL : URLEndpoint.LEAGUE_BY_SUMMONER);
            call.setData(copy);
            call.setVerbose(true);
            final String json = call.doCall();
            if (call.isError()) { throw new LibraryException(LibraryException.lastError); }
            final JsonNode node = L4J.mapper.readTree(json);
            for (final Long s : copy)
            {
                final List<League> pages = L4J.getMapper().convertValue(node.get("" + s), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
                summoners.put(s, pages);
                if (full)
                {
                    CacheData.getLeaguesFull().put(s, pages);
                } else
                {
                    CacheData.getLeaguesSelf().put(s, pages);
                }
            }
            return summoners;
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
        final MatchDetail test = CacheData.getMatchDetails().get(id);
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
            final String json = call.doCall();
            if (call.isError()) { throw new LibraryException(LibraryException.lastError); }
            final MatchDetail match = L4J.getMapper().readValue(json, MatchDetail.class);
            CacheData.getMatchDetails().put(id, match);
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
            final Summoner test = CacheData.getSummoners().get(s.toLowerCase().replaceAll(" ", ""));
            if (test != null)
            {
                summoners.add(test);
                copy.remove(s);
            }
        }
        if (copy.isEmpty()) { return summoners; }
        while (copy.size() > 40)
        {
            final List<String> remove = copy.subList(0, 40 > copy.size() ? copy.size() : 40);
            copy.removeAll(remove);
            summoners.addAll(this.getSummonersByName(remove.toArray(new String[40])));
        }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_BY_NAME);
            call.setData(copy);
            call.setVerbose(true);
            final String json = call.doCall();
            if (call.isError()) { throw new LibraryException(LibraryException.lastError); }
            final JsonNode node = L4J.mapper.readTree(json);
            for (final String s : copy)
            {
                final Summoner sum = L4J.mapper.readValue(node.get(s.toLowerCase().replaceAll(" ", "")), Summoner.class);
                summoners.add(sum);
                CacheData.getSummoners().put(s.toLowerCase().replaceAll(" ", ""), sum);
            }
            return summoners;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets Teams from summoner IDs
     *
     * @param id
     *            A arrays of id's to get teams from
     * @return a list of Teams
     */
    public HashMap<Long, List<Team>> getTeamBySummonerID(final Long... ids)
    {
        final List<Long> copy = new LinkedList<>(Arrays.asList(ids));
        final HashMap<Long, List<Team>> teams = new HashMap<>();
        for (final long s : ids)
        {
            final List<Team> test = CacheData.getTeamList().get(s);
            if (test != null)
            {
                teams.put(s, test);
                copy.remove(s);
            }
        }
        if (copy.isEmpty()) { return teams; }
        while (copy.size() > 10)
        {
            final List<Long> remove = copy.subList(0, 10 > copy.size() ? copy.size() : 10);
            copy.removeAll(remove);
            teams.putAll(this.getTeamBySummonerID(remove.toArray(new Long[10])));
        }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.TEAM_BY_SUMMONER);
            call.setData(copy);
            final String json = call.doCall();
            if (call.isError()) { throw new LibraryException(LibraryException.lastError); }
            final JsonNode node = L4J.mapper.readTree(json);
            for (final Long s : copy)
            {
                final List<Team> tea = L4J.mapper.readValue(node.get("" + s), L4J.mapper.getTypeFactory().constructCollectionType(List.class, Team.class));
                teams.put(s, tea);
                CacheData.getTeamList().put(s, tea);
            }
            return teams;
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
            final Team test = CacheData.getTeams().get(s);
            if (test != null)
            {
                teams.add(test);
                copy.remove(s);
            }
        }
        if (copy.isEmpty()) { return teams; }
        while (copy.size() > 10)
        {
            final List<String> remove = copy.subList(0, 10 > copy.size() ? copy.size() : 10);
            copy.removeAll(remove);
            teams.addAll(this.getTeamsByID(remove.toArray(new String[10])));
        }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.TEAM_BY_ID);
            call.setData(copy);
            final String json = call.doCall();
            if (call.isError()) { throw new LibraryException(LibraryException.lastError); }
            final JsonNode node = L4J.mapper.readTree(json);
            for (final String s : copy)
            {
                final Team tea = L4J.mapper.readValue(node.get(s), Team.class);
                teams.add(tea);
                CacheData.getTeams().put(tea.getFullId(), tea);
            }
            return teams;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
