package my.api.stelar7.usewith.lol.basic;

import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import my.api.stelar7.usewith.lol.dto.champion.ChampionList;
import my.api.stelar7.usewith.lol.dto.game.RecentGames;
import my.api.stelar7.usewith.lol.dto.league.League;
import my.api.stelar7.usewith.lol.dto.league.LeagueType;
import my.api.stelar7.usewith.lol.dto.masteries.MasteryPage;
import my.api.stelar7.usewith.lol.dto.match.MatchDetail;
import my.api.stelar7.usewith.lol.dto.matchhistory.PlayerHistory;
import my.api.stelar7.usewith.lol.dto.runes.RunePage;
import my.api.stelar7.usewith.lol.dto.staticdata.champion.Champion;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Realm;
import my.api.stelar7.usewith.lol.dto.staticdata.item.Item;
import my.api.stelar7.usewith.lol.dto.staticdata.item.ItemList;
import my.api.stelar7.usewith.lol.dto.staticdata.mastery.Mastery;
import my.api.stelar7.usewith.lol.dto.staticdata.mastery.MasteryList;
import my.api.stelar7.usewith.lol.dto.staticdata.rune.Rune;
import my.api.stelar7.usewith.lol.dto.staticdata.rune.RuneList;
import my.api.stelar7.usewith.lol.dto.staticdata.summoners.SummonerSpell;
import my.api.stelar7.usewith.lol.dto.staticdata.summoners.SummonerSpellList;
import my.api.stelar7.usewith.lol.dto.stats.RankedStats;
import my.api.stelar7.usewith.lol.dto.stats.StatSummary;
import my.api.stelar7.usewith.lol.dto.summoner.Summoner;
import my.api.stelar7.usewith.lol.dto.team.Team;

public class CacheData
{
    @Getter
    static HashMap<Integer, ChampionList>                                                    championList          = new HashMap<>();
    @Getter
    static HashMap<String, Summoner>                                                         summoners             = new HashMap<>();
    @Getter
    static HashMap<Long, Summoner>                                                           summonersId           = new HashMap<>();
    @Getter
    static HashMap<Long, RecentGames>                                                        games                 = new HashMap<>();
    @Getter
    static HashMap<Long, List<RunePage>>                                                     runePages             = new HashMap<>();
    @Getter
    static HashMap<Long, List<MasteryPage>>                                                  masteryPages          = new HashMap<>();
    @Getter
    static HashMap<Long, List<Team>>                                                         teamList              = new HashMap<>();
    @Getter
    static HashMap<String, Team>                                                             teams                 = new HashMap<>();
    @Getter
    static HashMap<Object, List<League>>                                                     leaguesFull           = new HashMap<>();
    @Getter
    static HashMap<Object, List<League>>                                                     leaguesSelf           = new HashMap<>();
    @Getter
    static HashMap<LeagueType, League>                                                       challengerLeague      = new HashMap<>();
    @Getter
    static HashMap<Long, MatchDetail>                                                        matchDetails          = new HashMap<>();
    @Getter
    static HashMap<Long, PlayerHistory>                                                      playerHistory         = new HashMap<>();
    @Getter
    static HashMap<Long, RankedStats>                                                        rankedStats           = new HashMap<>();
    @Getter
    static HashMap<Long, List<StatSummary>>                                                  statSummary           = new HashMap<>();
    @Getter
    static HashMap<Long, Champion>                                                           championData          = new HashMap<>();
    @Getter
    static HashMap<Integer, my.api.stelar7.usewith.lol.dto.staticdata.champion.ChampionList> championDataAll       = new HashMap<>();
    @Getter
    static HashMap<Integer, ItemList>                                                        itemListData          = new HashMap<>(); ;
    @Getter
    static HashMap<Long, Item>                                                               itemData              = new HashMap<>();
    @Getter
    static HashMap<Integer, MasteryList>                                                     masteryListData       = new HashMap<>(); ;
    @Getter
    static HashMap<Long, Mastery>                                                            masteryData           = new HashMap<>();
    @Getter
    static HashMap<Integer, List<String>>                                                    versions              = new HashMap<>();
    @Getter
    static HashMap<Integer, Realm>                                                           realm                 = new HashMap<>();
    @Getter
    static HashMap<Integer, RuneList>                                                        runeListData          = new HashMap<>();
    @Getter
    static HashMap<Long, Rune>                                                               runeData              = new HashMap<>();
    @Getter
    static HashMap<Integer, SummonerSpellList>                                               summonerSpellListData = new HashMap<>();
    @Getter
    static HashMap<Long, SummonerSpell>                                                      summonerSpellData     = new HashMap<>();
}
