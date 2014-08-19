package my.api.stelar7.usewith.lol.dto.staticdata.champion;

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
