package hotstone.variants.EtaStone;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;
import hotstone.variants.DeltaStone.DeltaStoneDeckStrategy;

import java.util.ArrayList;

public class EtaStoneDeckStrategy implements DeckStrategy {
    @Override
    public ArrayList<Card> deckInitialization(Player who) {
        ArrayList<Card> deck = new DeltaStoneDeckStrategy().deckInitialization(who);

        deck.forEach(card -> adjustCardAttributes((StandardHotStoneCard) card));

        return deck;
    }

    private void adjustCardAttributes(StandardHotStoneCard card) {
        switch (card.getName()) {
            case GameConstants.BROWN_RICE_CARD -> {
                card.setAttack(1);
            }
            case GameConstants.TOMATO_SALAD_CARD, GameConstants.POKE_BOWL_CARD -> {
                card.setAttack(2);
            }
            case GameConstants.NOODLE_SOUP_CARD -> {
                card.setAttack(5);
            }
            case GameConstants.CHICKEN_CURRY_CARD -> {
                card.setAttack(4);
            }
            case GameConstants.BEEF_BURGER_CARD -> {
                card.setAttack(8);
            }
        }
    }
}
