package no.stelar7.api.l4j.dto.summoner;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.DataCall;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.basic.Server;
import no.stelar7.api.l4j.basic.URLEndpoint;
import no.stelar7.api.l4j.dto.game.RecentGames;
import no.stelar7.api.l4j.dto.general.Season;
import no.stelar7.api.l4j.dto.league.League;
import no.stelar7.api.l4j.dto.masteries.MasteryPage;
import no.stelar7.api.l4j.dto.matchhistory.PlayerHistory;
import no.stelar7.api.l4j.dto.runes.RunePage;
import no.stelar7.api.l4j.dto.stats.RankedStats;
import no.stelar7.api.l4j.dto.stats.StatSummary;
import no.stelar7.api.l4j.dto.team.Team;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

@Getter
@ToString
public class Summoner implements Serializable
{
    Long   id;
    String name;
    int    profileIconId;
    Long   revisionDate;
    Long   summonerLevel;
    Server region;

    public List<League> getFullLeague() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER_FULL);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<MasteryPage> getMasteryPages() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_MASTERIES);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().convertValue(L4J.getMapper().readTree(json).get("" + this.id).get("pages"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, MasteryPage.class));
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
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
     * @throws LibraryException 
     */
    public PlayerHistory getMatchHistory(final List<Integer> champids, final List<String> rankedQueues, final Integer beginIndex, final Integer endIndex) throws LibraryException
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
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            if (json.equals("{}"))
            {
                return null;
            }
            return L4J.getMapper().readValue(json, PlayerHistory.class);
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public RankedStats getRankedStats(final Season season) throws LibraryException
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
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, RankedStats.class);
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public RecentGames getRecentGames() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.RECENT_GAMES);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, RecentGames.class);
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<RunePage> getRunePages() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.SUMMONER_RUNES);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("" + this.id).get("pages"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, RunePage.class));
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<League> getSelfLeague() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.LEAGUE_BY_SUMMONER);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().convertValue(L4J.getMapper().readTree(json).get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, League.class));
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<StatSummary> getStatSummary(final Season season) throws LibraryException
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
                        put("season", season.toString());
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("playerStatSummaries"), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, StatSummary.class));
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Team> getTeams() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.TEAM_BY_SUMMONER);
            call.setData(Arrays.asList(this.id));
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            final JsonNode node = L4J.getMapper().readTree(json);
            return L4J.getMapper().convertValue(node.get("" + this.id), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, Team.class));
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}