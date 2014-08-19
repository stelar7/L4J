package my.api.stelar7.usewith.lol.basic;

import lombok.Getter;

public enum Server
{
    BR("br.api.pvp.net"),
    EUNE("eune.api.pvp.net"),
    EUW("euw.api.pvp.net"),
    KR("kr.api.pvp.net"),
    LAN("lan.api.pvp.net"),
    LAS("las.api.pvp.net"),
    NA("na.api.pvp.net"),
    OCE("oce.api.pvp.net"),
    RU("ru.api.pvp.net"),
    GLOBAL("global.api.pvp.net"),
    TR("tr.api.pvp.net");

    @Getter
    String server;

    Server(final String endpoint)
    {
        this.server = endpoint;
    }
}
