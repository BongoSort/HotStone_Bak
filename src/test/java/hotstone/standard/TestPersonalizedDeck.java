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

    @Test
    public void WhenPersonalizedDeckIsAnimalDeckItContainsTwoOfEachCardFromTheDeck() {
        personalDeckReader = new PersonalDeckReader("animaldeck.json");
        personalizedDeckStrategy = new PersonalizedDeckStrategy(personalDeckReader);

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
}
