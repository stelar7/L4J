package no.stelar7.api.l4j.dto.staticdata.mastery;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.staticdata.general.Image;

@Getter
@ToString
public class Mastery implements Serializable
{
    List<String> description;
    int          id;
    Image        image;
    String       name;
    String       prereq;
    String       masteryTree;
    int          ranks;
    List<String> sanitizedDescription;
}
