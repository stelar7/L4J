package no.stelar7.api.l4j.dto.staticdata.item;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Group implements Serializable
{
    String key;
    String MaxGroupOwnable;
}
