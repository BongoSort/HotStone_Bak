package hotstone.utility;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;

import java.util.ArrayList;

public class TestDishDeckStrategy implements DeckStrategy {
    @Override
    public ArrayList<Card> deckInitialization(Player who) {
        ArrayList<Card> deck = new ArrayList<>();
        deck.add(new StandardHotStoneCard(GameConstants.BROWN_RICE_CARD, who,1,1,1));
        deck.add(new StandardHotStoneCard(GameConstants.TOMATO_SALAD_CARD, who,2,2,2));
        deck.add(new StandardHotStoneCard(GameConstants.POKE_BOWL_CARD, who,3,2,3));
        deck.add(new StandardHotStoneCard(GameConstants.NOODLE_SOUP_CARD, who,4,5,3));
        deck.add(new StandardHotStoneCard(GameConstants.CHICKEN_CURRY_CARD, who,6,4,4));
        deck.add(new StandardHotStoneCard(GameConstants.BEEF_BURGER_CARD, who,6,8,6));
        return deck;
    }
}
