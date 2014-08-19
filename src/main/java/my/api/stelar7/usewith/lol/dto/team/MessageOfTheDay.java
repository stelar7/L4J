package my.api.stelar7.usewith.lol.dto.team;

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
