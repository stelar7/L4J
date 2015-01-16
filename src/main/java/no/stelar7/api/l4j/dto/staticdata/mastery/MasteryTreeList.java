package no.stelar7.api.l4j.dto.staticdata.mastery;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MasteryTreeList implements Serializable
{
    List<MasteryTreeItem> masteryTreeItems;
}
