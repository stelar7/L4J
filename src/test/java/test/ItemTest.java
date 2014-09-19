package test;

import java.util.Arrays;
import java.util.Map;

import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.Server;
import my.api.stelar7.usewith.lol.dto.staticdata.item.ItemList;
import my.api.stelar7.usewith.lol.dto.staticdata.rune.RuneList;

import org.junit.Test;

public class ItemTest
{

    L4J lib = new L4J("THIS IS STILL MY API KEY");

    @Test
    public void test1()
    {
        L4J.setRegion(Server.EUW);
        ItemList items = lib.getStaticData().getItemData(null, null, Arrays.asList("all"));
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
    }
}
