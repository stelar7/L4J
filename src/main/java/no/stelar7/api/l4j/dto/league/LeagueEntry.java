package no.stelar7.api.l4j.dto.league;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LeagueEntry implements Serializable
{
    public String     division;
    public boolean    isFreshBlood;
    public boolean    isHotStreak;
    public boolean    isInactive;
    public boolean    isVeteran;
    public int        leaguePoints;
    public MiniSeries miniSeries;
    public String     playerOrTeamId;
    public String     playerOrTeamName;
    public int        wins;

}