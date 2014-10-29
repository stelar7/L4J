package my.api.stelar7.usewith.lol.dto.league;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LeagueEntry
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