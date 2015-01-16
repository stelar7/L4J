package no.stelar7.api.l4j.dto.staticdata.general;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LevelTip implements Serializable
{
    List<String> effect;
    List<String> label;
}
