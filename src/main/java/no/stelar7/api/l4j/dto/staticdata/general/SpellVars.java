package no.stelar7.api.l4j.dto.staticdata.general;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SpellVars implements Serializable
{
    List<Double> coeff;
    String       dyn;
    String       key;
    String       link;
    String       ranksWith;
}
