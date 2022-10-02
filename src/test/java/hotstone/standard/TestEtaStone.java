package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.variants.DeltaStone.AlternatingDishDeckStrategy;
import hotstone.variants.DeltaStone.AugmentedDishDeckStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaStone {
    private ArrayList<Card> deck;

    @BeforeEach
    public void setUp() {
        DeckStrategy dishDeckStrategy = new AugmentedDishDeckStrategy();
        DeckStrategy deckStrategy = new AlternatingDishDeckStrategy(dishDeckStrategy);
        deck = deckStrategy.deckInitialization(Player.FINDUS);
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



}
