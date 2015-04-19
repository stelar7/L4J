package no.stelar7.api.l4j.dto.current_game;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.match.MatchMastery;

@Getter
@ToString
public class CurrentParticipant
{
    boolean            bot;
    int                championId;
    List<MatchMastery> masteries;
    long               profileIconId;
    List<CurrentRune>  runes;
    int                spell1Id;
    int                spell2Id;
    long               summonerId;
    String             summonerName;
    int                teamId;
}
