package no.stelar7.api.l4j.dto.staticdata.item;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.staticdata.general.BasicData;

@ToString
@Getter
public class ItemList implements Serializable
{
    BasicData         basic;
    Map<String, Item> data;
    List<Group>       groups;
    List<ItemTree>    tree;
    String            type;
    String            version;
}
