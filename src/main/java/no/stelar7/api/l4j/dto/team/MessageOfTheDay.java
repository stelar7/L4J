package no.stelar7.api.l4j.dto.team;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MessageOfTheDay implements Serializable
{
    Long   createDate;
    String message;
    int    version;

}
