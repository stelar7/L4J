package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.staticdata.general.Image;

@Getter
@ToString
public class Passive implements Serializable
{
    String description;
    Image  image;
    String name;
    String sanitizedDescription;
}
