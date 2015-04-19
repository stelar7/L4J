package no.stelar7.api.l4j.dto.staticdata.summoners;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.staticdata.general.Image;
import no.stelar7.api.l4j.dto.staticdata.general.LevelTip;
import no.stelar7.api.l4j.dto.staticdata.general.SpellVars;

@Getter
@ToString
public class SummonerSpell implements Serializable
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

    public String getSanitizedTooltip(final int championLevel)
    {
        return this.replaceVariables(this.sanitizedTooltip, championLevel);
    }

    public String getTooltip(final int championLevel)
    {
        return this.replaceVariables(this.tooltip, championLevel);
    }

    private String replaceVariables(String text, final int level)
    {
        if ((level < 1) || (level > 18))
        {
            throw new IllegalArgumentException("Champion level out of bounds");
        }

        if (this.vars != null)
        {
            for (final SpellVars var : this.vars)
            {
                if (var == null)
                {
                    continue;
                }
                final Double val = var.getLink().equals("@player.level") ? var.getCoeff().get(level - 1) : var.getCoeff().get(0);
                text = text.replaceAll("\\{\\{ " + var.getKey() + " \\}\\}", val.toString());
            }
        }

        return text;
    }
}
