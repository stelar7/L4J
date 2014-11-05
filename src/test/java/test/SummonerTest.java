package test;

import java.util.Map;

import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.Server;
import my.api.stelar7.usewith.lol.dto.summoner.Summoner;

import org.junit.Test;

public class SummonerTest
{

    L4J lib = new L4J("THIS IS MY API KEY");

    @Test
    public void test1()
    {
        L4J.setRegion(Server.EUW);
        Map<String, Summoner> a = lib.getSummonersByName("stelar7", "henriko950", "vibbsdsfdsfdsfdsfdsfdsen");
        System.out.println(lib.getLeagueBySummoners(false, a.get("stelar7").getId()));
        System.out.println(lib.getLeagueBySummoners(true, a.get("henriko950").getId()));
    }
}
