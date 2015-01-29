package test;

import java.util.Arrays;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.basic.Server;
import no.stelar7.api.l4j.dto.staticdata.summoners.SummonerSpell;
import no.stelar7.api.l4j.dto.staticdata.summoners.SummonerSpellList;

import org.junit.Test;

public class StaticTest
{
    L4J lib = new L4J("STILL HIDDEN!");

    @Test
    public void test1()
    {
        try
        {
            L4J.setRegion(Server.EUW);
            SummonerSpell champ = lib.getStaticData().getSummonerSpellData(SummonerSpellList.Spell.EXHAUST.getId(), null, null, Arrays.asList("all"));
            System.out.println(champ.getSanitizedTooltip(18));
        } catch (LibraryException e)
        {
            e.printStackTrace();
        }
    }
}
