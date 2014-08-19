package my.api.stelar7.usewith.lol.dto.general;

public enum PickMode
{
    BLIND_PICK(1),
    DRAFT_PICK(2),
    ARAM(4),
    TOURNAMENT_DRAFT(6);
    int id;

    PickMode(final int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "pick" + this.id;
    }
}
