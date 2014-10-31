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
        final Map<String, Map<String, Double>> retme = new HashMap<String, Map<String, Double>>();
        if (this.ancientGolemAssistsPerMinCounts != null)
        {
            retme.put("ancientGolemAssistsPerMinCounts", this.ancientGolemAssistsPerMinCounts.getFields());
        }
        if (this.ancientGolemKillsPerMinCounts != null)
        {
            retme.put("ancientGolemKillsPerMinCounts", this.ancientGolemKillsPerMinCounts.getFields());
        }
        if (this.ancientGolemKillsPerMinCounts != null)
        {
            retme.put("assistedLaneDeathsPerMinDeltas", this.assistedLaneDeathsPerMinDeltas.getFields());
        }
        if (this.assistedLaneKillsPerMinDeltas != null)
        {
            retme.put("assistedLaneKillsPerMinDeltas", this.assistedLaneKillsPerMinDeltas.getFields());
        }
        if (this.baronAssistsPerMinCounts != null)
        {
            retme.put("baronAssistsPerMinCounts", this.baronAssistsPerMinCounts.getFields());
        }
        if (this.baronKillsPerMinCounts != null)
        {
            retme.put("baronKillsPerMinCounts", this.baronKillsPerMinCounts.getFields());
        }
        if (this.creepsPerMinDeltas != null)
        {
            retme.put("creepsPerMinDeltas", this.creepsPerMinDeltas.getFields());
        }
        if (this.csDiffPerMinDeltas != null)
        {
            retme.put("csDiffPerMinDeltas", this.csDiffPerMinDeltas.getFields());
        }
        if (this.damageTakenDiffPerMinDeltas != null)
        {
            retme.put("damageTakenDiffPerMinDeltas", this.damageTakenDiffPerMinDeltas.getFields());
        }
        if (this.damageTakenDiffPerMinDeltas != null)
        {
            retme.put("damageTakenPerMinDeltas", this.damageTakenPerMinDeltas.getFields());
        }
        if (this.dragonAssistsPerMinCounts != null)
        {
            retme.put("dragonAssistsPerMinCounts", this.dragonAssistsPerMinCounts.getFields());
        }
        if (this.dragonKillsPerMinCounts != null)
        {
            retme.put("dragonKillsPerMinCounts", this.dragonKillsPerMinCounts.getFields());
        }
        if (this.elderLizardAssistsPerMinCounts != null)
        {
            retme.put("elderLizardAssistsPerMinCounts", this.elderLizardAssistsPerMinCounts.getFields());
        }
        if (this.elderLizardKillsPerMinCounts != null)
        {
            retme.put("elderLizardKillsPerMinCounts", this.elderLizardKillsPerMinCounts.getFields());
        }
        if (this.goldPerMinDeltas != null)
        {
            retme.put("goldPerMinDeltas", this.goldPerMinDeltas.getFields());
        }
        if (this.towerAssistsPerMinCounts != null)
        {
            retme.put("towerAssistsPerMinCounts", this.towerAssistsPerMinCounts.getFields());
        }
        if (this.towerKillsPerMinCounts != null)
        {
            retme.put("towerKillsPerMinCounts", this.towerKillsPerMinCounts.getFields());
        }
        if (this.towerKillsPerMinDeltas != null)
        {
            retme.put("towerKillsPerMinDeltas", this.towerKillsPerMinDeltas.getFields());
        }
        if (this.vilemawAssistsPerMinCounts != null)
        {
            retme.put("vilemawAssistsPerMinCounts", this.vilemawAssistsPerMinCounts.getFields());
        }
        if (this.vilemawKillsPerMinCounts != null)
        {
            retme.put("vilemawKillsPerMinCounts", this.vilemawKillsPerMinCounts.getFields());
        }
        if (this.wardsPerMinDeltas != null)
        {
            retme.put("wardsPerMinDeltas", this.wardsPerMinDeltas.getFields());
        }
        if (this.xpPerMinDeltas != null)
        {
            retme.put("xpPerMinDeltas", this.xpPerMinDeltas.getFields());
        }
        if (this.xpDiffPerMinDeltas != null)
        {
            retme.put("xpDiffPerMinDeltas", this.xpDiffPerMinDeltas.getFields());
        }
        return retme;
    }
}
