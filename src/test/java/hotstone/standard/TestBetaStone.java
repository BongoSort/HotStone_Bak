package hotstone.standard;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import hotstone.framework.Player;
import hotstone.utility.TestHelper;
import hotstone.variants.ManaProductionAlphaStone;
import hotstone.variants.ManaProductionBetaStone;
import org.junit.jupiter.api.*;
import hotstone.framework.Game;
public class TestBetaStone {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new ManaProductionBetaStone());
    }

    @Test
    public void findusShouldHave1ManaInTurn1() {
        assertThat(game.getHero(Player.FINDUS).getMana(),is(1));
    }

    @Test
    public void peddersenShouldHave1ManaInTurn1() {
        game.endTurn();
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(1));
    }

    @Test
    public void findusShouldHave4ManaInRound4() {
        TestHelper.advanceGameNRounds(game,4);
        assertThat(game.getHero(Player.FINDUS).getMana(),is(4));
    }

    @Test public void peddersenShouldHave4ManaInHisRound4() {
        TestHelper.advanceGameNRounds(game,4);
        game.endTurn();
        assertThat(game.getHero(Player.PEDDERSEN).getMana(),is(4));
    }

    @Test
    public void findusShouldHave7ManaInTurn10() {
        TestHelper.advanceGameNRounds(game,10);
        assertThat(game.getHero(Player.FINDUS).getMana(),is(7));
    }

    @Test
    public void peddersenShouldHave7ManaInTurn10() {
        TestHelper.advanceGameNRounds(game,10);
        assertThat(game.getHero(Player.FINDUS).getMana(),is(7));
    }

}
