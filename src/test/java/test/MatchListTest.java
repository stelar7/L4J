package test;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.basic.Server;
import no.stelar7.api.l4j.dto.matchlist.MatchList;
import no.stelar7.api.l4j.dto.matchlist.MatchReference;
import no.stelar7.api.l4j.dto.summoner.Summoner;

public class MatchListTest
{
    L4J lib = new L4J("YOUR API KEY HERE");

    @Test()
    public void test1()
    {
        try
        {
            // set region (defaults to EUW)
            L4J.setRegion(Server.EUW);

            // set it to output the calls to the logger
            L4J.setVerbose(true);

            // load a summoner to fetch data from
            Summoner s = lib.getSummonersByName("stelar7").get("stelar7");

            // load the Summoners MatchList
            MatchList list = s.getMatchList(null, null, null, null, null, null);

            // get the MatchReferences
            List<MatchReference> matches = list.getMatches();

            // create a HashMap to store data about the lanes
            HashMap<String, Integer> lane = new HashMap<String, Integer>();

            // for every MatchReference in matches
            for (MatchReference match : matches)
            {
                // get current value
                Integer current = lane.get(match.getLane());

                // if its a valid value
                if (current != null)
                {
                    // add 1 to the counter
                    lane.put(match.getLane(), lane.get(match.getLane()) + 1);
                } else
                {
                    // else, set it to 1
                    lane.put(match.getLane(), 1);
                }
            }

            // print the result
            System.out.println(lane.toString());
            
            // {TOP=135, JUNGLE=86, MID=152, BOTTOM=265}
            
        } catch (LibraryException e)
        {
            Assert.assertEquals(e.getMessage(), "(NOT_FOUND) No data found");
        }
    }
}
