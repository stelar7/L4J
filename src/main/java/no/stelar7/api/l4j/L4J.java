package no.stelar7.api.l4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import no.stelar7.api.l4j.basic.*;
import no.stelar7.api.l4j.dto.champion.ChampionList;
import no.stelar7.api.l4j.dto.current_game.CurrentGameInfo;
import no.stelar7.api.l4j.dto.current_game.PlatformId;
import no.stelar7.api.l4j.dto.featured_game.FeaturedGames;
import no.stelar7.api.l4j.dto.league.League;
import no.stelar7.api.l4j.dto.league.LeagueType;
import no.stelar7.api.l4j.dto.match.MatchDetail;
import no.stelar7.api.l4j.dto.staticdata.general.Image;
import no.stelar7.api.l4j.dto.summoner.Summoner;
import no.stelar7.api.l4j.dto.team.Team;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;

public class L4J
{

    @Getter
    @Setter
    static String                       APIKey           = null;
    @Getter
    @Setter
    static HashMap<Server, RateLimiter> rateLimiter      = new HashMap<Server, RateLimiter>();
    @Getter
    @Setter
    static Server                       region           = Server.EUW;
    @Getter
    private static final ObjectMapper   mapper           = new ObjectMapper();
    @Getter
    @Setter
    public static boolean               verbose          = false;
    private static final int            MAX_PER_SUMMONER = 40;

    private static final int            MAX_PER_TEAM     = 10;
    private static final int            MAX_PER_LEAGUE   = 10;
    @Getter
    private final StaticCaller          staticData       = new StaticCaller();

    /**
     *
     * @param key
     *            Your development API key
     */
    public L4J(final String key)
    {
        this(key, 7.0 / 10.0);
    }

    /**
     *
     * @param key
     *            Your development API key
     * @param rate
     *            the rate to use (requests / seconds)
     */
    public L4J(final String key, final double rate)
    {
        this(key, rate, L4J.region);
    }

    /**
     *
     * @param key
     *            Your development API key
     * @param rate
     *            the rate to use (requests / seconds)
     * @param server
     *            Your regional endpoint
     */
    public L4J(final String key, final double rate, final Server server)
    {
        L4J.APIKey = key;
        L4J.region = server;
        for (final Server s : Server.values())
        {
            L4J.rateLimiter.put(s, RateLimiter.create(rate));
        }
        L4J.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Gets the current Challenger League
     *
     * @param type
     *            The type of league to find (5x5,3x3,SOLOQ)
     * @return the League
     * @throws LibraryException
     */
    public League getChallengerLeague(final LeagueType type) throws LibraryException
    {
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
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, League.class);
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
            return null;
        } catch (final IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a list of all Champions avalible in the game
     *
     * @return list of all Champions avalible in the game
     * @throws LibraryException
     */
    public ChampionList getChampionList() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.CHAMPION_LIST);
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.mapper.readValue(json, ChampionList.class);
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
            return null;
        } catch (final IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a on-going match from its id
     *
     * @param id
     *            The id of the match to find
     * @param timeline
     *            Include the timeline
     * @return CurrentGameInfo of the match
     * @throws LibraryException
     */
    public CurrentGameInfo getCurrentGameInfo(final PlatformId platform, final long summonerId) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.CURRENT_GAME);
            call.setExtraData(new HashMap<String, Object>()
                    {
                {
                    this.put("{platformId}", platform);
                    this.put("{summonerId}", summonerId);
                }
                    });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, CurrentGameInfo.class);
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
            return null;
        } catch (final IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets featured games from the server
     *
     * @return FeaturedGames from the server
     * @throws LibraryException
     */
    public FeaturedGames getFeaturedGames() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.FEATURED_GAMES);
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, FeaturedGames.class);
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
            return null;
        } catch (final IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the URL for the ImageDto
     *
     * @return a String with the URL to the image
     */
    public String getImageURL(final ImageType type, final Image image) throws LibraryException
    {
        return URLEndpoint.DDRAGON.getValue().replace("{dragonversion}", this.getStaticData().getRealm().getV()).replace("{type}", type.getURLPart()) + image.getFull();
    }

    /**
     * Gets the URL for the ImageDto
     *
     * @return a String with the URL to the image
     */
    public String getImageURL(final ImageType type, final String imagename) throws LibraryException
    {
        return URLEndpoint.DDRAGON.getValue().replace("{dragonversion}", this.getStaticData().getRealm().getV()).replace("{type}", type.getURLPart()) + imagename;
    }

    /**
     * Gets a list of leagues from summoner IDs
     *
     * @return list of leagues from summoner IDs
     * @throws LibraryException
     */
    public Map<Long, List<League>> getLeagueBySummoners(final boolean full, final Long... names) throws LibraryException
    {
        final List<Long> copy = new LinkedList<Long>(Arrays.asList(names));
        final HashMap<Long, List<League>> summoners = new HashMap<Long, List<League>>();
        while (copy.size() > L4J.MAX_PER_LEAGUE)
        {
            final List<Long> remove = new ArrayList<Long>(copy.subList(0, L4J.MAX_PER_LEAGUE > copy.size() ? copy.size() : L4J.MAX_PER_LEAGUE));
            copy.removeAll(remove);
            summoners.putAll(this.getLeagueBySummoners(full, remove.toArray(new Long[L4J.MAX_PER_LEAGUE])));
        }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(full ? URLEndpoint.LEAGUE_BY_SUMMONER_FULL : URLEndpoint.LEAGUE_BY_SUMMONER);
            call.setData(copy);
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            final JsonNode node = L4J.mapper.readTree(json);
            for (final Long s : copy)
            {
                final List<League> pages = L4J.getMapper().convertValue(node.get("" + s), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
                summoners.put(s, pages);
            }
            return summoners;
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
            return null;
        } catch (final IOException e)
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
     * @param timeline
     *            Include the timeline
     * @return MatchDetail of the match
     * @throws LibraryException
     */
    public MatchDetail getMatch(final long id, final boolean timeline) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.MATCH);
            call.setData(Arrays.asList(id));
            call.setUrlParams(new HashMap<String, Object>()
                    {
                {
                    this.put("includeTimeline", timeline);
                }
                    });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, MatchDetail.class);
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
            return null;
        } catch (final IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a list of Summoners from their name
     *
     * @return list of of Summoners from their name
     * @throws LibraryException
     */
    public Map<Long, Summoner> getSummonersByID(final Long... names) throws LibraryException
    {
        final List<Long> copy = new LinkedList<Long>(Arrays.asList(names));
        final HashMap<Long, Summoner> summoners = new HashMap<Long, Summoner>();
        while (copy.size() > L4J.MAX_PER_SUMMONER)
        {
            final List<Long> remove = new ArrayList<Long>(copy.subList(0, L4J.MAX_PER_SUMMONER > copy.size() ? copy.size() : L4J.MAX_PER_SUMMONER));
            copy.removeAll(remove);
            summoners.putAll(this.getSummonersByID(remove.toArray(new Long[L4J.MAX_PER_SUMMONER])));
        }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_BY_ID);
            call.setData(copy);
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            final JsonNode node = L4J.mapper.readTree(json);
            for (final Long s : copy)
            {
                final JsonNode innernode = node.get(Long.toString(s));
                if (innernode == null)
                {
                    summoners.put(s, null);
                } else
                {
                    final Summoner sum = L4J.mapper.readValue(innernode.toString(), Summoner.class);
                    final Field f = Summoner.class.getDeclaredField("region");
                    f.setAccessible(true);
                    f.set(sum, L4J.region);
                    summoners.put(s, sum);
                }
            }
            return summoners;
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (final IOException e)
        {
            e.printStackTrace();
        } catch (final IllegalArgumentException e)
        {
            e.printStackTrace();
        } catch (final IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (final NoSuchFieldException e)
        {
            e.printStackTrace();
        } catch (final SecurityException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a list of Summoners from their name
     *
     * @return list of of Summoners from their name
     * @throws LibraryException
     */
    public Map<String, Summoner> getSummonersByName(final String... names) throws LibraryException
    {
        final List<String> copy = new LinkedList<String>(Arrays.asList(names));
        final HashMap<String, Summoner> summoners = new HashMap<String, Summoner>();
        while (copy.size() > L4J.MAX_PER_SUMMONER)
        {
            final List<String> remove = new ArrayList<String>(copy.subList(0, L4J.MAX_PER_SUMMONER > copy.size() ? copy.size() : L4J.MAX_PER_SUMMONER));
            copy.removeAll(remove);
            summoners.putAll(this.getSummonersByName(remove.toArray(new String[L4J.MAX_PER_SUMMONER])));
        }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_BY_NAME);
            call.setData(copy);
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            final JsonNode node = L4J.mapper.readTree(json);
            for (final String s : copy)
            {
                final JsonNode innernode = node.get(s.toLowerCase().replaceAll(" ", ""));
                if (innernode == null)
                {
                    summoners.put(s, null);
                } else
                {
                    final Summoner sum = L4J.mapper.readValue(innernode.toString(), Summoner.class);
                    final Field f = Summoner.class.getDeclaredField("region");
                    f.setAccessible(true);
                    f.set(sum, L4J.region);
                    summoners.put(s, sum);
                }
            }
            return summoners;
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (final IOException e)
        {
            e.printStackTrace();
        } catch (final IllegalArgumentException e)
        {
            e.printStackTrace();
        } catch (final IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (final NoSuchFieldException e)
        {
            e.printStackTrace();
        } catch (final SecurityException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets Teams from summoner IDs
     *
     * @param id
     *            A arrays of id's to get teams from
     * @return a list of Teams
     * @throws LibraryException
     */
    public Map<Long, List<Team>> getTeamBySummonerID(final Long... ids) throws LibraryException
    {
        final List<Long> copy = new LinkedList<Long>(Arrays.asList(ids));
        final HashMap<Long, List<Team>> teams = new HashMap<Long, List<Team>>();
        while (copy.size() > L4J.MAX_PER_TEAM)
        {
            final List<Long> remove = new ArrayList<Long>(copy.subList(0, L4J.MAX_PER_TEAM > copy.size() ? copy.size() : L4J.MAX_PER_TEAM));
            copy.removeAll(remove);
            teams.putAll(this.getTeamBySummonerID(remove.toArray(new Long[L4J.MAX_PER_TEAM])));
        }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.TEAM_BY_SUMMONER);
            call.setData(copy);
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            final JsonNode node = L4J.mapper.readTree(json);
            for (final Long s : copy)
            {
                final JsonNode innernode = node.get("" + s);
                if (innernode == null)
                {
                    teams.put(s, null);
                } else
                {
                    final List<Team> tea = L4J.mapper.readValue(innernode.toString(), L4J.mapper.getTypeFactory().constructCollectionType(List.class, Team.class));
                    teams.put(s, tea);
                }
            }
            return teams;
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
            return null;
        } catch (final IOException e)
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
     * @throws LibraryException
     */
    public Map<String, Team> getTeamsByID(final String... id) throws LibraryException
    {
        final List<String> copy = new LinkedList<String>(Arrays.asList(id));
        final HashMap<String, Team> teams = new HashMap<String, Team>();
        while (copy.size() > L4J.MAX_PER_TEAM)
        {
            final List<String> remove = new ArrayList<String>(copy.subList(0, L4J.MAX_PER_TEAM > copy.size() ? copy.size() : L4J.MAX_PER_TEAM));
            copy.removeAll(remove);
            teams.putAll(this.getTeamsByID(remove.toArray(new String[L4J.MAX_PER_TEAM])));
        }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.TEAM_BY_ID);
            call.setData(copy);
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            final JsonNode node = L4J.mapper.readTree(json);
            for (final String s : copy)
            {
                final JsonNode innernode = node.get(s);
                if (innernode == null)
                {
                    teams.put(s, null);
                } else
                {
                    final Team tea = L4J.mapper.readValue(innernode.toString(), Team.class);
                    teams.put(s, tea);
                }
            }
            return teams;
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
            return null;
        } catch (final IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
