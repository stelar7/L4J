package my.api.stelar7.usewith.lol.other;

import java.util.Base64;

import lombok.NonNull;
import lombok.experimental.Builder;
import my.api.stelar7.usewith.lol.dto.general.Map;
import my.api.stelar7.usewith.lol.dto.general.PickMode;
import my.api.stelar7.usewith.lol.dto.general.SpectatorMode;

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
