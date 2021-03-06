package no.stelar7.api.l4j.dto.staticdata.item;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ItemTree implements Serializable
{
    String       header;
    List<String> tags;
}
