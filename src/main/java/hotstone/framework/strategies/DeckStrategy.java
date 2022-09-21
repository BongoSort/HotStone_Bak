package hotstone.framework.strategies;

import hotstone.framework.Card;
import hotstone.framework.Player;

import java.util.ArrayList;

public interface DeckStrategy {

    /**
     * Initializes the deck of a player
     * @param who is the player who owns the deck
     * @return the deck
     */
    ArrayList<Card> deckInitialization(Player who);
}
