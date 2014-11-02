package my.api.stelar7.usewith.lol.dto.staticdata.shard;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Shard
{
    String       hostname;
    List<String> locales;
    String       name;
    String       region_tag;
    String       slug;
}
