package no.stelar7.api.l4j.dto.staticdata.rune;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.staticdata.general.BasicData;

@Getter
@ToString
public class RuneList implements Serializable
{
    BasicData         basic;
    Map<String, Rune> data;
    String            type;
    String            version;
}
