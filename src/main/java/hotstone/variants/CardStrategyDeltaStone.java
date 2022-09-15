package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.CardStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;
import java.util.*;

public class CardStrategyDeltaStone implements CardStrategy {

    @Override
    public ArrayList<Card> deckInitialization(Player who) {
        ArrayList<Card> deck = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            deck.add(new StandardHotStoneCard(GameConstants.BROWN_RICE_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.FRENCH_FRIES_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.GREEN_SALAD_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.TOMATO_SALAD_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.POKE_BOWL_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.PUMPKIN_SOUP_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.NOODLE_SOUP_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.SPRING_ROLLS_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.BAKED_SALMON_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.CHICKEN_CURRY_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.BEEF_BURGER_CARD, who));
            deck.add(new StandardHotStoneCard(GameConstants.FILET_MIGNON_CARD, who));
        }
        return deck;
    }

    @Override
    public ArrayList<Card> handInitialization(ArrayList<Card> deck) {
        return null;
    }
}
