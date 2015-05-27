package test;

import java.util.Arrays;
import java.util.Map;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.dto.staticdata.item.ItemList;
import no.stelar7.api.l4j.dto.staticdata.rune.RuneList;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest
{

    L4J lib = new L4J("API KEY GOES HERE");

    @Test
    public void test1()
    {
        try
        {
            // Load all the items and runes from the API
            ItemList items = lib.getStaticData().getItemData(null, null, Arrays.asList("all"));
            RuneList runes = lib.getStaticData().getRuneData(null, null, Arrays.asList("all"));

            // Print their stats
            for (String s : items.getData().keySet())
            {
                Map<String, Double> stats = items.getData().get(s).getStats();
                System.out.format("%s=%s%n", items.getData().get(s).getName(), stats);
            }

            for (String s : runes.getData().keySet())
            {
                Map<String, Double> stats = runes.getData().get(s).getStats();
                System.out.format("%s=%s&n", runes.getData().get(s).getName(), stats);
            }

        } catch (LibraryException e)
        {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
}
