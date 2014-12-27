package no.stelar7.api.l4j.dto.team;

import java.util.HashMap;
import java.util.List;

import lombok.Getter;

public class TeamList
{

    @Getter
    HashMap<String, List<Team>> teams;

}
