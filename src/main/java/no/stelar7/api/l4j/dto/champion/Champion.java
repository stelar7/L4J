package no.stelar7.api.l4j.dto.champion;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Champion
{
    boolean active;
    boolean botEnabled;
    boolean botMmEnabled;
    boolean freeToPlay;
    Long    id;
    boolean rankedPlayEnabled;
}
