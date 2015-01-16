package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Block implements Serializable
{
    List<BlockItem> items;
    boolean         recMath;
    String          type;
}
