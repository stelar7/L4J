package my.api.stelar7.usewith.lol.dto.staticdata.champion;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Image;

@Getter
@ToString
public class Passive
{
    String description;
    Image  image;
    String name;
    String sanitizedDescription;
}
