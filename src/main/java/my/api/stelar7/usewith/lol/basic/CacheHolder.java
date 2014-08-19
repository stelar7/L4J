package my.api.stelar7.usewith.lol.basic;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheHolder
{
    @Getter
    static Cache<Integer, ChampionList>                                                    championList          = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<String, Summoner>                                                         summoners             = CacheBuilder.newBuilder().build();
    @Getter
    static Cache<Long, RecentGames>                                                        games                 = CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.MINUTES).build();
    @Getter
    static Cache<Long, List<RunePage>>                                                     runePages             = CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.MINUTES).build();
    @Getter
    static Cache<Long, List<MasteryPage>>                                                  masteryPages          = CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.MINUTES).build();
    @Getter
    static Cache<Long, List<Team>>                                                         teamList              = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<String, Team>                                                             teams                 = CacheBuilder.newBuilder().build();
    @Getter
    static Cache<Object, League>                                                           leaguesFull           = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Object, League>                                                           leaguesSelf           = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<LeagueType, League>                                                       challengerLeague      = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Long, MatchDetail>                                                        matchDetails          = CacheBuilder.newBuilder().build();
    @Getter
    static Cache<Long, PlayerHistory>                                                      playerHistory         = CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.MINUTES).build();
    @Getter
    static Cache<Long, RankedStats>                                                        rankedStats           = CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.MINUTES).build();
    @Getter
    static Cache<Long, List<StatSummary>>                                                  statSummary           = CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.MINUTES).build();
    @Getter
    static Cache<Long, Champion>                                                           championData          = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Integer, my.api.stelar7.usewith.lol.dto.staticdata.champion.ChampionList> championDataAll       = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Integer, ItemList>                                                        itemListData          = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Long, Item>                                                               itemData              = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Integer, MasteryList>                                                     masteryListData       = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Long, Mastery>                                                            masteryData           = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Integer, List<String>>                                                    versions              = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Integer, Realm>                                                           realm                 = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Integer, RuneList>                                                        runeListData          = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Long, Rune>                                                               runeData              = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Integer, SummonerSpellList>                                               summonerSpellListData = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
    @Getter
    static Cache<Long, SummonerSpell>                                                      summonerSpellData     = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();
}
