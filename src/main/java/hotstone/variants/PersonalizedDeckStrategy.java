package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.standard.StandardHotStoneCard;
import hotstone.variants.DeltaStone.DeltaStoneDeckStrategy;
import thirdparty.CardPODO;
import thirdparty.PersonalDeckReader;

import java.util.ArrayList;

public class PersonalizedDeckStrategy implements DeckStrategy {
    PersonalDeckReader personalDeckReader;
    DeltaStoneDeckStrategy deltaStoneDeckStrategy;

    public PersonalizedDeckStrategy(PersonalDeckReader personalDeckReader) {
        this.personalDeckReader = personalDeckReader;
        this.deltaStoneDeckStrategy = new DeltaStoneDeckStrategy();
    }

    @Override
    public ArrayList<Card> deckInitialization(Player who) {
        ArrayList<Card> deck = new ArrayList<>();
        for (CardPODO c : personalDeckReader) {
            deck.add(new StandardHotStoneCard(c.name(), who, c.mana(), c.attack(), c.health()));
            deck.add(new StandardHotStoneCard(c.name(), who, c.mana(), c.attack(), c.health()));
        }

        return deltaStoneDeckStrategy.shuffleDeck(deck);
    }
}
