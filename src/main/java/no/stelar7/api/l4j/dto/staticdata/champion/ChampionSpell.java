package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;
import java.util.List;

import no.stelar7.api.l4j.dto.staticdata.general.Image;
import no.stelar7.api.l4j.dto.staticdata.general.LevelTip;
import no.stelar7.api.l4j.dto.staticdata.general.SpellVars;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChampionSpell implements Serializable
{
    List<Image>        altimages;
    List<Double>       cooldown;
    String             cooldownBurn;
    List<Integer>      cost;
    String             costBurn;
    String             costType;
    String             description;
    List<List<Double>> effect;
    List<String>       effectBurn;
    Image              image;
    String             key;
    LevelTip           leveltip;
    int                maxrank;
    String             name;
    Object             range;
    String             rangeBurn;
    String             resource;
    String             sanitizedDescription;
    String             sanitizedTooltip;
    String             tooltip;
    List<SpellVars>    vars;

    public String getTooltip(int champLevel, int spellLevel)
    {
        return replaceVariables(tooltip, champLevel, spellLevel);
    }

    public String getSanitizedTooltip(final int champLevel, final int spellLevel)
    {
        return replaceVariables(sanitizedTooltip, champLevel, spellLevel);
    }

    private String replaceVariables(String text, int clevel, int slevel)
    {
        if (clevel > 18 || clevel < 1) { throw new IllegalArgumentException("Champion level out of bounds!"); }
        if (slevel > maxrank || slevel < 1) { throw new IllegalArgumentException("Spell level out of bounds!"); }
        if (effect != null)
        {
            int i = 1;
            for (final List<Double> e : effect)
            {
                if (e == null) continue;
                text = text.replaceAll("\\{\\{ e" + i + " \\}\\}", e.get(slevel - 1).toString());
                i++;
            }
        }
        if (vars != null)
        {
            for (final SpellVars var : vars)
            {
                if (var == null) continue;
                Double val = var.getCoeff().get(0);
                if (var.getCoeff().size() == maxrank)
                {
                    val = var.getCoeff().get(slevel - 1);
                } else if (var.getCoeff().size() == 18)
                {
                    val = var.getCoeff().get(clevel - 1);
                }
                text = text.replaceAll("\\{\\{ " + var.getKey() + " \\}\\}", val.toString());
            }
        }
        return text;
    }
}
