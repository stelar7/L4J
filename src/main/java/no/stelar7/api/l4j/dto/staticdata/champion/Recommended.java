package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Recommended implements Serializable
{
    List<Block> blocks;
    String      champion;
    String      map;
    String      mode;
    boolean     priority;
    String      title;
    String      type;
}
