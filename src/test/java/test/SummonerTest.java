package test;

import java.util.List;

import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.Server;
import my.api.stelar7.usewith.lol.dto.masteries.MasteryPage;
import my.api.stelar7.usewith.lol.dto.runes.RunePage;
import my.api.stelar7.usewith.lol.dto.summoner.Summoner;
import my.api.stelar7.usewith.lol.dto.team.Team;

import org.junit.Assert;
import org.junit.Test;

public class SummonerTest
{

    L4J lib = new L4J("THIS IS MY API KEY");

    @Test
    public void test1()
    {
        L4J.setRegion(Server.EUW);
        List<Summoner> friends = lib.getSummonersByName("stelar7", "henriko950", "vibbsen");
        Summoner stelar7 = friends.get(0);
        Summoner henriko950 = friends.get(1);
        Summoner vibbsen = friends.get(2);
        Assert.assertNotNull(stelar7);
        Assert.assertNotNull(henriko950);
        Assert.assertNotNull(vibbsen);
        Assert.assertNotEquals(stelar7, henriko950);
        Assert.assertNotEquals(henriko950, vibbsen);
    }

    @Test
    public void test2()
    {
        Summoner self = lib.getSummonersByName("stelar7").get(0);
        List<RunePage> runes = self.getRunePages();
        List<MasteryPage> masteries = self.getMasteryPages();
        runes.stream().forEach(System.out::println);
        masteries.stream().forEach(System.out::println);
        Assert.assertNotNull(runes);
        Assert.assertNotNull(masteries);
        Assert.assertNotEquals(runes, masteries);
    }

    @Test
    public void test3()
    {
        Summoner self = lib.getSummonersByName("stelar7").get(0);
        List<Team> teams = self.getTeams();
        teams.stream().forEach(System.out::println);
        Assert.assertNotNull(teams);

    }
}
