package no.stelar7.api.l4j.dto.featured_game;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.current_game.CurrentGameInfo;

@Getter
@ToString
public class FeaturedGames
{
    long                  clientRefreshInterval;
    List<CurrentGameInfo> gameList;
}
