package no.stelar7.api.l4j.dto.match;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ParticipantTimelineData implements Serializable
{
    double tenToTwenty;
    double thirtyToEnd;
    double twentyToThirty;
    double zeroToTen;

    public Map<String, Double> getFields()
    {
        final Map<String, Double> retme = new HashMap<String, Double>();
        retme.put("zeroToTen", this.zeroToTen);
        retme.put("tenToTwenty", this.tenToTwenty);
        retme.put("twentyToThirty", this.twentyToThirty);
        retme.put("thirtyToEnd", this.thirtyToEnd);
        return retme;
    }
}
