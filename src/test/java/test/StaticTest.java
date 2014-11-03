package test;

import java.util.Arrays;

import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.Server;
import my.api.stelar7.usewith.lol.dto.staticdata.summoners.SummonerSpell;

import org.junit.Test;

public class StaticTest
{
    L4J lib = new L4J("STILL HIDDEN!");

    @Test
    public void test1()
    {
        L4J.setRegion(Server.EUW);
        SummonerSpell champ = lib.getStaticData().getSummonerSpellData(7, null, null, Arrays.asList("all"));
        System.out.println(champ.getSanitizedTooltip(18));
    }
}
