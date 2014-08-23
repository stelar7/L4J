package my.api.stelar7.usewith.lol.dto.staticdata.mastery;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Image;

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
