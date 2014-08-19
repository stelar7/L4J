package my.api.stelar7.usewith.lol.dto.staticdata.rune;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Gold;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Image;
import my.api.stelar7.usewith.lol.dto.staticdata.general.MetaData;

@Getter
@ToString
public class Rune
{
    String               colloq;
    boolean              consumeOnFull;
    boolean              consumed;
    int                  depth;
    String               description;
    List<String>         from;
    Gold                 gold;
    String               group;
    boolean              hideFromAll;
    int                  id;
    Image                image;
    boolean              inStore;
    List<String>         into;
    Map<String, Boolean> maps;
    String               name;
    String               plaintext;
    String               requiredChampion;
    MetaData             rune;
    String               sanitizedDescription;
    int                  specialRecipe;
    int                  stacks;
    // BasicDataStats stats;
    Map<String, Double>  stats;
    List<String>         tags;
}
