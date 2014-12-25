package test;

import java.util.Arrays;

import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.basic.Server;
import my.api.stelar7.usewith.lol.dto.general.SubType;
import my.api.stelar7.usewith.lol.dto.match.Event;
import my.api.stelar7.usewith.lol.dto.match.Frame;
import my.api.stelar7.usewith.lol.dto.match.MatchDetail;

import org.junit.Test;

public class MatchTest
{
    L4J lib = new L4J("API KEY GOES HERE!");

    @Test
    public void test1()
    {
        L4J.setRegion(Server.EUW);
        L4J.setVerbose(true);
        MatchDetail match = lib.getMatch(1843818315, true);
        for (Frame f : match.getTimeline().getFrames())
        {
            if (f.getEvents() == null) continue;
            for (Event e : f.getEvents())
            {
                if (e.getParticipantId() == 8)
                {
                    if (e.getEventType().contains("ITEM_PURCHASE"))
                    {
                        System.out.format("%s %s%n", e.getEventType(), lib.getStaticData().getItemData(e.getItemId(), null, null, null).getName());
                    }
                }
            }
        }
        lib.getSummonersByName("stelar7").get("stelar7").getMatchHistory(null, Arrays.asList(SubType.RANKED_TEAM_5x5), null, null);
    }
}
