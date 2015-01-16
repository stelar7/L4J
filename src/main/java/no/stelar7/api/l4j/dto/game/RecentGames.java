package no.stelar7.api.l4j.dto.game;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RecentGames implements Serializable
{
    private Long       summonerId;
    private List<Game> games;

}