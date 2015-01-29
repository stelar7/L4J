package test;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.basic.Server;
import no.stelar7.api.l4j.dto.current_game.PlatformId;

import org.junit.Test;

public class OngoingTest
{

    L4J lib = new L4J("API KEY");

    @Test
    public void test1()
    {
        try
        {
            L4J.setRegion(Server.EUW);
            L4J.setVerbose(true);
            System.out.println(lib.getCurrentGameInfo(PlatformId.EUW1, lib.getSummonersByName("stelar7").get("stelar7").getId()));
            System.out.println(lib.getFeaturedGames());
        } catch (LibraryException e)
        {
            e.printStackTrace();
        }
    }

}
