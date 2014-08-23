package my.api.stelar7.usewith.lol.dto.staticdata.mastery;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MasteryList
{
    Map<String, Mastery> data;
    MasteryTree          tree;
    String               type;
    String               version;
}
