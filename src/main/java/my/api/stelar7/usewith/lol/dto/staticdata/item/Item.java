package my.api.stelar7.usewith.lol.dto.staticdata.item;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;
import my.api.stelar7.usewith.lol.dto.staticdata.general.BasicDataStats;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Gold;
import my.api.stelar7.usewith.lol.dto.staticdata.general.Image;
import my.api.stelar7.usewith.lol.dto.staticdata.general.MetaData;

@ToString
@Getter
public class Item
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
    BasicDataStats       stats;
    List<String>         tags;
}
