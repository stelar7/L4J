package my.api.stelar7.usewith.lol.dto.champion;

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
