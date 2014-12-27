package no.stelar7.api.l4j.dto.staticdata.shard;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Translation
{
    String content;
    String locale;
    String updated_at;
}
