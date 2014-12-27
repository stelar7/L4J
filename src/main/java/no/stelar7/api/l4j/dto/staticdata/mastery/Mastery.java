package no.stelar7.api.l4j.dto.staticdata.mastery;

import java.util.List;

import no.stelar7.api.l4j.dto.staticdata.general.Image;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Mastery
{
    List<String> description;
    int          id;
    Image        image;
    String       name;
    String       prereq;
    int          ranks;
    List<String> sanitizedDescription;
}
