package no.stelar7.api.l4j.dto.matchhistory;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PlayerHistory implements Serializable
{
    List<MatchSummary> matches;
}
