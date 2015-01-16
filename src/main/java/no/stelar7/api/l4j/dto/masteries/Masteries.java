package no.stelar7.api.l4j.dto.masteries;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Masteries implements Serializable
{
    int id;
    int rank;

}