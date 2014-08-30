package my.api.stelar7.usewith.lol.basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Locale;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Realm;
import my.api.stelar7.usewith.lol.dto.staticdata.item.Item;
import my.api.stelar7.usewith.lol.dto.staticdata.item.ItemList;
import my.api.stelar7.usewith.lol.dto.staticdata.mastery.Mastery;
import my.api.stelar7.usewith.lol.dto.staticdata.mastery.MasteryList;
import my.api.stelar7.usewith.lol.dto.staticdata.rune.Rune;
import my.api.stelar7.usewith.lol.dto.staticdata.rune.RuneList;
import my.api.stelar7.usewith.lol.dto.staticdata.summoners.SummonerSpell;
import my.api.stelar7.usewith.lol.dto.staticdata.summoners.SummonerSpellList;

public class StaticCaller
{

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
    public my.api.stelar7.usewith.lol.dto.staticdata.champion.ChampionList getChampionData(final Locale locale, final String version, final List<String> champdata, final boolean idAsKey)
    {
        final my.api.stelar7.usewith.lol.dto.staticdata.champion.ChampionList test = CacheData.getChampionDataAll().get(0);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_CHAMPION);
            call.setVerbose(true);
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
            final my.api.stelar7.usewith.lol.dto.staticdata.champion.ChampionList champ = L4J.getMapper().readValue(call.doCall(), my.api.stelar7.usewith.lol.dto.staticdata.champion.ChampionList.class);
            if ((champdata != null) && champdata.contains("all"))
            {
                CacheData.getChampionDataAll().put(0, champ);
            }
            return champ;
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
    public my.api.stelar7.usewith.lol.dto.staticdata.champion.Champion getChampionData(final long id, final Locale locale, final String version, final List<String> champdata)
    {
        final my.api.stelar7.usewith.lol.dto.staticdata.champion.Champion test = CacheData.getChampionData().get(id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_CHAMPION_ID);
            call.setVerbose(true);
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
            final my.api.stelar7.usewith.lol.dto.staticdata.champion.Champion champ = L4J.getMapper().readValue(call.doCall(), my.api.stelar7.usewith.lol.dto.staticdata.champion.Champion.class);
            if ((champdata != null) && champdata.contains("all"))
            {
                CacheData.getChampionData().put(id, champ);
            }
            return champ;
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
        final ItemList test = CacheData.getItemListData().get(0);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_ITEM);
            call.setVerbose(true);
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
            final ItemList champ = L4J.getMapper().readValue(call.doCall(), ItemList.class);
            if ((itemListData != null) && itemListData.contains("all"))
            {
                CacheData.getItemListData().put(0, champ);
            }
            return champ;
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
        final Item test = CacheData.getItemData().get(0);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_ITEM_ID);
            call.setVerbose(true);
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
            final Item champ = L4J.getMapper().readValue(call.doCall(), Item.class);
            if ((itemData != null) && itemData.contains("all"))
            {
                CacheData.getItemData().put(id, champ);
            }
            return champ;
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
        final MasteryList test = CacheData.getMasteryListData().get(0);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_MASTERY);
            call.setVerbose(true);
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
            final MasteryList champ = L4J.getMapper().readValue(call.doCall(), MasteryList.class);
            if ((champdata != null) && champdata.contains("all"))
            {
                CacheData.getMasteryListData().put(0, champ);
            }
            return champ;
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
        final Mastery test = CacheData.getMasteryData().get(id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_MASTERY_ID);
            call.setVerbose(true);
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
            final Mastery champ = L4J.getMapper().readValue(call.doCall(), Mastery.class);
            if ((data != null) && data.contains("all"))
            {
                CacheData.getMasteryData().put(id, champ);
            }
            return champ;
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
        final Realm test = CacheData.getRealm().get(0);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_REALM);
            call.setVerbose(true);
            final Realm champ = L4J.getMapper().readValue(call.doCall(), Realm.class);
            CacheData.getRealm().put(0, champ);
            return champ;
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
        final RuneList test = CacheData.getRuneListData().get(0);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_RUNE);
            call.setVerbose(true);
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
            final RuneList champ = L4J.getMapper().readValue(call.doCall(), RuneList.class);
            if ((itemData != null) && itemData.contains("all"))
            {
                CacheData.getRuneListData().put(0, champ);
            }
            return champ;
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
        final Rune test = CacheData.getRuneData().get(0);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_RUNE_ID);
            call.setVerbose(true);
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
            final Rune champ = L4J.getMapper().readValue(call.doCall(), Rune.class);
            if ((itemData != null) && itemData.contains("all"))
            {
                CacheData.getRuneData().put(id, champ);
            }
            return champ;
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
        final SummonerSpellList test = CacheData.getSummonerSpellListData().get(0);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_SUMMONER_SPELL);
            call.setVerbose(true);
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
            final SummonerSpellList champ = L4J.getMapper().readValue(call.doCall(), SummonerSpellList.class);
            if ((itemData != null) && itemData.contains("all"))
            {
                CacheData.getSummonerSpellListData().put(0, champ);
            }
            return champ;
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
        final SummonerSpell test = CacheData.getSummonerSpellData().get(id);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_SUMMONER_SPELL_ID);
            call.setVerbose(true);
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
            final SummonerSpell champ = L4J.getMapper().readValue(call.doCall(), SummonerSpell.class);
            if ((itemData != null) && itemData.contains("all"))
            {
                CacheData.getSummonerSpellData().put(id, champ);
            }
            return champ;
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
        final List<String> test = CacheData.getVersions().get(0);
        if (test != null) { return test; }
        try
        {
            final DataCall call = new DataCall();
            call.setUrlEndpoint(URLEndpoint.STATIC_VERSION);
            call.setVerbose(true);
            final List<String> champ = L4J.getMapper().readValue(call.doCall(), L4J.getMapper().getTypeFactory().constructCollectionType(List.class, String.class));
            CacheData.getVersions().put(0, champ);
            return champ;
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
