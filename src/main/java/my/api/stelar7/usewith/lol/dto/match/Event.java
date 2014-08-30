package my.api.stelar7.usewith.lol.dto.match;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Event
{
    List<Integer> assistingParticipantIds;
    String        buildingType;
    int           creatorId;
    String        eventType;
    int           itemAfter;
    int           itemBefore;
    int           killerId;
    String        laneType;
    String        levelUpType;
    String        monsterType;
    int           participantId;
    Position      position;
    int           skillSlot;
    int           teamId;
    long          timestamp;
    String        towerType;
    int           victimId;
    String        wardType;
}
