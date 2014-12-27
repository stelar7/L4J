package no.stelar7.api.l4j.dto.match;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Team
{
    List<BannedChampion> bans;
    int                  baronKills;
    long                 dominionVictoryScore;
    int                  dragonKills;
    boolean              firstBaron;
    boolean              firstBlood;
    boolean              firstDragon;
    boolean              firstInhibitor;
    boolean              firstTower;
    int                  inhibitorKills;
    int                  teamId;
    int                  towerKills;
    int                  vilemawKills;
    boolean              winner;
}
