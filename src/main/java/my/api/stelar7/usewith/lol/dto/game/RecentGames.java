package my.api.stelar7.usewith.lol.dto.game;

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