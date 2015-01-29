package test;

import java.util.Map;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.basic.Server;
import no.stelar7.api.l4j.dto.summoner.Summoner;

import org.junit.Test;

public class SummonerTest
{

    L4J lib = new L4J("THIS IS MY API KEY");

    @Test
    public void test1()
    {
        try
        {
            L4J.setRegion(Server.EUW);
            Map<String, Summoner> a = lib.getSummonersByName("stelar7", "henriko950", "vibbsdsfdsfdsfdsfdsfdsen");
            System.out.println(lib.getLeagueBySummoners(false, a.get("stelar7").getId()));
            System.out.println(lib.getLeagueBySummoners(true, a.get("henriko950").getId()));
        } catch (LibraryException e)
        {
            e.printStackTrace();
        }
    }
}
