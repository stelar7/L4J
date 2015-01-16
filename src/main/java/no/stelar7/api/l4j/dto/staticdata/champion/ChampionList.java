package no.stelar7.api.l4j.dto.staticdata.champion;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChampionList implements Serializable
{
    public enum Champion
    {
        AHRI(103),
        AKALI(84),
        ALISTAR(12),
        AMUMU(32),
        ANIVIA(34),
        ANNIE(1),
        ASHE(22),
        AZIR(268),
        BLITZCRANK(53),
        BRAND(63),
        BRAUM(201),
        CAITLYN(51),
        CASSIOPEIA(69),
        CHOGATH(31),
        CORKI(42),
        DARIUS(122),
        DIANA(131),
        DR_MUNDO(36),
        DRAVEN(119),
        ELISE(60),
        EVENLYNN(28),
        EZREAL(81),
        FIDDLESTICKS(9),
        FIORA(114),
        FIZZ(105),
        GALIO(3),
        GANGPLANK(41),
        GAREN(86),
        GNAR(150),
        GRAGAS(79),
        GRAVES(104),
        HECARIM(120),
        HEIMERDINGER(74),
        IRELIA(39),
        JANNA(40),
        JARVAN_IV(59),
        JAX(24),
        JAYCE(126),
        JINX(222),
        KARMA(43),
        KARTHUS(30),
        KASSADIN(38),
        KATARINA(55),
        KAYLE(10),
        KENNEN(85),
        KHAZIX(121),
        KOGMAW(96),
        LEBLANC(7),
        LEE_SIN(64),
        LEONA(89),
        LISSANDRA(127),
        LUCIAN(236),
        LULU(117),
        LUX(99),
        MALPHITE(54),
        MALZAHAR(90),
        MAOKAI(57),
        MASTER_YI(11),
        MISS_FORTUNE(21),
        MORDEKAISER(82),
        MOREGANA(25),
        NAMI(267),
        NASUS(75),
        NAUTILUS(111),
        NIDALEE(76),
        NOCTURNE(56),
        NUNU(20),
        OLAF(2),
        ORIANNA(61),
        PANTHEON(80),
        POPPY(78),
        QUINN(133),
        RAMMUS(33),
        REKSAI(421),
        RENEKTON(58),
        RENGAR(107),
        RIVEN(92),
        RUMBLE(68),
        RYZE(13),
        SEJUANI(113),
        SHACO(35),
        SHEN(98),
        SHYVANA(102),
        SINGED(27),
        SION(14),
        SIVIR(15),
        SKARNER(72),
        SONA(37),
        SORAKA(16),
        SWAIN(50),
        SYNDRA(134),
        TALON(91),
        TARIC(44),
        TEEMO(17),
        THRESH(412),
        TRISTANA(18),
        TRUNDLE(48),
        TRYNDAMERE(23),
        TWISTED_FATE(4),
        TWITCH(29),
        UDYR(77),
        URGOT(6),
        VARUS(110),
        VAYNE(67),
        VEIGAR(45),
        VELKOZ(161),
        VI(254),
        VIKTOR(112),
        VLADIMIR(8),
        VOLIBEAR(106),
        WARWICK(19),
        WUKONG(62),
        XERATH(101),
        XINZHAO(5),
        YASUO(157),
        YORICK(83),
        ZAC(154),
        ZED(238),
        ZIGGS(115),
        ZILEAN(26),
        ZYRA(143),
        AATROX(266);

        @Getter
        int id;

        Champion(final int id)
        {
            this.id = id;
        }

        public static Champion getById(final String key)
        {
            for (final Champion c : Champion.values())
            {
                if (c.id == Integer.parseInt(key))
                {
                    return c;
                }
            }
            return null;
        }
    }

    Map<String, Champion> data;
    String                format;
    Map<String, String>   keys;
    String                type;
    String                version;
}
