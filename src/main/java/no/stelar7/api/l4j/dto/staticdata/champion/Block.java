package no.stelar7.api.l4j.dto.staticdata.champion;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Block
{
    List<BlockItem> items;
    boolean         recMath;
    String          type;
}
