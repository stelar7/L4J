package my.api.stelar7.usewith.lol.dto.general;

public enum Map
{
    SUMMONERS_RIFT_SUMMER(1),
    SUMMONERS_RIFT_WINTER(2),
    PROVING_GROUNDS(3),
    TWISTED_TREELINE_OLD(4),
    CRYSTAL_SCAR(8),
    TWISTED_TREELINE_NEW(10),
    HOWLING_ABYSS(12);

    long id;

    Map(final long id)
    {
        this.id = id;
    }

    public static Map get(final long l)
    {
        for (final Map m : Map.values())
        {
            if (m.id == l) { return m; }
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "map" + this.id;
    }
}
