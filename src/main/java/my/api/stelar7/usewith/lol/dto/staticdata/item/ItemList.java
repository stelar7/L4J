package my.api.stelar7.usewith.lol.dto.staticdata.item;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.staticdata.general.BasicData;

@ToString
@Getter
public class ItemList
{
    BasicData         basic;
    Map<String, Item> data;
    List<Group>       groups;
    List<ItemTree>    tree;
    String            type;
    String            version;
}
