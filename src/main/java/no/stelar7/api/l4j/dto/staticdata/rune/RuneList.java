package no.stelar7.api.l4j.dto.staticdata.rune;

import java.io.Serializable;
import java.util.Map;

import no.stelar7.api.l4j.dto.staticdata.general.BasicData;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RuneList implements Serializable
{
    BasicData         basic;
    Map<String, Rune> data;
    String            type;
    String            version;
}
