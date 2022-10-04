package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.utility.FixedIndexStrategy;
import hotstone.utility.TestDishDeckStrategy;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.AlphaStoneHeroStrategy;
import hotstone.variants.AlphaStone.AlphaStoneWinnerStrategy;
import hotstone.variants.DeltaStone.DeltaStoneManaProductionStrategy;
import hotstone.variants.EtaStone.EtaStoneCardEffectStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaStone {
    private ArrayList<Card> deck;
    private FixedIndexStrategy fixedIndexStrategy;
    private Game game;

    @BeforeEach
    public void setUp() {
        DeckStrategy deckStrategy = new TestDishDeckStrategy();
        deck = deckStrategy.deckInitialization(Player.FINDUS);
        fixedIndexStrategy = new FixedIndexStrategy();
        game = new StandardHotStoneGame(new DeltaStoneManaProductionStrategy(), new AlphaStoneWinnerStrategy(),
                new AlphaStoneHeroStrategy(), deckStrategy, new EtaStoneCardEffectStrategy(fixedIndexStrategy));
    }

    @Test
    public void CardBrownRiceShouldHaveAttributes1_1_1() {
        Card card = deck.stream().filter(c -> c.getName().equals(GameConstants.BROWN_RICE_CARD)).findAny().get();
        assertThat(card.getManaCost(), is(1));
        assertThat(card.getAttack(),is(1));
        assertThat(card.getHealth(),is(1));
    }

    @Test
    public void CardTomatoSaladShouldHaveAttributes2_2_2() {
        Card card = deck.stream().filter(c -> c.getName().equals(GameConstants.TOMATO_SALAD_CARD)).findAny().get();
        assertThat(card.getManaCost(), is(2));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(2));
    }

    @Test
    public void CardPokeBowlShouldHaveAttributes3_2_3() {
        Card card = deck.stream().filter(c -> c.getName().equals(GameConstants.POKE_BOWL_CARD)).findAny().get();
        assertThat(card.getManaCost(), is(3));
        assertThat(card.getAttack(),is(2));
        assertThat(card.getHealth(),is(3));
    }

    @Test
    public void CardNoodleSoupShouldHaveAttributes4_5_3() {
        Card card = deck.stream().filter(c -> c.getName().equals(GameConstants.NOODLE_SOUP_CARD)).findAny().get();
        assertThat(card.getManaCost(), is(4));
        assertThat(card.getAttack(),is(5));
        assertThat(card.getHealth(),is(3));
    }

    @Test
    public void CardChickenCurryShouldHaveAttributes6_4_4() {
        Card card = deck.stream().filter(c -> c.getName().equals(GameConstants.CHICKEN_CURRY_CARD)).findAny().get();
        assertThat(card.getManaCost(), is(6));
        assertThat(card.getAttack(),is(4));
        assertThat(card.getHealth(),is(4));
    }

    @Test
    public void CardBeefBurgerShouldHaveAttributes6_8_6() {
        Card card = deck.stream().filter(c -> c.getName().equals(GameConstants.BEEF_BURGER_CARD)).findAny().get();
        assertThat(card.getManaCost(), is(6));
        assertThat(card.getAttack(),is(8));
        assertThat(card.getHealth(),is(6));
    }

    @Test
    public void brownRiceCardReducesOpponentHeroHealthByOneWhenPlayed() {
        Card card = game.getCardInHand(Player.FINDUS,0);
        game.playCard(Player.FINDUS,card);
        assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(20));
    }

    @Test
    public void tomatoSaladCardAddsOneAttackToOwnRandomMinion() {
        fixedIndexStrategy.setValue(0);
        Card card = game.getCardInHand(Player.FINDUS,0);
        game.playCard(Player.FINDUS,card);

        card = game.getCardInHand(Player.FINDUS,0);
        game.playCard(Player.FINDUS,card);

        assertThat(game.getCardInField(Player.FINDUS,1).getAttack(),is(2));
    }

    @Test
    public void pokeBowlCardAdds2HealthToOwnHero() {
        ((StandardHotStoneHero) game.getHero(Player.FINDUS)).reduceHealth(2);

        Card card = game.getCardInHand(Player.FINDUS,2);
        game.playCard(Player.FINDUS,card);
        assertThat(game.getHero(Player.FINDUS).getHealth(),is(GameConstants.HERO_MAX_HEALTH));
    }

    @Test
    public void noodleSoupCardDrawsCardFromOwnDeck() {
        TestHelper.advanceGameNRounds(game,1);
        assertThat(game.getHandSize(Player.FINDUS),is(4));

        Card card = game.getCardInHand(Player.FINDUS,0);
        game.playCard(Player.FINDUS,card);
        //then a card is drawn, so Findus HandSize isn't reduced
        assertThat(game.getHandSize(Player.FINDUS),is(4));
    }

    @Test
    public void chickenCurryCardKillsRandomOpponentMinion() {
        TestHelper.advanceGameNRounds(game,1);
        game.endTurn();

        Card card = game.getCardInHand(Player.PEDDERSEN,0);
        game.playCard(Player.PEDDERSEN,card);

        assertThat(game.getFieldSize(Player.PEDDERSEN), is(1));

        game.endTurn();

        card = game.getCardInHand(Player.FINDUS,0);
        game.playCard(Player.FINDUS,card);

        //Then Peddersens minion dies
        assertThat(game.getFieldSize(Player.PEDDERSEN),is(0));

    }


    @Test
    public void beefBurgerCardShouldIncreaseRandomOpponentCardAttackBy2() {
        fixedIndexStrategy.setValue(0);
        game.endTurn();
        Card card = game.getCardInHand(Player.PEDDERSEN,1);
        game.playCard(Player.PEDDERSEN,card);
        game.endTurn();

        TestHelper.advanceGameNRounds(game,2);
        card = game.getCardInHand(Player.FINDUS,0);
        game.playCard(Player.FINDUS,card);

        //Then BrownRiceCard on peddersens Field should have attributes(1,3,1) instead of (1,1,1)
        assertThat(game.getCardInField(Player.PEDDERSEN,0).getAttack(),is(3));
    }

}
