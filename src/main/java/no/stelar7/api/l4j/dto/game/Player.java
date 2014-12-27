package no.stelar7.api.l4j.dto.game;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Player
{
    int  championId;
    Long summonerId;
    int  teamId;

}