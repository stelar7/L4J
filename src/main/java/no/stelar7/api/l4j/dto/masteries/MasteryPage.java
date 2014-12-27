package no.stelar7.api.l4j.dto.masteries;

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