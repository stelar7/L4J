package no.stelar7.api.l4j.dto.game;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Player implements Serializable
{
    int  championId;
    Long summonerId;
    int  teamId;

}