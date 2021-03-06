package no.stelar7.api.l4j.dto.league;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MiniSeries implements Serializable
{
    public int    losses;
    public String progress;
    public int    target;
    public int    wins;
}