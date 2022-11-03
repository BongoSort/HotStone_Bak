package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.variants.PersonalizedDeckStrategy;
import hotstone.variants.ZetaStone.ZetaStoneDeckStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import thirdparty.PersonalDeckReader;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPersonalizedDeck {
    private DeckStrategy personalizedDeckStrategy;
    private PersonalDeckReader personalDeckReader;

    @BeforeEach
    public void setUp() {
        personalDeckReader = new PersonalDeckReader("animaldeck.json");
        personalizedDeckStrategy = new PersonalizedDeckStrategy(personalDeckReader);
    }


    @Test
    public void aPersonalizedDeckSizeIs16() {
        ArrayList<Card> deck = personalizedDeckStrategy.deckInitialization(Player.FINDUS);
        assertThat(deck.size(), is(16));
    }

    @Test
    public void WhenPersonalizedDeckIsAnimalDeckItContainsTwoOfEachCardFromTheDeck() {
        ArrayList<Card> deck = personalizedDeckStrategy.deckInitialization(Player.FINDUS);
        int res = (int) deck.stream().filter(card -> card.getName().equals("Fish")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Cat")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Cow")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Dog")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Bird")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Horse")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Pig")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Sheep")).count();
        assertThat(res, is(2));
    }

    @Test
    public void WhenPersonalizedDeckIsNotAnimalDeckItContainsTwoOfEachCardFromTheList() {
        personalDeckReader = new PersonalDeckReader("norsegoddeck.json");
        personalizedDeckStrategy = new PersonalizedDeckStrategy(personalDeckReader);

        ArrayList<Card> deck = personalizedDeckStrategy.deckInitialization(Player.FINDUS);
        int res = (int) deck.stream().filter(card -> card.getName().equals("Loki")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Heimdallur")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Hel")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Sif")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Baldur")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Freyja")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Thor")).count();
        assertThat(res, is(2));

        res = (int) deck.stream().filter(card -> card.getName().equals("Odin")).count();
        assertThat(res, is(2));
    }

    @Test
    public void afterDeckInitializationFirstCardInDeckShouldHaveManaCost1() {
        ArrayList<Card> deck = personalizedDeckStrategy.deckInitialization(Player.FINDUS);
        assertThat(deck.get(0).getManaCost(), is(1));
    }


    @Test
    public void afterDeckInitializationFirstCardInDeckShouldHaveManaCost2OrLower() {
        ArrayList<Card> deck = personalizedDeckStrategy.deckInitialization(Player.FINDUS);
        assertThat(deck.get(1).getManaCost() <= 2, is(true));
    }


    @Test
    public void afterDeckInitializationFirstCardInDeckShouldHaveManaCost4OrLower() {
        ArrayList<Card> deck = personalizedDeckStrategy.deckInitialization(Player.FINDUS);
        assertThat(deck.get(2).getManaCost() <= 4, is(true));
    }
}
