package no.stelar7.api.l4j.dto.match;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Event
{
    String        ascendedType;
    List<Integer> assistingParticipantIds;
    String        buildingType;
    int           creatorId;
    String        eventType;
    int           itemAfter;
    int           itemBefore;
    int           itemId;
    int           killerId;
    String        laneType;
    String        levelUpType;
    String        monsterType;
    int           participantId;
    String        pointCaptured;
    Position      position;
    int           skillSlot;
    int           teamId;
    long          timestamp;
    String        towerType;
    int           victimId;
    String        wardType;
}
