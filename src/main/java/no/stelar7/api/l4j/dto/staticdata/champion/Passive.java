package no.stelar7.api.l4j.dto.staticdata.champion;

import no.stelar7.api.l4j.dto.staticdata.general.Image;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Passive
{
    String description;
    Image  image;
    String name;
    String sanitizedDescription;
}
