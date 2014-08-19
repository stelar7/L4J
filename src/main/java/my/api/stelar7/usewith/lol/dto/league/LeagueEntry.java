package my.api.stelar7.usewith.lol.dto.league;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LeagueEntry
{
    public boolean    isFreshBlood;
    public boolean    isInactive;
    public boolean    isVeteran;
    public boolean    isHotStreak;
    public int        leaguePoints;
    public MiniSeries miniSeries;
    public String     playerOrTeamId;
    public String     playerOrTeamName;
    public String     division;
    public int        wins;

}