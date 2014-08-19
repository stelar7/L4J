package my.api.stelar7.usewith.lol.dto.masteries;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MasteryPage
{
    boolean         current;
    String          name;
    long            id;
    List<Masteries> masteries;

}