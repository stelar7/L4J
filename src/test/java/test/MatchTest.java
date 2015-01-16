package test;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.Server;
import no.stelar7.api.l4j.dto.match.Event;
import no.stelar7.api.l4j.dto.match.Frame;
import no.stelar7.api.l4j.dto.match.MatchDetail;

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
    }
}
