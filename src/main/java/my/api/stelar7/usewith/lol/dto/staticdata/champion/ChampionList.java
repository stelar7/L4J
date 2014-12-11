package my.api.stelar7.usewith.lol.dto.staticdata.champion;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChampionList
{
    Map<String, Champion> data;
    Map<String, String>   keys;
    String                format;
    String                type;
    String                version;

    public enum Champion
    {
        
        SHACO(35),
        DR_MUNDO(36),
        IRELIA(39),
        SONA(37),
        KASSADIN(38),
        GNAR(150),
        KARMA(43),
        CORKI(42),
        GANGPLANK(41),
        JANNA(40),
        BRAUM(201),
        ASHE(22),
        TRYNDAMERE(23),
        JAX(24),
        MOREGANA(25),
        ZILEAN(26),
        SINGED(27),
        EVENLYNN(28),
        TWITCH(29),
        VELKOZ(161),
        GALIO(3),
        OLAF(2),
        ANNIE(1),
        KARTHUS(30),
        LEBLANC(7),
        URGOT(6),
        XINZHAO(5),
        AMUMU(32),
        TWISTED_FATE(4),
        CHOGATH(31),
        REKSAI(421),
        FIDDLESTICKS(9),
        VLADIMIR(8),
        WARWICK(19),
        TEEMO(17),
        TRISTANA(18),
        SIVIR(15),
        SORAKA(16),
        RYZE(13),
        SION(14),
        MASTER_YI(11),
        ALISTAR(12),
        MISS_FORTUNE(21),
        NUNU(20),
        RENGAR(107),
        VOLIBEAR(106),
        FIZZ(105),
        GRAVES(104),
        AHRI(103),
        LUX(99),
        SHYVANA(102),
        XERATH(101),
        THRESH(412),
        SHEN(98),
        JINX(222),
        KOGMAW(96),
        RIVEN(92),
        TALON(91),
        MALZAHAR(90),
        KAYLE(10),
        LEONA(89),
        GRAGAS(79),
        LULU(117),
        FIORA(114),
        POPPY(78),
        ZIGGS(115),
        UDYR(77),
        VIKTOR(112),
        SEJUANI(113),
        VARUS(110),
        NAUTILUS(111),
        DRAVEN(119),
        MORDEKAISER(82),
        YORICK(83),
        PANTHEON(80),
        EZREAL(81),
        GAREN(86),
        AKALI(84),
        KENNEN(85),
        VAYNE(67),
        JAYCE(126),
        CASSIOPEIA(69),
        LISSANDRA(127),
        RUMBLE(68),
        KHAZIX(121),
        DARIUS(122),
        HECARIM(120),
        SKARNER(72),
        LUCIAN(236),
        HEIMERDINGER(74),
        ZED(238),
        NASUS(75),
        NIDALEE(76),
        SYNDRA(134),
        JARVAN_IV(59),
        QUINN(133),
        RENEKTON(58),
        MAOKAI(57),
        NOCTURNE(56),
        KATARINA(55),
        LEE_SIN(64),
        WUKONG(62),
        AZIR(268),
        BRAND(63),
        DIANA(131),
        ELISE(60),
        NAMI(267),
        AATROX(266),
        ORIANNA(61),
        ZYRA(143),
        TRUNDLE(48),
        VEIGAR(45),
        TARIC(44),
        CAITLYN(51),
        BLITZCRANK(53),
        MALPHITE(54),
        VI(254),
        SWAIN(50),
        ZAC(154),
        ANIVIA(34),
        YASUO(157),
        RAMMUS(33);

        @Getter
        int id;

        Champion(int id)
        {
            this.id = id;
        }

        public static Champion getById(String key)
        {
            for (Champion c : Champion.values())
            {
                if (c.id == Integer.parseInt(key)) { return c; }
            }
            return null;
        }
    }
}
