package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Skin implements Serializable
{
    int    id;
    String name;
    int    num;
}
