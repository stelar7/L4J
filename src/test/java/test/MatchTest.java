package test;

import java.util.Arrays;
import java.util.Collection;

import no.stelar7.api.l4j.L4J;
import no.stelar7.api.l4j.basic.LibraryException;
import no.stelar7.api.l4j.dto.match.Event;
import no.stelar7.api.l4j.dto.match.EventType;
import no.stelar7.api.l4j.dto.match.Frame;
import no.stelar7.api.l4j.dto.match.MatchDetail;
import no.stelar7.api.l4j.dto.staticdata.item.Item;

import org.junit.Assert;
import org.junit.Test;

public class MatchTest
{
    L4J lib = new L4J("API KEY GOES HERE");

    @Test
    public void test1()
    {
        try
        {
            // get the match with the timeline
            MatchDetail match = lib.getMatch(1843818315, true);
            
            // get all the items
            Collection<Item> list = lib.getStaticData().getItemData(null, null, Arrays.asList("all")).getData().values();

            for (Frame f : match.getTimeline().getFrames())
            {
                // some frames are without events so we skip them
                if (f.getEvents() == null)
                {
                    continue;
                }

                // if player with id 8 bougth an item, print it.
                for (Event e : f.getEvents())
                {
                    if (e.getParticipantId() == 8)
                    {
                        if (e.getEventType() == EventType.ITEM_PURCHASED)
                        {
                            System.out.format("%s %s%n", e.getEventType(), getItem(list, e.getItemId()).getName());
                        }
                    }
                }
            }
        } catch (LibraryException e1)
        {
            Assert.assertTrue(false);
            e1.printStackTrace();
        }
    }

    // Filter the collection to find the correct item
    private Item getItem(Collection<Item> items, int id)
    {
        for (Item i : items)
        {
            if (i.getId() == id)
            {
                return i;
            }
        }
        return null;
    }
}
