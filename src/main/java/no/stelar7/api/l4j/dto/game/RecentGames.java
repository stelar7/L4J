package no.stelar7.api.l4j.dto.game;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RecentGames
{
    private Long       summonerId;
    private List<Game> games;

}