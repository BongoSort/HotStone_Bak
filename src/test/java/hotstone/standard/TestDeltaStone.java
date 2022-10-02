package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.framework.strategies.ManaProductionStrategy;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.AlphaStoneHeroStrategy;
import hotstone.variants.AlphaStone.AlphaStoneWinnerStrategy;
import hotstone.variants.DeltaStone.AlternatingDishDeckStrategy;
import hotstone.variants.DeltaStone.DeltaStoneManaProductionStrategy;
import hotstone.variants.DeltaStone.StandardDishDeckStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeltaStone {
    private Game game;
    private DeckStrategy deckStrategy;
    private ManaProductionStrategy manaProductionStrategy;

    @BeforeEach
    public void setUp() {
        DeckStrategy dishDeckStrategy = new StandardDishDeckStrategy();
        deckStrategy = new AlternatingDishDeckStrategy(dishDeckStrategy);
        manaProductionStrategy = new DeltaStoneManaProductionStrategy();
        game = new StandardHotStoneGame(manaProductionStrategy, new AlphaStoneWinnerStrategy(), new AlphaStoneHeroStrategy(), deckStrategy);

    }

    //Unit tests for cardStrategy
    @Test
    public void CardBrownRiceShouldHaveAttributes1_1_2() {
        Card card = new StandardHotStoneCard(GameConstants.BROWN_RICE_CARD,Player.FINDUS, 1,1,2);
        assertThat(card.getManaCost(), is(1));
        assertThat(card.getAttack(),is(1));
        assertThat(card.getHealth(),is(2));
    }
    @Test
    public void CardFrenchFriesShouldHaveAttributes1_2_1() {
        Card card = new StandardHotStoneCard(GameConstants.FRENCH_FRIES_CARD,Player.FINDUS,1,2,1);
        assertThat(card.getManaCost(), is(1));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(1));
    }
    @Test
    public void CardGreenSaladShouldHaveAttributes2_2_3() {
        Card card = new StandardHotStoneCard(GameConstants.GREEN_SALAD_CARD,Player.FINDUS,2,2,3);
        assertThat(card.getManaCost(), is(2));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(3));
    }
    @Test
    public void CardTomatoSaladShouldHaveAttributes2_3_2() {
        Card card = new StandardHotStoneCard(GameConstants.TOMATO_SALAD_CARD,Player.FINDUS,2,3,2);
        assertThat(card.getManaCost(), is(2));
        assertThat(card.getAttack(),is(3));
        assertThat(card.getHealth(),is(2));
    }
    @Test
    public void CardPokeBowlShouldHaveAttributes3_2_4() {
        Card card = new StandardHotStoneCard(GameConstants.POKE_BOWL_CARD,Player.FINDUS,3,2,4);
        assertThat(card.getManaCost(), is(3));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(4));
    }
    @Test
    public void CardPumpkinSoupShouldHaveAttributes4_2_7() {
        Card card = new StandardHotStoneCard(GameConstants.PUMPKIN_SOUP_CARD,Player.FINDUS,4,2,7);
        assertThat(card.getManaCost(), is(4));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(7));
    }
    @Test
    public void CardNoodleSoupShouldHaveAttributes4_5_3() {
        Card card = new StandardHotStoneCard(GameConstants.NOODLE_SOUP_CARD,Player.FINDUS,4,5,3);
        assertThat(card.getManaCost(), is(4));
        assertThat(card.getAttack(),is(5));
        assertThat(card.getHealth(),is(3));
    }
    @Test
    public void CardSpringRollsShouldHaveAttributes5_3_7() {
        Card card = new StandardHotStoneCard(GameConstants.SPRING_ROLLS_CARD,Player.FINDUS,5,3,7);
        assertThat(card.getManaCost(), is(5));
        assertThat(card.getAttack(),is(3));
        assertThat(card.getHealth(),is(7));
    }
    @Test
    public void CardBakedSalmonShouldHaveAttributes5_8_2() {
        Card card = new StandardHotStoneCard(GameConstants.BAKED_SALMON_CARD,Player.FINDUS,5,8,2);
        assertThat(card.getManaCost(), is(5));
        assertThat(card.getAttack(),is(8));
        assertThat(card.getHealth(),is(2));
    }
    @Test
    public void CardChickenCurryShouldHaveAttributes6_8_4() {
        Card card = new StandardHotStoneCard(GameConstants.CHICKEN_CURRY_CARD,Player.FINDUS,6,8,4);
        assertThat(card.getManaCost(), is(6));
        assertThat(card.getAttack(),is(8));
        assertThat(card.getHealth(),is(4));
    }
    @Test
    public void CardBeefBurgerShouldHaveAttributes6_5_6() {
        Card card = new StandardHotStoneCard(GameConstants.BEEF_BURGER_CARD,Player.FINDUS,6,5,6);
        assertThat(card.getManaCost(), is(6));
        assertThat(card.getAttack(),is(5));
        assertThat(card.getHealth(),is(6));
    }
    @Test
    public void CardFiletMignonShouldHaveAttributes7_9_5() {
        Card card = new StandardHotStoneCard(GameConstants.FILET_MIGNON_CARD,Player.FINDUS, 7,9,5);
        assertThat(card.getManaCost(), is(7));
        assertThat(card.getAttack(),is(9));
        assertThat(card.getHealth(),is(5));
    }

    //Deck Unit Tests
    @Test
    public void deckShouldContain24Cards() {
        assertThat(deckStrategy.deckInitialization(Player.FINDUS).size(), is(24));
    }

    @Test
    public void aDeckShouldContainTwoOfEachCardFromDishDeck() {
        ArrayList<Card> deck = deckStrategy.deckInitialization(Player.FINDUS);
        int res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.BROWN_RICE_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.FRENCH_FRIES_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.GREEN_SALAD_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.TOMATO_SALAD_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.POKE_BOWL_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.PUMPKIN_SOUP_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.NOODLE_SOUP_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.SPRING_ROLLS_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.BAKED_SALMON_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.CHICKEN_CURRY_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.BEEF_BURGER_CARD)).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals(GameConstants.FILET_MIGNON_CARD)).count();
        assertThat(res, is(2));
    }

    //UnitTest for ManaProduction
    @Test
    public void eachPlayerStartsWith7Mana() {
        assertThat(manaProductionStrategy.calculateMana(0),is(7));
    }

    @Test
    public void inTurn4ManaAvailableIs7(){
        assertThat(manaProductionStrategy.calculateMana(3), is(7));
    }

    //Non-unit Tests
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

    @Test
    public void EachPlayersDeckSize21AtStartOfGame() {
        //Given a game each player's deck should contain 21 Cards
        assertThat(game.getDeckSize(Player.FINDUS), is(21));
        assertThat(game.getDeckSize(Player.PEDDERSEN), is(21));
    }
    @Test
    public void firstCardInHandShouldBeOneManaCostForFindus() {
        assertThat(game.getCardInHand(Player.FINDUS, 0).getManaCost(), is(1));
    }

    @Test
    public void firstCardInHandShouldBeOneManaCostForPeddersen() {
        assertThat(game.getCardInHand(Player.PEDDERSEN, 0).getManaCost(), is(1));
    }
    @Test
    public void secondCardInHandShouldBeTwoOrLessManaCostForFindus() {
        assertThat(game.getCardInHand(Player.FINDUS, 1).getManaCost() <= 2,is(true));
    }

    @Test
    public void secondCardInHandShouldBeTwoOrLessManaCostForPeddersen() {
        assertThat(game.getCardInHand(Player.PEDDERSEN, 1).getManaCost() <= 2 ,is(true));
    }

    @Test
    public void thirdCardInHandShouldBeFourOrLessManaCostForFindus() {
        assertThat(game.getCardInHand(Player.FINDUS, 2).getManaCost() <= 4 ,is(true));
    }

    @Test
    public void thirdCardInHandShouldBeFourOrLessManaCostForPeddersen() {
        assertThat(game.getCardInHand(Player.PEDDERSEN, 2).getManaCost() <= 4,is(true));
    }

    @Test
    public void findusPlaysFirstCardShouldHaveSixManaLeft() {
        // Given a DeltaGame
        // When findus plays a one mana card
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0));
        // Then findus should have six mana left
        assertThat(game.getHero(Player.FINDUS).getMana(), is(6));
    }

    @Test
    public void peddersenPlaysSecondCardShouldHaveSixManaLeft() {
        // Given a DeltaGame
        // When it is peddersens turn
        game.endTurn();
        // Peddersen plays a one mana card
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, 1));
        // Then Peddersen should have six mana left
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(6));
    }
}
