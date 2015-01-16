package no.stelar7.api.l4j.dto.staticdata.general;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Gold implements Serializable
{
    int     base;
    int     sell;
    int     total;
    boolean purchasable;
}
