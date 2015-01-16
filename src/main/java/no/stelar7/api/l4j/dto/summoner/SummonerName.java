package no.stelar7.api.l4j.dto.summoner;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SummonerName implements Serializable
{
    Long   id;
    String name;
}