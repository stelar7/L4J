package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;

import no.stelar7.api.l4j.dto.staticdata.general.Image;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Passive implements Serializable
{
    String description;
    Image  image;
    String name;
    String sanitizedDescription;
}
