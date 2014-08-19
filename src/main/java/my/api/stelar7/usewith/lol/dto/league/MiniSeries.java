package my.api.stelar7.usewith.lol.dto.league;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MiniSeries
{
    public int    losses;
    public String progress;
    public int    target;
    public int    wins;
}