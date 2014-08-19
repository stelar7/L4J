package my.api.stelar7.usewith.lol.dto.match;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ParticipantTimeline
{
    ParticipantTimelineData ancientGolemAssistsPerMinCounts;
    ParticipantTimelineData ancientGolemKillsPerMinCounts;
    ParticipantTimelineData assistedLaneDeathsPerMinDeltas;
    ParticipantTimelineData assistedLaneKillsPerMinDeltas;
    ParticipantTimelineData baronAssistsPerMinCounts;
    ParticipantTimelineData baronKillsPerMinCounts;
    ParticipantTimelineData creepsPerMinDeltas;
    ParticipantTimelineData csDiffPerMinDeltas;
    ParticipantTimelineData damageTakenDiffPerMinDeltas;
    ParticipantTimelineData damageTakenPerMinDeltas;
    ParticipantTimelineData dragonAssistsPerMinCounts;
    ParticipantTimelineData dragonKillsPerMinCounts;
    ParticipantTimelineData elderLizardAssistsPerMinCounts;
    ParticipantTimelineData elderLizardKillsPerMinCounts;
    ParticipantTimelineData goldPerMinDeltas;
    ParticipantTimelineData inhibitorAssistsPerMinCounts;
    ParticipantTimelineData inhibitorKillsPerMinCounts;
    String                  lane;
    String                  role;
    ParticipantTimelineData towerAssistsPerMinCounts;
    ParticipantTimelineData towerKillsPerMinCounts;
    ParticipantTimelineData towerKillsPerMinDeltas;
    ParticipantTimelineData vilemawAssistsPerMinCounts;
    ParticipantTimelineData vilemawKillsPerMinCounts;
    ParticipantTimelineData wardsPerMinDeltas;
    ParticipantTimelineData xpDiffPerMinDeltas;
    ParticipantTimelineData xpPerMinDeltas;
}
