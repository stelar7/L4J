package no.stelar7.api.l4j.dto.match;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.matchhistory.MatchSummary;

@ToString
@Getter
public class MatchDetail extends MatchSummary implements Serializable
{

    List<Team>    teams;
    MatchTimeline timeline;
}
