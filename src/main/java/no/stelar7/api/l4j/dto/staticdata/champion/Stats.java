package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Stats implements Serializable
{
    double armor;
    double armorperlevel;
    double attackdamage;
    double attackdamageperlevel;
    double attackrange;
    double attackspeedoffset;
    double attackspeedperlevel;
    double crit;
    double critperlevel;
    double hp;
    double hpperlevel;
    double hpregen;
    double hpregenperlevel;
    double movespeed;
    double mp;
    double mpperlevel;
    double mpregen;
    double mpregenperlevel;
    double spellblock;
    double spellblockperlevel;
}
