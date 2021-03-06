package no.stelar7.api.l4j.dto.staticdata.shard;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Shard implements Serializable
{
    String       hostname;
    List<String> locales;
    String       name;
    String       region_tag;
    String       slug;
}
