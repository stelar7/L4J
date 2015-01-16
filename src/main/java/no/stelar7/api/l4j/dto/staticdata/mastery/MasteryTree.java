package no.stelar7.api.l4j.dto.staticdata.mastery;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MasteryTree implements Serializable
{
    List<MasteryTreeList> Defense;
    List<MasteryTreeList> Offense;
    List<MasteryTreeList> Utility;
}
