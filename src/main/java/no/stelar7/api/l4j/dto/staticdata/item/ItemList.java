package no.stelar7.api.l4j.dto.staticdata.item;

import java.util.List;
import java.util.Map;

import no.stelar7.api.l4j.dto.staticdata.general.BasicData;
import lombok.Getter;
import lombok.ToString;

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
