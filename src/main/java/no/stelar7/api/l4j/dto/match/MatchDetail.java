package no.stelar7.api.l4j.dto.match;

import java.util.List;

import no.stelar7.api.l4j.dto.matchhistory.MatchSummary;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MatchDetail extends MatchSummary
{

    List<Team>    teams;
    MatchTimeline timeline;
}
