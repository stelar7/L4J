package my.api.stelar7.usewith.lol.dto.matchhistory;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PlayerHistory
{
    List<MatchSummary> matches;
}
