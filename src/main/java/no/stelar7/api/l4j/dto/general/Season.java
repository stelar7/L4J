package no.stelar7.api.l4j.dto.general;

public enum Season
{
    PRESEASON_3,
    SEASON_3,
    PRESEASON_2014,
    SEASON_4,
    SEASON_2014,
    PRESEASON_2015,
    SEASON_5,
    SEASON_2015;

    @Override
    public String toString()
    {
        return this.name().replace("_", "");
    }

}
