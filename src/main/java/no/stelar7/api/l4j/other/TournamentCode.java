package no.stelar7.api.l4j.other;

import java.util.Base64;

import no.stelar7.api.l4j.dto.general.Map;
import no.stelar7.api.l4j.dto.general.PickMode;
import no.stelar7.api.l4j.dto.general.SpectatorMode;
import lombok.NonNull;
import lombok.experimental.Builder;

@Builder
public class TournamentCode
{

    private static final String BASE = "pvpnet://lol/customgame/joinorcreate/";
    @NonNull
    private final Map           withMap;
    @NonNull
    private final PickMode      withPickMode;
    @NonNull
    private final SpectatorMode withSpectatorMode;
    @NonNull
    private final String        withMatchName;
    private final String        withMatchPassword;
    private final String        withMatchIdentifier;
    private final String        withCallbackUrl;
    @NonNull
    private Integer             withTeamSize;

    public static TournamentCode basic()
    {
        return TournamentCode.builder().withMap(Map.SUMMONERS_RIFT_SUMMER).withPickMode(PickMode.BLIND_PICK).withSpectatorMode(SpectatorMode.ALL).withMatchName("genericmatch").withMatchIdentifier("game3").withCallbackUrl("404.com").withTeamSize(5).build();
    }

    private String base64(final String string)
    {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    private String generateJSON()
    {
        return "{\"name\":\"" + this.withMatchName + "\",\"extra\":\"" + this.withMatchIdentifier + "\",\"password\":\"" + this.withMatchPassword + "\",\"report\":\"" + this.withCallbackUrl + "\"}";
    }

    @Override
    public String toString()
    {
        this.withTeamSize = this.withTeamSize > 5 ? 5 : this.withTeamSize < 1 ? 1 : this.withTeamSize;
        return TournamentCode.BASE + this.withMap + "/" + this.withPickMode + "/team" + this.withTeamSize + "/" + this.withSpectatorMode + "/" + this.base64(this.generateJSON());
    }
}
