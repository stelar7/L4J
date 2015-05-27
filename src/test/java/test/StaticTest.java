package test;

import java.util.Arrays;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.dto.staticdata.summoners.SummonerSpell;
import no.stelar7.api.l4j.dto.staticdata.summoners.SummonerSpellList;

import org.junit.Assert;
import org.junit.Test;

public class StaticTest
{
    L4J lib = new L4J("API KEY GOES HERE");

    @Test
    public void test1()
    {
        try
        {
            // Load data about Exhaust from the API
            // THIS WILL CONTAIN f2, f3, and f4 because of an error in the static data API..
            SummonerSpell champ = lib.getStaticData().getSummonerSpellData(SummonerSpellList.Spell.EXHAUST.getId(), null, null, Arrays.asList("all"));
            System.out.println(champ.getSanitizedTooltip(18));
        } catch (LibraryException e)
        {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
}
