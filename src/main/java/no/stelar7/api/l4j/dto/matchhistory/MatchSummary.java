package no.stelar7.api.l4j.dto.matchhistory;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.match.Participant;
import no.stelar7.api.l4j.dto.match.ParticipantIdentity;

@ToString
@Getter
public class MatchSummary implements Serializable
{
    long                      mapId;
    long                      matchCreation;
    long                      matchDuration;
    long                      matchId;
    String                    matchMode;
    String                    matchType;
    String                    matchVersion;
    List<ParticipantIdentity> participantIdentities;
    List<Participant>         participants;
    String                    platformId;
    String                    queueType;
    String                    region;
    String                    season;
}
