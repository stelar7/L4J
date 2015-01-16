package no.stelar7.api.l4j.dto.match;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MatchTimeline implements Serializable
{
    long        frameInterval;
    List<Frame> frames;
}
