package no.stelar7.api.l4j.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.dto.matchhistory.MatchSummary;
import no.stelar7.api.l4j.dto.staticdata.champion.Champion;
import no.stelar7.api.l4j.dto.staticdata.champion.ChampionList;
import no.stelar7.api.l4j.dto.staticdata.general.Realm;
import no.stelar7.api.l4j.dto.staticdata.item.Item;
import no.stelar7.api.l4j.dto.staticdata.item.ItemList;
import no.stelar7.api.l4j.dto.staticdata.mastery.Mastery;
import no.stelar7.api.l4j.dto.staticdata.mastery.MasteryList;
import no.stelar7.api.l4j.dto.staticdata.rune.Rune;
import no.stelar7.api.l4j.dto.staticdata.rune.RuneList;
import no.stelar7.api.l4j.dto.staticdata.shard.Shard;
import no.stelar7.api.l4j.dto.staticdata.shard.ShardStatus;
import no.stelar7.api.l4j.dto.staticdata.summoners.SummonerSpell;
import no.stelar7.api.l4j.dto.staticdata.summoners.SummonerSpellList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class StaticCaller
{

    /**
     * Gets a list of the supported languages for that region
     * 
     * @param region
     *            the region to get languages from
     * 
     * @return list of supported languages
     * @throws LibraryException
     */
    public List<String> getLanguages(final Server region) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_LANGUAGES);

            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    this.put("region", region.name().toLowerCase());
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, new TypeReference<List<String>>()
            {});
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
     * Gets a map of Strings that map to propperly formatted Strings
     * 
     * @param locale
     *            the locale to use, may be null
     * @param version
     *            the version to use, may be null
     * @return map of Strings that map to propperly formatted Strings
     * @throws LibraryException
     */
    public Map<String, String> getLanguageStrings(final String locale, final String version) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_LANGUAGE_STRINGS);

            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }

                    if (version != null)
                    {
                        this.put("version", version);
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            JsonNode node = L4J.getMapper().readTree(json).get("data");
            return L4J.getMapper().readValue(node.toString(), new TypeReference<Map<String, String>>()
            {});
        } catch (final JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<no.stelar7.api.l4j.dto.staticdata.map.Map> getMapData(final String locale, final String version) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_MAP);

            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }

                    if (version != null)
                    {
                        this.put("version", version);
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            List<no.stelar7.api.l4j.dto.staticdata.map.Map> maps = new ArrayList<no.stelar7.api.l4j.dto.staticdata.map.Map>();
            JsonNode node = L4J.getMapper().readTree(json).get("data");
            Iterator<JsonNode> iter = node.elements();
            while (iter.hasNext())
            {
                JsonNode dta = iter.next();
                maps.add(L4J.getMapper().readValue(dta.toString(), no.stelar7.api.l4j.dto.staticdata.map.Map.class));
            }
            return maps;
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
     * Gets seed data for ranked matches. This data is supplied by RIOT @ https://s3-us-west-1.amazonaws.com/riot-api/seed_data/matches{@code seedfile}.json
     * 
     * @param seedfile
     *            the seedfile to get (1 -> 10)
     * @return A list of matches from the seedfile.
     * @throws LibraryException
     */
    public List<MatchSummary> getSeedMatches(int seedfile) throws LibraryException
    {
        try
        {
            if (seedfile > 10 || seedfile < 1)
            {
                throw new IllegalArgumentException("Seedfile out of bounds!");
            }
            String URL = String.format("https://s3-us-west-1.amazonaws.com/riot-api/seed_data/matches%s.json", seedfile);
            HttpURLConnection con = (HttpURLConnection) new URL(URL.toString()).openConnection();
            if (con.getResponseCode() != 200)
            {
                throw new LibraryException(con.getResponseCode(), con.getHeaderFieldInt("Retry-After", 0));
            }
            StringBuilder data = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                data.append(inputLine);
            }
            in.close();
            JsonNode node = L4J.getMapper().readTree(data.toString()).get("matches");
            return L4J.getMapper().readValue(node.toString(), new TypeReference<List<MatchSummary>>()
            {});
        } catch (JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets static data about all champions
     *
     * @param id
     *            the id of the champion to get data about
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param champdata
     *            the data to return. To get all the data use "all". default is id, key, name, and title (can be null)
     * @throws LibraryException
     */
    public ChampionList getChampionData(final String locale, final String version, final List<String> champdata, final boolean idAsKey) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_CHAMPION);

            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    if (champdata != null)
                    {
                        this.put("champData", champdata.toString().substring(1, champdata.toString().lastIndexOf("]")));
                    }
                    this.put("dataById", idAsKey);
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, no.stelar7.api.l4j.dto.staticdata.champion.ChampionList.class);
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
     * Gets data about the game in the form of a Shard
     * 
     * @return A list of Shards
     * @throws LibraryException
     */

    public List<Shard> getShards() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_SHARD);
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, new TypeReference<List<Shard>>()
            {});
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
     * Gets the ShardStatus of a shard from its slug
     * 
     * @param slug
     *            the region to check
     * @return
     * @throws LibraryException
     */
    public ShardStatus getShardStatus(String slug) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_SHARD_REGION);
            call.setData(Arrays.asList(slug.toLowerCase()));
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, ShardStatus.class);
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
     * Gets static data about a champion
     *
     * @param id
     *            the id of the champion to get data about
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param champdata
     *            the data to return. To get all the data use "all". default is id, key, name, and title (can be null)
     * @throws LibraryException
     */
    public Champion getChampionData(final long id, final String locale, final String version, final List<String> champdata) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_CHAMPION_ID);
            call.setData(Arrays.asList(id));
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    if (champdata != null)
                    {
                        this.put("champData", champdata.toString().substring(1, champdata.toString().lastIndexOf("]")));
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, no.stelar7.api.l4j.dto.staticdata.champion.Champion.class);
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
     * Gets static data about all items
     *
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param itemListData
     *            the data to return. To get all the data use "all". default is type, version, basic, data, id, name, plaintext, group and description (can be null)
     * @throws LibraryException
     */
    public ItemList getItemData(final String locale, final String version, final List<String> itemListData) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_ITEM);
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    if (itemListData != null)
                    {
                        this.put("itemListData", itemListData.toString().substring(1, itemListData.toString().lastIndexOf("]")));
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, ItemList.class);
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
     * Gets static data about a item
     *
     * @param id
     *            the id of the item to get data about
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param itemListData
     *            the data to return. To get all the data use "all". default is type, version, basic, data, id, name, plaintext, group and description (can be null)
     * @throws LibraryException
     */
    public Item getItemData(final long id, final String locale, final String version, final List<String> itemData) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_ITEM_ID);
            call.setData(Arrays.asList(id));
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    if (itemData != null)
                    {
                        this.put("itemData", itemData.toString().substring(1, itemData.toString().lastIndexOf("]")));
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, Item.class);
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
     * Gets static data about all masteies
     *
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param champdata
     *            the data to return. To get all the data use "all". default is id, key, name, and title (can be null)
     * @throws LibraryException
     */
    public MasteryList getMasteryData(final String locale, final String version, final List<String> champdata) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_MASTERY);
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    if (champdata != null)
                    {
                        this.put("masteryListData", champdata.toString().substring(1, champdata.toString().lastIndexOf("]")));
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, MasteryList.class);
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
     * Gets static data about a mastery
     *
     * @param id
     *            the id of the mastery to get data about
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param data
     *            the data to return. To get all the data use "all". default is type, version, basic, data, id, name, plaintext, group and description (can be null)
     * @throws LibraryException
     */
    public Mastery getMasteryData(final long id, final String locale, final String version, final List<String> data) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_MASTERY_ID);
            call.setData(Arrays.asList(id));
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    if (data != null)
                    {
                        this.put("masteryData", data.toString().substring(1, data.toString().lastIndexOf("]")));
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, Mastery.class);
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
     * Gets static data about the realm
     * 
     * @throws LibraryException
     */
    public Realm getRealm() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_REALM);

            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, Realm.class);
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
     * Gets static data about all runes
     *
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param itemData
     *            the data to return. To get all the data use "all". default is type, version, basic, data, id, name, plaintext, group and description (can be null)
     * @throws LibraryException
     */
    public RuneList getRuneData(final String locale, final String version, final List<String> itemData) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_RUNE);
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    if (itemData != null)
                    {
                        this.put("runeListData", itemData.toString().substring(1, itemData.toString().lastIndexOf("]")));
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, RuneList.class);
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
     * Gets static data about a rune
     *
     * @param id
     *            the id of the rune to get data about
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param itemData
     *            the data to return. To get all the data use "all". default is type, version, basic, data, id, name, plaintext, group and description (can be null)
     * @throws LibraryException
     */
    public Rune getRuneData(final long id, final String locale, final String version, final List<String> itemData) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_RUNE_ID);
            call.setData(Arrays.asList(id));
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    if (itemData != null)
                    {
                        this.put("runeData", itemData.toString().substring(1, itemData.toString().lastIndexOf("]")));
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, Rune.class);
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
     * Gets static data about all summoner spells
     *
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param itemData
     *            the data to return. To get all the data use "all". default is type, version, basic, data, id, name, plaintext, group and description (can be null)
     * @param idAsKey
     *            use the id as the key instead of the normal key
     * @throws LibraryException
     */
    public SummonerSpellList getSummonerSpellData(final String locale, final String version, final List<String> itemData, final boolean idAsKey) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_SUMMONER_SPELL);
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    this.put("dataById", idAsKey);
                    if (itemData != null)
                    {
                        this.put("spellData", itemData.toString().substring(1, itemData.toString().lastIndexOf("]")));
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, SummonerSpellList.class);
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
     * Gets static data about a summoner spell
     *
     * @param id
     *            the id to find data from
     * @param locale
     *            the locale to get data in (can be null)
     * @param version
     *            the version to get data from (can be null)
     * @param itemData
     *            the data to return. To get all the data use "all". default is type, version, basic, data, id, name, plaintext, group and description (can be null)
     * @param idAsKey
     *            use the id as the key instead of the normal key
     * @throws LibraryException
     */
    public SummonerSpell getSummonerSpellData(final long id, final String locale, final String version, final List<String> itemData) throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_SUMMONER_SPELL_ID);
            call.setData(Arrays.asList(id));
            call.setUrlParams(new HashMap<String, Object>()
            {
                {
                    if (locale != null)
                    {
                        this.put("locale", locale);
                    }
                    if (version != null)
                    {
                        this.put("version", version);
                    }
                    if (itemData != null)
                    {
                        this.put("spellData", itemData.toString().substring(1, itemData.toString().lastIndexOf("]")));
                    }
                }
            });
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, SummonerSpell.class);
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
     * Gets static data about the versions
     * 
     * @throws LibraryException
     */
    public List<String> getVersions() throws LibraryException
    {
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_VERSION);
            final String json = call.doCall();
            if (call.hasError())
            {
                throw call.getErrorData();
            }
            return L4J.getMapper().readValue(json, L4J.getMapper().getTypeFactory().constructCollectionType(List.class, String.class));
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
