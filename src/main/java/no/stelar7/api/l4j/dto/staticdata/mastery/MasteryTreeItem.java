package no.stelar7.api.l4j.dto.staticdata.mastery;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MasteryTreeItem implements Serializable
{
    int    masteryId;
    String prereq;
}
