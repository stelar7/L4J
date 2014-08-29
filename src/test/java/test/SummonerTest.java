package test;

import java.util.List;

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
        List<Summoner> a = lib.getSummonersByName("stelar7", "henriko950", "vibbsen");
        lib.getLeagueBySummoners(true, a.get(0).getId(), a.get(1).getId(), a.get(2).getId());
        lib.getLeagueBySummoners(false, a.get(0).getId(), a.get(1).getId(), a.get(2).getId());
        System.out.println(lib.getLeagueBySummoners(true, a.get(0).getId(), a.get(1).getId(), a.get(2).getId()));
        System.out.println(lib.getLeagueBySummoners(false, a.get(0).getId(), a.get(1).getId(), a.get(2).getId()));
        System.out.println(lib.getLeagueBySummoners(false, a.get(0).getId()));
        System.out.println(lib.getLeagueBySummoners(false, a.get(1).getId()));
        System.out.println(lib.getLeagueBySummoners(false, a.get(2).getId()));
        }
}
