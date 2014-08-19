package my.api.stelar7.usewith.lol.dto.matchhistory;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.match.Participant;
import my.api.stelar7.usewith.lol.dto.match.ParticipantIdentity;

@ToString
@Getter
public class MatchSummary
{
    long                      mapId;
    long                      matchCreation;
    long                      matchDuration;
    long                      matchId;
    String                    matchVersion;
    List<ParticipantIdentity> participantIdentities;
    List<Participant>         participants;
    String                    queueType;
    String                    region;
    String                    season;
}
