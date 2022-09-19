package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.strategies.CardStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;

import java.util.ArrayList;

public class AlphaStoneCardStrategy implements CardStrategy {

    @Override
    public ArrayList<Card> deckInitialization(Player who) {
        ArrayList<Card> deck = new ArrayList<>();
        deck.add(new StandardHotStoneCard(GameConstants.TRES_CARD,who));
        deck.add(new StandardHotStoneCard(GameConstants.DOS_CARD,who));
        deck.add(new StandardHotStoneCard(GameConstants.UNO_CARD,who));
        deck.add(new StandardHotStoneCard(GameConstants.CUATRO_CARD, who));
        deck.add(new StandardHotStoneCard(GameConstants.CINCO_CARD, who));
        deck.add(new StandardHotStoneCard(GameConstants.SEIS_CARD, who));
        deck.add(new StandardHotStoneCard(GameConstants.SIETE_CARD, who));
        return deck;
    }

    @Override
    public ArrayList<Card> handInitialization(ArrayList<Card> deck) {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(deck.remove(0));
        hand.add(deck.remove(0));
        hand.add(deck.remove(0));
        return hand;
    }
}
