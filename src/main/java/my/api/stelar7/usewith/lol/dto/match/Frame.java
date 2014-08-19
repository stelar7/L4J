package my.api.stelar7.usewith.lol.dto.match;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Frame
{
    List<Event>                   events;
    Map<String, ParticipantFrame> participantFrames;
    long                          timestamp;
}
