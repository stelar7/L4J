package test;

import java.util.Arrays;
import java.util.Map;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.basic.Server;
import no.stelar7.api.l4j.dto.staticdata.item.ItemList;
import no.stelar7.api.l4j.dto.staticdata.rune.RuneList;

import org.junit.Test;

public class ItemTest
{

    L4J lib = new L4J("THIS IS STILL MY API KEY");

    @Test
    public void test1()
    {
        try
        {
            ItemList items = lib.getStaticData().getItemData(null, null, Arrays.asList("all"));
            L4J.setRegion(Server.EUW);
            RuneList runes = lib.getStaticData().getRuneData(null, null, Arrays.asList("all"));
            for (String s : items.getData().keySet())
            {
                Map<String, Double> stats = items.getData().get(s).getStats();
                System.out.println(stats);
            }
            for (String s : runes.getData().keySet())
            {
                Map<String, Double> stats = runes.getData().get(s).getStats();
                System.out.println(stats);
            }
        } catch (LibraryException e)
        {
            e.printStackTrace();
        }
    }
}
