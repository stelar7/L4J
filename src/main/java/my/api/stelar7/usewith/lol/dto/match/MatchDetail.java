package my.api.stelar7.usewith.lol.dto.match;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.matchhistory.MatchSummary;

@ToString
@Getter
public class MatchDetail extends MatchSummary
{

    List<Team>    teams;
    MatchTimeline timeline;
}
