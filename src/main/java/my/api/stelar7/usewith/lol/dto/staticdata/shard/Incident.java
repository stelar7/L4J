package my.api.stelar7.usewith.lol.dto.staticdata.shard;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Incident
{
    boolean       active;
    String        created_at;
    long          id;
    List<Message> updates;
}