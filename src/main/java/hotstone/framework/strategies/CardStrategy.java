package hotstone.framework.strategies;

import hotstone.framework.Card;
import hotstone.framework.Player;

import java.util.ArrayList;

public interface CardStrategy {

    /**
     * Initializes the deck of a player
     * @param who is the player who owns the deck
     * @return the deck
     */
    ArrayList<Card> deckInitialization(Player who);

    /**
     * Initializes the hand of a player
     * @param deck is the deck used for the starting hand.
     * @return the starting hand
     */
    ArrayList<Card> handInitialization(ArrayList<Card> deck);
}
