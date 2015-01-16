package no.stelar7.api.l4j.dto.staticdata.mastery;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MasteryList implements Serializable
{
    Map<String, Mastery> data;
    MasteryTree          tree;
    String               type;
    String               version;
}
