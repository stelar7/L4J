package no.stelar7.api.l4j.dto.match;

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
