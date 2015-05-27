package no.stelar7.api.l4j.dto.champion;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Champion implements Serializable
{
    boolean active;
    boolean botEnabled;
    boolean botMmEnabled;
    boolean freeToPlay;
    int    id;
    boolean rankedPlayEnabled;
}
