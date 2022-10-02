package hotstone.variants.DeltaStone;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;

import java.util.ArrayList;
import java.util.Collections;

public class StandardDishDeckStrategy implements DeckStrategy {

    @Override
    public ArrayList<Card> deckInitialization(Player who) {
        ArrayList<Card> deck = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            deck.add(new StandardHotStoneCard(GameConstants.BROWN_RICE_CARD, who,1,1,2));
            deck.add(new StandardHotStoneCard(GameConstants.FRENCH_FRIES_CARD, who,1,2,1));
            deck.add(new StandardHotStoneCard(GameConstants.GREEN_SALAD_CARD, who,2,2,3));
            deck.add(new StandardHotStoneCard(GameConstants.TOMATO_SALAD_CARD, who,2,3,2));
            deck.add(new StandardHotStoneCard(GameConstants.POKE_BOWL_CARD, who,3,2,4));
            deck.add(new StandardHotStoneCard(GameConstants.PUMPKIN_SOUP_CARD, who,4,2,7));
            deck.add(new StandardHotStoneCard(GameConstants.NOODLE_SOUP_CARD, who,4,5,3));
            deck.add(new StandardHotStoneCard(GameConstants.SPRING_ROLLS_CARD, who,5,3,7));
            deck.add(new StandardHotStoneCard(GameConstants.BAKED_SALMON_CARD, who,5,8,2));
            deck.add(new StandardHotStoneCard(GameConstants.CHICKEN_CURRY_CARD, who,6,8,4));
            deck.add(new StandardHotStoneCard(GameConstants.BEEF_BURGER_CARD, who,6,5,6));
            deck.add(new StandardHotStoneCard(GameConstants.FILET_MIGNON_CARD, who,7,9,5));
        }

        Collections.shuffle(deck);
        Card firstCard = deck.stream().filter((c) -> c.getManaCost() == 1).findAny().orElse(null);
        deck.remove(firstCard);

        Card secondCard = deck.stream().filter((c) -> c.getManaCost() <= 2).findAny().orElse(null);
        deck.remove(secondCard);

        Card thirdCard = deck.stream().filter((c) -> c.getManaCost() <= 4).findAny().orElse(null);
        deck.remove(thirdCard);

        deck.add(0,thirdCard);
        deck.add(0,secondCard);
        deck.add(0,firstCard);
        return deck;
    }

}
