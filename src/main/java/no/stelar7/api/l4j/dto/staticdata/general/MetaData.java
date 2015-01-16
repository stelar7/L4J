package no.stelar7.api.l4j.dto.staticdata.general;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MetaData implements Serializable
{
    boolean isRune;
    String  tier;
    String  type;
}
