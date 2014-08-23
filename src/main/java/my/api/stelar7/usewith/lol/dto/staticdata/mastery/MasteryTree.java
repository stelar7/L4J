package my.api.stelar7.usewith.lol.dto.staticdata.mastery;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MasteryTree
{
    List<MasteryTreeList> Defense;
    List<MasteryTreeList> Offense;
    List<MasteryTreeList> Utility;
}
