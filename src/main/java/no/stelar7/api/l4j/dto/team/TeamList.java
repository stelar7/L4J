package no.stelar7.api.l4j.dto.team;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;

public class TeamList implements Serializable
{

    @Getter
    HashMap<String, List<Team>> teams;

}
