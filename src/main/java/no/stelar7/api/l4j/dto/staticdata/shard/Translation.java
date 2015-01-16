package no.stelar7.api.l4j.dto.staticdata.shard;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Translation implements Serializable
{
    String content;
    String locale;
    String updated_at;
}
