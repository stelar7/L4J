package no.stelar7.api.l4j.dto.staticdata.shard;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Message
{
    String            author;
    String            content;
    String            created_at;
    long              id;
    String            severity;
    List<Translation> translations;
    String            updated_at;
}
