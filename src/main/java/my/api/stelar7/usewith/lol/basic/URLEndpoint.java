package my.api.stelar7.usewith.lol.basic;

import lombok.Getter;

public enum URLEndpoint
{
    CHAMPION_LIST("api/lol/{region}/{version}/champion"),
    CHAMPION_ID("api/lol/{region}/{version}/champion/{data}"),
    SUMMONER_BY_NAME("api/lol/{region}/{version}/summoner/by-name/{data}"),
    SUMMONER_BY_ID("api/lol/{region}/{version}/summoner/{data}"),
    RECENT_GAMES("api/lol/{region}/{version}/game/by-summoner/{data}/recent"),
    SUMMONER_RUNES("api/lol/{region}/{version}/summoner/{data}/runes"),
    SUMMONER_MASTERIES("api/lol/{region}/{version}/summoner/{data}/masteries"),
    TEAM_BY_SUMMONER("api/lol/{region}/{version}/team/by-summoner/{data}"),
    TEAM_BY_ID("api/lol/{region}/{version}/team/{data}"),
    LEAGUE_BY_SUMMONER_FULL("api/lol/{region}/{version}/league/by-summoner/{data}"),
    LEAGUE_BY_SUMMONER("api/lol/{region}/{version}/league/by-summoner/{data}/entry"),
    LEAGUE_BY_TEAM_FULL("api/lol/{region}/{version}/league/by-team/{data}"),
    LEAGUE_BY_TEAM("api/lol/{region}/{version}/league/by-team/{data}/entry"),
    CHALLENGER_LEAGUE("api/lol/{region}/{version}/league/challenger"),
    MATCH("api/lol/{region}/{version}/match/{data}"),
    MATCH_HISTORY("api/lol/{region}/{version}/matchhistory/{data}"),
    RANKED_STATS("api/lol/{region}/{version}/stats/by-summoner/{data}/ranked"),
    STAT_SUMMARY("api/lol/{region}/{version}/stats/by-summoner/{data}/summary"),
    STATIC_CHAMPION("api/lol/static-data/{region}/{version}/champion"),
    STATIC_CHAMPION_ID("api/lol/static-data/{region}/{version}/champion/{data}"),
    STATIC_ITEM("api/lol/static-data/{region}/{version}/item"),
    STATIC_ITEM_ID("api/lol/static-data/{region}/{version}/item/{data}"),
    STATIC_MASTERY("api/lol/static-data/{region}/{version}/mastery"),
    STATIC_MASTERY_ID("api/lol/static-data/{region}/{version}/mastery/{data}"),
    STATIC_REALM("api/lol/static-data/{region}/{version}/realm"),
    STATIC_VERSION("api/lol/static-data/{region}/{version}/versions"),
    STATIC_RUNE("api/lol/static-data/{region}/{version}/rune"),
    STATIC_RUNE_ID("api/lol/static-data/{region}/{version}/rune/{data}"),
    STATIC_SUMMONER_SPELL("api/lol/static-data/{region}/{version}/summoner-spell"),
    STATIC_SUMMONER_SPELL_ID("api/lol/static-data/{region}/{version}/summoner-spell/{data}");

    @Getter
    String value;

    private URLEndpoint(final String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return this.value;
    }
}
