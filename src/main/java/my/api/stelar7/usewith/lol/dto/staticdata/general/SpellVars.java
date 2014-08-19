package my.api.stelar7.usewith.lol.dto.staticdata.general;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SpellVars
{
    List<Double> coeff;
    String       dyn;
    String       key;
    String       link;
    String       ranksWith;
}
