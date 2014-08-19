package my.api.stelar7.usewith.lol.basic;

public class VersionChecker
{

    public static String getFor(final URLEndpoint urlEndpoint)
    {
        switch (urlEndpoint)
        {
            case CHAMPION_LIST:
                return "v1.2";
            case CHAMPION_ID:
                return "v1.2";
            case SUMMONER_BY_NAME:
                return "v1.4";
            case RECENT_GAMES:
                return "v1.3";
            case SUMMONER_RUNES:
                return "v1.4";
            case SUMMONER_MASTERIES:
                return "v1.4";
            case TEAM_BY_SUMMONER:
                return "v2.3";
            case TEAM_BY_ID:
                return "v2.3";
            case LEAGUE_BY_SUMMONER_FULL:
                return "v2.4";
            case LEAGUE_BY_SUMMONER:
                return "v2.4";
            case LEAGUE_BY_TEAM:
                return "v2.4";
            case LEAGUE_BY_TEAM_FULL:
                return "v2.4";
            case CHALLENGER_LEAGUE:
                return "v2.4";
            case MATCH:
                return "v2.2";
            case MATCH_HISTORY:
                return "v2.2";
            case RANKED_STATS:
                return "v1.3";
            case STAT_SUMMARY:
                return "v1.3";
            case STATIC_CHAMPION:
                return "v1.2";
            case STATIC_CHAMPION_ID:
                return "v1.2";
            case STATIC_ITEM:
                return "v1.2";
            case STATIC_ITEM_ID:
                return "v1.2";
            case STATIC_MASTERY:
                return "v1.2";
            case STATIC_MASTERY_ID:
                return "v1.2";
            case STATIC_REALM:
                return "v1.2";
            case STATIC_VERSION:
                return "v1.2";
            case STATIC_RUNE:
                return "v1.2";
            case STATIC_RUNE_ID:
                return "v1.2";
            case STATIC_SUMMONER_SPELL:
                return "v1.2";
            case STATIC_SUMMONER_SPELL_ID:
                return "v1.2";
        }
        return "null";
    }
}
