package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.staticdata.general.Image;
import no.stelar7.api.l4j.dto.staticdata.general.LevelTip;
import no.stelar7.api.l4j.dto.staticdata.general.SpellVars;

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

    public String getSanitizedTooltip(final int champLevel, final int spellLevel)
    {
        return this.replaceVariables(this.sanitizedTooltip, champLevel, spellLevel);
    }

    public String getTooltip(final int champLevel, final int spellLevel)
    {
        return this.replaceVariables(this.tooltip, champLevel, spellLevel);
    }

    private String replaceVariables(String text, final int clevel, final int slevel)
    {
        if ((clevel > 18) || (clevel < 1))
        {
            throw new IllegalArgumentException("Champion level out of bounds!");
        }
        if ((slevel > this.maxrank) || (slevel < 1))
        {
            throw new IllegalArgumentException("Spell level out of bounds!");
        }
        if (this.effect != null)
        {
            int i = 1;
            for (final List<Double> e : this.effect)
            {
                if (e == null)
                {
                    continue;
                }
                text = text.replaceAll("\\{\\{ e" + i + " \\}\\}", e.get(slevel - 1).toString());
                i++;
            }
        }
        if (this.vars != null)
        {
            for (final SpellVars var : this.vars)
            {
                if (var == null)
                {
                    continue;
                }
                Double val = var.getCoeff().get(0);
                if (var.getCoeff().size() == this.maxrank)
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
