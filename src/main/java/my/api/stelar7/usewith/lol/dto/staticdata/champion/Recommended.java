package my.api.stelar7.usewith.lol.dto.staticdata.champion;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Recommended
{
    List<Block> blocks;
    String      champion;
    String      map;
    String      mode;
    boolean     priority;
    String      title;
    String      type;
}
