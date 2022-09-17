package hotstone.framework.strategies;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;

import java.util.ArrayList;

public interface CardStrategy {

    /**
     * Initializes the deck of a player
     * @return the filled deck
     */
    ArrayList<Card> deckInitialization(Player who);

    /**
     * Initializes the hand of a player
     * @return the filled hand
     */
    ArrayList<Card> handInitialization(ArrayList<Card> deck);
}
