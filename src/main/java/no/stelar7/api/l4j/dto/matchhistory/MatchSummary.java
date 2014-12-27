package no.stelar7.api.l4j.dto.matchhistory;

import java.util.List;

import no.stelar7.api.l4j.dto.match.Participant;
import no.stelar7.api.l4j.dto.match.ParticipantIdentity;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MatchSummary
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
