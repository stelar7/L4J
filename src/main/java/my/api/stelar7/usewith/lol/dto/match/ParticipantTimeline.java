package my.api.stelar7.usewith.lol.dto.match;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Map<String, Double>> getFields()
    {
        Map<String, Map<String, Double>> retme = new HashMap<String, Map<String, Double>>();
        if (ancientGolemAssistsPerMinCounts != null) retme.put("ancientGolemAssistsPerMinCounts", ancientGolemAssistsPerMinCounts.getFields());
        if (ancientGolemKillsPerMinCounts != null) retme.put("ancientGolemKillsPerMinCounts", ancientGolemKillsPerMinCounts.getFields());
        if (ancientGolemKillsPerMinCounts != null) retme.put("assistedLaneDeathsPerMinDeltas", assistedLaneDeathsPerMinDeltas.getFields());
        if (assistedLaneKillsPerMinDeltas != null) retme.put("assistedLaneKillsPerMinDeltas", assistedLaneKillsPerMinDeltas.getFields());
        if (baronAssistsPerMinCounts != null) retme.put("baronAssistsPerMinCounts", baronAssistsPerMinCounts.getFields());
        if (baronKillsPerMinCounts != null) retme.put("baronKillsPerMinCounts", baronKillsPerMinCounts.getFields());
        if (creepsPerMinDeltas != null) retme.put("creepsPerMinDeltas", creepsPerMinDeltas.getFields());
        if (csDiffPerMinDeltas != null) retme.put("csDiffPerMinDeltas", csDiffPerMinDeltas.getFields());
        if (damageTakenDiffPerMinDeltas != null) retme.put("damageTakenDiffPerMinDeltas", damageTakenDiffPerMinDeltas.getFields());
        if (damageTakenDiffPerMinDeltas != null) retme.put("damageTakenPerMinDeltas", damageTakenPerMinDeltas.getFields());
        if (dragonAssistsPerMinCounts != null) retme.put("dragonAssistsPerMinCounts", dragonAssistsPerMinCounts.getFields());
        if (dragonKillsPerMinCounts != null) retme.put("dragonKillsPerMinCounts", dragonKillsPerMinCounts.getFields());
        if (elderLizardAssistsPerMinCounts != null) retme.put("elderLizardAssistsPerMinCounts", elderLizardAssistsPerMinCounts.getFields());
        if (elderLizardKillsPerMinCounts != null) retme.put("elderLizardKillsPerMinCounts", elderLizardKillsPerMinCounts.getFields());
        if (goldPerMinDeltas != null) retme.put("goldPerMinDeltas", goldPerMinDeltas.getFields());
        if (towerAssistsPerMinCounts != null) retme.put("towerAssistsPerMinCounts", towerAssistsPerMinCounts.getFields());
        if (towerKillsPerMinCounts != null) retme.put("towerKillsPerMinCounts", towerKillsPerMinCounts.getFields());
        if (towerKillsPerMinDeltas != null) retme.put("towerKillsPerMinDeltas", towerKillsPerMinDeltas.getFields());
        if (vilemawAssistsPerMinCounts != null) retme.put("vilemawAssistsPerMinCounts", vilemawAssistsPerMinCounts.getFields());
        if (vilemawKillsPerMinCounts != null) retme.put("vilemawKillsPerMinCounts", vilemawKillsPerMinCounts.getFields());
        if (wardsPerMinDeltas != null) retme.put("wardsPerMinDeltas", wardsPerMinDeltas.getFields());
        if (xpPerMinDeltas != null) retme.put("xpPerMinDeltas", xpPerMinDeltas.getFields());
        if (xpDiffPerMinDeltas != null) retme.put("xpDiffPerMinDeltas", xpDiffPerMinDeltas.getFields());
        return retme;
    }
}
