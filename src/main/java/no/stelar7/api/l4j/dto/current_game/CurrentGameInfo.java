package no.stelar7.api.l4j.dto.current_game;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.match.BannedChampion;
@Getter
@ToString
public class CurrentGameInfo
{
    List<BannedChampion>     bannedChampions;
    long                     gameId;
    long                     gameLength;
    String                   gameMode;
    long                     gameQueueConfigId;
    long                     gameStartTime;
    String                   gameType;
    long                     gameTypeConfigId;
    long                     mapId;
    Observer                 observers;
    List<CurrentParticipant> participants;
    String                   platformId;
}
