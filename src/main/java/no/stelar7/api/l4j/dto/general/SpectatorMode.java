package no.stelar7.api.l4j.dto.general;

public enum SpectatorMode
{

    ALL("ALL"),
    NONE("NONE"),
    FRIENDS("FRIENDS"),
    LOBBY("LOBBYONLY");
    String type;

    SpectatorMode(final String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "spec" + this.type;
    }
}
