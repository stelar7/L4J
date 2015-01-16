package no.stelar7.api.l4j.dto.masteries;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MasteryPages implements Serializable
{
    List<MasteryPage> pages;
    Long              summonerId;

}