package no.stelar7.api.l4j.dto.current_game;

import lombok.Getter;

@Getter
public enum PlatformId
{
    NA1,
    BR1,
    LA1,
    LA2,
    OC1,
    EUN1,
    TR1,
    RU,
    EUW1,
    KR;
    
    public String toString() {
        return this.name();
    }
}
