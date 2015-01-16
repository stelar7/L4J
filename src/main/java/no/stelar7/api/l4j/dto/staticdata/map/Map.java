package no.stelar7.api.l4j.dto.staticdata.map;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import no.stelar7.api.l4j.dto.staticdata.general.Image;

@Getter
@ToString
public class Map
{
    int           mapId;
    List<Integer> unpurchasableItemList;
    Image         image;
    String        mapName;
}
