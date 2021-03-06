package no.stelar7.api.l4j.dto.staticdata.shard;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Incident implements Serializable
{
    boolean       active;
    String        created_at;
    long          id;
    List<Message> updates;
}
