package no.stelar7.api.l4j.basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.dto.matchhistory.MatchSummary;
import no.stelar7.api.l4j.dto.staticdata.champion.Champion;
import no.stelar7.api.l4j.dto.staticdata.champion.ChampionList;
import no.stelar7.api.l4j.dto.staticdata.general.Locale;
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
import no.stelar7.api.l4j.network.GET;
import no.stelar7.api.l4j.network.HttpClient;
import no.stelar7.api.l4j.network.HttpResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.type.TypeReference;

public class StaticCaller
{

    public List<String> getLanguages(final Server region)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets seed data for ranked matches. This data is supplied by RIOT @ https://s3-us-west-1.amazonaws.com/riot-api/seed_data/matches{@code seedfile}.json
     * 
     * @param seedfile
     *            the seedfile to get (1 -> 10)
     * @return A list of matches from the seedfile.
     */
    public List<MatchSummary> getSeedMatches(int seedfile)
    {
        try
        {
            if (seedfile > 10 || seedfile < 1)
            {
                throw new IllegalArgumentException("Seedfile out of bounds!");
            }
            String url = String.format("https://s3-us-west-1.amazonaws.com/riot-api/seed_data/matches%s.json", seedfile);
            final HttpResponse response = HttpClient.execute(new GET(url));
            if (response.getStatusCode() != 200)
            {
                throw new LibraryException(response);
            }
            JsonNode node = L4J.getMapper().readTree(response.getBody()).get("matches");
            return L4J.getMapper().readValue(node, new TypeReference<List<MatchSummary>>()
            {});
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public ChampionList getChampionData(final Locale locale, final String version, final List<String> champdata, final boolean idAsKey)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets data about the game in the form of a Shard
     * 
     * @return A list of Shards
     */

    public List<Shard> getShards()
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the ShardStatus of a shard from its slug
     * 
     * @param slug
     *            the region to check
     * @return
     */
    public ShardStatus getShardStatus(String slug)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public Champion getChampionData(final long id, final Locale locale, final String version, final List<String> champdata)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public ItemList getItemData(final Locale locale, final String version, final List<String> itemListData)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public Item getItemData(final long id, final Locale locale, final String version, final List<String> itemData)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public MasteryList getMasteryData(final Locale locale, final String version, final List<String> champdata)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public Mastery getMasteryData(final long id, final Locale locale, final String version, final List<String> data)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets static data about the realm
     */
    public Realm getRealm()
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public RuneList getRuneData(final Locale locale, final String version, final List<String> itemData)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public Rune getRuneData(final long id, final Locale locale, final String version, final List<String> itemData)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public SummonerSpellList getSummonerSpellData(final Locale locale, final String version, final List<String> itemData, final boolean idAsKey)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
     */
    public SummonerSpell getSummonerSpellData(final long id, final Locale locale, final String version, final List<String> itemData)
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets static data about the versions
     */
    public List<String> getVersions()
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
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
