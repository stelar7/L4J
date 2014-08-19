package my.api.stelar7.usewith.lol.dto.match;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Event
{
    List<Integer> assistingParticipantIds;
    String        uildingType;
    int           creatorId;
    String        eventType;
    int           killerId;
    String        laneType;
    String        monsterType;
    Position      position;
    int           teamId;
    long          timestamp;
    String        towerType;
    int           victimId;
    String        wardType;
}
