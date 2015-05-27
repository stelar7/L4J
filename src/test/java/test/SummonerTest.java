package test;

import java.util.List;
import java.util.Map;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.dto.league.League;
import no.stelar7.api.l4j.dto.league.LeagueType;
import no.stelar7.api.l4j.dto.summoner.Summoner;

import org.junit.Assert;
import org.junit.Test;

public class SummonerTest
{

    L4J lib = new L4J("API KEY GOES HERE");
    
    @Test
    public void test1()
    {
        try
        {
            // Load summoners from the API
            Map<String, Summoner> summoners = lib.getSummonersByName("stelar7", "henriko950", "vibbsen");

            Summoner stelar = summoners.get("stelar7");
            Summoner henriko = summoners.get("henriko950");
            Summoner vibbsen = summoners.get("vibbsen");

            // Load leagues from the API
            Map<Long, List<League>> leagues = lib.getLeagueBySummoners(false, stelar.getId(), henriko.getId(), vibbsen.getId());

            for (Long summonerid : leagues.keySet())
            {
                List<League> summonerLeagues = leagues.get(summonerid);

                League ranked = null;
                String name = null; 

                // find the ranked 5x5 league 
                for (League l : summonerLeagues)
                {
                    if (l.getQueue() == LeagueType.RANKED_SOLO_5x5)
                    {
                        ranked = l;
                        break;
                    }
                }
                
                // find the summoner name
                for (Summoner summoner : summoners.values()) {
                    if (summoner.getId() == summonerid) {
                        name = summoner.getName();
                        break;
                    }
                }
                

                // with the first parameter of getLeagueBySummoners set to false, it only returns the current player entry, so it is always at index 0
                System.out.format("%s: %s %s%n", name, ranked.getTier(), ranked.getEntries().get(0).getDivision());
            }

        } catch (LibraryException e)
        {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
}
