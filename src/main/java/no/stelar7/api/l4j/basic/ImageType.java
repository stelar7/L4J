package no.stelar7.api.l4j.basic;

public enum ImageType
{
    PROFILEICON("img/profileicon/"),
    CHAMPION_SPASH("img/champion/splash/"),
    CHAMPION_LOADING("img/champion/loading/"),
    CHAMPION_SQUARE("img/champion/"),
    CHAMPION_PASSIVE("img/passive/"),
    CHAMPION_ABILITY("img/spell/"),
    SUMMONER_SPELL("img/spell/"),
    ITEM("img/item/"),
    RUNE("img/rune/"),
    MASTERY("img/mastery/"),
    SPRITE("img/sprite/"),
    MINIMAP("img/map/"),
    SCOREBOARD("img/ui/");

    String urlpart;

    ImageType(final String urlpart)
    {
        this.urlpart = urlpart;
    }

    public String getURLPart()
    {
        return this.urlpart;
    }
}
