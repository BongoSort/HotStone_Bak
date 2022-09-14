package hotstone.standard;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import hotstone.framework.Player;
import hotstone.utility.TestHelper;
import hotstone.variants.FatigueDamageBetaStone;
import hotstone.variants.ManaProductionAlphaStone;
import hotstone.variants.ManaProductionBetaStone;
import org.junit.jupiter.api.*;
import hotstone.framework.Game;
public class TestBetaStone {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new ManaProductionBetaStone(), new FatigueDamageBetaStone());
    }

    @Test
    public void findusShouldHave1ManaInTurn1() {
        assertThat(game.getHero(Player.FINDUS).getMana(),is(1));
    }

    @Test
    public void peddersenShouldHave1ManaInTurn2() {
        game.endTurn();
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(1));
    }

    @Test
    public void findusShouldHave2ManaInTurn3() {
        TestHelper.advanceGameNRounds(game,2);
        assertThat(game.getHero(Player.FINDUS).getMana(),is(2));
    }

    @Test
    public void peddersenShouldHave2ManaInTurn4() {
        TestHelper.advanceGameNRounds(game,2);
        game.endTurn();
        assertThat(game.getHero(Player.FINDUS).getMana(),is(2));
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

    @Test
    public void findusDrawsCardFromEmptyDeckShouldTakeTwoDamage() {
        TestHelper.advanceGameNRounds(game, 4);
        // Given a game and it is round 5, Findus have full health
        assertThat(game.getHero(Player.FINDUS).getHealth(), is(21));
        // Then Findus deck is empty
        assertThat(game.getDeckSize(Player.FINDUS), is(0));
        // and one more round advances
        TestHelper.advanceGameNRounds(game, 1);
        // Then Findus should take 2 damage from drawing from the empty deck
        assertThat(game.getHero(Player.FINDUS).getHealth(), is(19));
    }

}
