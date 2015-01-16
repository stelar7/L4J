package no.stelar7.api.l4j.dto.staticdata.summoners;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SummonerSpellList implements Serializable
{
    public enum Spell
    {
        BARRIER(21),
        CLAIRVOYANCE(2),
        CLARITY(13),
        CLEANSE(1),
        EXHAUST(3),
        FLASH(4),
        GARRISON(17),
        GHOST(6),
        HEAL(7),
        IGNITE(14),
        REVIVE(10),
        SMITE(11),
        TELEPORT(12);

        @Getter
        int id;

        private Spell(final int id)
        {
            this.id = id;
        }
    }

    Map<String, SummonerSpell> data;
    String                     type;
    String                     version;
}
