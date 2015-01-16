package no.stelar7.api.l4j.dto.match;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Player implements Serializable
{
    String matchHistoryUri;
    int    profileIcon;
    long   summonerId;
    String summonerName;
}
