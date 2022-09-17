package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.CardStrategy;
import hotstone.framework.strategies.ManaProductionStrategy;
import hotstone.utility.TestHelper;
import hotstone.variants.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeltaStone {
    private Game game;
    private CardStrategy cardStrategy;
    private ManaProductionStrategy manaProductionStrategy;

    @BeforeEach
    public void setUp() {
        cardStrategy = new DeltaStoneCardStrategy();
        manaProductionStrategy = new DeltaStoneManaProduction();
        game = new StandardHotStoneGame(manaProductionStrategy, new AlphaStoneWinnerStrategy(), new AlphaStoneHeroStrategy());

    }

    //Unit tests for cardStrategy
    @Test
    public void CardBrownRiceShouldHaveAttributes1_1_2() {
        Card card = new StandardHotStoneCard(GameConstants.BROWN_RICE_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(1));
        assertThat(card.getAttack(),is(1));
        assertThat(card.getHealth(),is(2));
    }
    @Test
    public void CardFrenchFriesShouldHaveAttributes1_2_1() {
        Card card = new StandardHotStoneCard(GameConstants.FRENCH_FRIES_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(1));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(1));
    }
    @Test
    public void CardGreenSaladShouldHaveAttributes2_2_3() {
        Card card = new StandardHotStoneCard(GameConstants.GREEN_SALAD_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(2));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(3));
    }
    @Test
    public void CardTomatoSaladShouldHaveAttributes2_3_2() {
        Card card = new StandardHotStoneCard(GameConstants.TOMATO_SALAD_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(2));
        assertThat(card.getAttack(),is(3));
        assertThat(card.getHealth(),is(2));
    }
    @Test
    public void CardPokeBowlShouldHaveAttributes3_2_4() {
        Card card = new StandardHotStoneCard(GameConstants.POKE_BOWL_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(3));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(4));
    }
    @Test
    public void CardPumpkinSoupShouldHaveAttributes4_2_7() {
        Card card = new StandardHotStoneCard(GameConstants.PUMPKIN_SOUP_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(4));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(7));
    }
    @Test
    public void CardNoodleSoupShouldHaveAttributes4_5_3() {
        Card card = new StandardHotStoneCard(GameConstants.NOODLE_SOUP_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(4));
        assertThat(card.getAttack(),is(5));
        assertThat(card.getHealth(),is(3));
    }
    @Test
    public void CardSpringRollsShouldHaveAttributes5_3_7() {
        Card card = new StandardHotStoneCard(GameConstants.SPRING_ROLLS_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(5));
        assertThat(card.getAttack(),is(3));
        assertThat(card.getHealth(),is(7));
    }
    @Test
    public void CardBakedSalmonShouldHaveAttributes5_8_2() {
        Card card = new StandardHotStoneCard(GameConstants.BAKED_SALMON_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(5));
        assertThat(card.getAttack(),is(8));
        assertThat(card.getHealth(),is(2));
    }
    @Test
    public void CardChickenCurryShouldHaveAttributes6_8_4() {
        Card card = new StandardHotStoneCard(GameConstants.CHICKEN_CURRY_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(6));
        assertThat(card.getAttack(),is(8));
        assertThat(card.getHealth(),is(4));
    }
    @Test
    public void CardBeefBurgerShouldHaveAttributes6_5_6() {
        Card card = new StandardHotStoneCard(GameConstants.BEEF_BURGER_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(6));
        assertThat(card.getAttack(),is(5));
        assertThat(card.getHealth(),is(6));
    }
    @Test
    public void CardFiletMignonShouldHaveAttributes7_9_5() {
        Card card = new StandardHotStoneCard(GameConstants.FILET_MIGNON_CARD,Player.FINDUS);
        assertThat(card.getManaCost(), is(7));
        assertThat(card.getAttack(),is(9));
        assertThat(card.getHealth(),is(5));
    }

    //Deck Unit Tests
    @Test
    public void deckShouldContain24Cards() {
        assertThat(cardStrategy.deckInitialization(Player.FINDUS).size(), is(24));
    }

    @Test
    public void handIndex0ShouldBeCardWith1ManaCost() {
        ArrayList<Card> deck = cardStrategy.deckInitialization(Player.FINDUS);
        assertThat(cardStrategy.handInitialization(deck).get(0).getManaCost(),is(1));
    }

    @Test
    public void handIndex1ShouldBeCardWith2_OrLessManaCost() {
        ArrayList<Card> deck = cardStrategy.deckInitialization(Player.FINDUS);
        assertThat(cardStrategy.handInitialization(deck).get(1).getManaCost() <= 2 ,is(true));
    }

    @Test
    public void handIndex2ShouldBeCardWith3_OrLessManaCost() {
        ArrayList<Card> deck = cardStrategy.deckInitialization(Player.FINDUS);
        assertThat(cardStrategy.handInitialization(deck).get(2).getManaCost() <= 4 ,is(true));
    }

    //UnitTest for manaproduction
    @Test
    public void eachPlayerStartsWith7Mana() {
        assertThat(manaProductionStrategy.calculateMana(0),is(7));
    }

    @Test
    public void inTurn4ManaAvailableIs7(){
        assertThat(manaProductionStrategy.calculateMana(3), is(7));
    }

    @Test
    public void eachPlayerHave7ManaInTotalAtStartOfNewGame() {
        assertThat(game.getHero(Player.FINDUS).getMana(), is(7));
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(7));
    }

    @Test
    public void eachPlayerHave7ManaInTotalInRound2() {
        TestHelper.advanceGameNRounds(game, 1);
        assertThat(game.getHero(Player.FINDUS).getMana(), is(7));
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(7));
    }

}