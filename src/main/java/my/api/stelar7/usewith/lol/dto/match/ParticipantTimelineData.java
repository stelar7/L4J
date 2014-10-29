package my.api.stelar7.usewith.lol.dto.match;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ParticipantTimelineData
{
    double tenToTwenty;
    double thirtyToEnd;
    double twentyToThirty;
    double zeroToTen;

    public Map<String, Double> getFields()
    {
        Map<String, Double> retme = new HashMap<String, Double>();
        retme.put("zeroToTen", zeroToTen);
        retme.put("tenToTwenty", tenToTwenty);
        retme.put("twentyToThirty", twentyToThirty);
        retme.put("thirtyToEnd", thirtyToEnd);
        return retme;
    }
}
