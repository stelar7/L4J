package no.stelar7.api.l4j.dto.team;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MessageOfTheDay
{
    Long   createDate;
    String message;
    int    version;

}
