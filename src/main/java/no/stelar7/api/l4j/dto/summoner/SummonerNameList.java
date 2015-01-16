package no.stelar7.api.l4j.dto.summoner;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SummonerNameList implements Serializable
{
    Map<String, String> map;

}