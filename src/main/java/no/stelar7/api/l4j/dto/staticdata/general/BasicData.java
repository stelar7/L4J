package no.stelar7.api.l4j.dto.staticdata.general;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BasicData implements Serializable
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
    Map<String, Double>  stats;
    List<String>         tags;

}
