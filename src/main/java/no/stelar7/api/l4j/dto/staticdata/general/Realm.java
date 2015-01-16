package no.stelar7.api.l4j.dto.staticdata.general;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Realm implements Serializable
{
    String              cdn;
    String              css;
    String              dd;
    String              l;
    String              lg;
    Map<String, String> n;
    int                 profileiconmax;
    String              store;
    String              v;
}
