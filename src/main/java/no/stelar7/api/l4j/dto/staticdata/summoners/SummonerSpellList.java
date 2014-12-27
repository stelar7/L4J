package no.stelar7.api.l4j.dto.staticdata.summoners;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SummonerSpellList
{
    Map<String, SummonerSpell> data;
    String                     type;
    String                     version;

    public enum Spell
    {
        EXHAUST(3),
        BARRIER(21),
        CLAIRVOYANCE(2),
        REVIVE(10),
        CLEANSE(1),
        HEAL(7),
        GHOST(6),
        FLASH(4),
        GARRISON(17),
        CLARITY(13),
        IGNITE(14),
        SMITE(11),
        TELEPORT(12);

        @Getter
        int id;

        private Spell(int id)
        {
            this.id = id;
        }
    }
}
