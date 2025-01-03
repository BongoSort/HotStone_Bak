package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.AbstractFactory;
import hotstone.framework.strategies.ManaProductionStrategy;
import hotstone.utility.TestHelper;
import hotstone.variants.BetaStone.BetaStoneConcreteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestBetaStone {
    private Game game;
    private ManaProductionStrategy manaProduction;

    @BeforeEach
    public void setUp() {
        AbstractFactory betaStoneAbstractFactory = new BetaStoneConcreteFactory();
        manaProduction = betaStoneAbstractFactory.createManaProductionStrategy();
        game = new StandardHotStoneGame(betaStoneAbstractFactory);
    }

    //Unit tests for manaProductionStrategy in BetaStone
    @Test
    public void turnCounter0ManaShouldbe1() {
        assertThat(manaProduction.calculateMana(0),is(1));
    }

    @Test
    public void turnCounter1ManaShouldBe1() {
        assertThat(manaProduction.calculateMana(1), is(1));
    }
    @Test
    public void turnCounter2ManaShouldBe1() {
        assertThat(manaProduction.calculateMana(2),is(2));
    }

    @Test
    public void turnCounter6ManaShouldBe() {
        assertThat(manaProduction.calculateMana(6),is(4));
    }

    @Test
    public void turnCounter14ManaShouldBe7() {
        assertThat(manaProduction.calculateMana(14),is(7));
    }

    @Test
    public void turnCounter15ManaShouldBe7() {
        assertThat(manaProduction.calculateMana(15),is(7));
    }

    //tests for BetaStone Game variant.
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
        TestHelper.advanceGameNRounds(game,1);
        assertThat(game.getHero(Player.FINDUS).getMana(),is(2));
    }

    @Test
    public void peddersenShouldHave2ManaInTurn4() {
        TestHelper.advanceGameNRounds(game,1);
        game.endTurn();
        assertThat(game.getHero(Player.FINDUS).getMana(),is(2));
    }

    @Test
    public void findusShouldHave4ManaInRound4() {
        TestHelper.advanceGameNRounds(game,3);
        assertThat(game.getHero(Player.FINDUS).getMana(),is(4));
    }

    @Test public void peddersenShouldHave4ManaInHisRound4() {
        TestHelper.advanceGameNRounds(game,3);
        game.endTurn();
        assertThat(game.getHero(Player.PEDDERSEN).getMana(),is(4));
    }

    @Test
    public void findusShouldHave7ManaInTurn10() {
        TestHelper.advanceGameNRounds(game,9);
        assertThat(game.getHero(Player.FINDUS).getMana(),is(7));
    }

    @Test
    public void peddersenShouldHave7ManaInTurn10() {
        TestHelper.advanceGameNRounds(game,9);
        assertThat(game.getHero(Player.FINDUS).getMana(),is(7));
    }


    @Test
    public void findusUnusedManaDoesNotCarryOverToHisNextTurn() {
        TestHelper.advanceGameNRounds(game, 1);
        assertThat(game.getHero(Player.FINDUS).getMana(), is(2));
    }

    @Test
    public void findusPlayedCardUno1ManaRemainingShouldNotCarryOverToHisNextTurn() {
        TestHelper.advanceGameNRounds(game,1);
        game.playCard(Player.PEDDERSEN,game.getCardInHand(Player.FINDUS,3));
        TestHelper.advanceGameNRounds(game,1);
        assertThat(game.getHero(Player.FINDUS).getMana(),is(3));
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

    @Test
    public void ifFindusHealthIsZeroOrBelowPeddersenWins() {
        TestHelper.fieldUnoDosTresCuatroForFindusAndPeddersen(game);
        ((StandardHotStoneHero) game.getHero(Player.FINDUS)).reduceHealth(GameConstants.HERO_MAX_HEALTH - 1);
        game.endTurn();
        Card peddersensCard = game.getCardInField(Player.PEDDERSEN,0);
        game.attackHero(Player.PEDDERSEN,peddersensCard);
        assertThat(game.getWinner(),is(Player.PEDDERSEN));
    }

    @Test
    public void whenBothHeroesHaveHealthAboveZeroNobodyWins() {
        assertThat(game.getWinner() == null, is(true));
    }

    @Test
    public void ifPeddersenHealthIsZeroOrBelowFindusWins() {
        TestHelper.fieldUnoDosTresCuatroForFindusAndPeddersen(game);
        ((StandardHotStoneHero) game.getHero(Player.PEDDERSEN)).reduceHealth(GameConstants.HERO_MAX_HEALTH);
        Card findusCard = game.getCardInField(Player.FINDUS,0);
        game.attackHero(Player.FINDUS,findusCard);
        assertThat(game.getWinner(),is(Player.FINDUS));
    }



}
