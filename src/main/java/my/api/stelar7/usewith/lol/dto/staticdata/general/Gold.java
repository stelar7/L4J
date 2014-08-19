package my.api.stelar7.usewith.lol.dto.staticdata.general;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Gold
{
    int     base;
    int     sell;
    int     total;
    boolean purchasable;
}
