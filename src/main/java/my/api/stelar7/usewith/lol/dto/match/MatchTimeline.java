package my.api.stelar7.usewith.lol.dto.match;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MatchTimeline
{
    long        frameInterval;
    List<Frame> frames;
}
