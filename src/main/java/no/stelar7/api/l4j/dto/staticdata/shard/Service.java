package no.stelar7.api.l4j.dto.staticdata.shard;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Service
{
    List<Incident> incidents;
    String         name;
    String         slug;
    String         status;
}
