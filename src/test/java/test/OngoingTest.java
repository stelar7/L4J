package test;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.dto.current_game.PlatformId;

import org.junit.Assert;
import org.junit.Test;

public class OngoingTest
{

    L4J lib = new L4J("API KEY GOES HERE");

    @Test()
    public void test1()
    {
        try
        {
            // Load current game info from the API 
            System.out.println(lib.getCurrentGameInfo(PlatformId.EUW1, lib.getSummonersByName("stelar7").get("stelar7").getId()));
        } catch (LibraryException e)
        {
            Assert.assertEquals(e.getMessage(), "(NOT_FOUND) No data found");
        }
    }

}
