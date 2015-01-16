package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BlockItem implements Serializable
{
    int count;
    int id;
}
