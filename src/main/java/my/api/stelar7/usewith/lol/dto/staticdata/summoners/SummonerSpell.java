package my.api.stelar7.usewith.lol.dto.staticdata.summoners;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Image;
import my.api.stelar7.usewith.lol.dto.staticdata.general.LevelTip;
import my.api.stelar7.usewith.lol.dto.staticdata.general.SpellVars;

@Getter
@ToString
public class SummonerSpell
{
    List<Double>       cooldown;
    String             cooldownBurn;
    List<Integer>      cost;
    String             costBurn;
    String             costType;
    String             description;
    List<List<Double>> effect;
    List<String>       effectBurn;
    int                id;
    Image              image;
    String             key;
    LevelTip           leveltip;
    int                maxrank;
    List<String>       modes;
    String             name;
    Object             range;
    String             rangeBurn;
    String             resource;
    String             sanitizedDescription;
    String             sanitizedTooltip;
    int                summonerLevel;
    String             tooltip;
    List<SpellVars>    vars;

    public enum Spells
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

        private Spells(int id)
        {
            this.id = id;
        }
    }

    public String getSanitizedTooltip(final int championLevel)
    {
        return replaceVariables(sanitizedTooltip, championLevel);
    }

    public String getTooltip(final int championLevel)
    {
        return replaceVariables(tooltip, championLevel);
    }

    private String replaceVariables(String text, final int level)
    {
        if (level < 1 || level > 18) { throw new IllegalArgumentException("Champion level out of bounds"); }

        if (vars != null)
        {
            for (final SpellVars var : vars)
            {
                if (var == null) continue;
                final Double val = var.getLink().equals("@player.level") ? var.getCoeff().get(level - 1) : var.getCoeff().get(0);
                text = text.replaceAll("\\{\\{ " + var.getKey() + " \\}\\}", val.toString());
            }
        }

        return text;
    }
}
