package no.stelar7.api.l4j.dto.staticdata.general;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Image implements Serializable
{
    String full;
    String group;
    String sprite;
    int    w;
    int    h;
    int    x;
    int    y;
}
