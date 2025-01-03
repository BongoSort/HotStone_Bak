package hotstone.variants.EtaStone;

import hotstone.framework.Card;
import hotstone.framework.MutableCard;
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

        deck.forEach(card -> adjustCardAttributes((MutableCard) card));

        return deck;
    }

    private void adjustCardAttributes(MutableCard card) {
        switch (card.getName()) {
            case GameConstants.BROWN_RICE_CARD -> {
                card.setManaCost(1);
                card.setAttack(1);
                card.setHealth(1);
                card.setEffectDescription(GameConstants.BROWN_RICE_EFFECT_DESCRIPTION);
            }
            case GameConstants.TOMATO_SALAD_CARD -> {
                card.setManaCost(2);
                card.setAttack(2);
                card.setHealth(2);
                card.setEffectDescription(GameConstants.TOMATO_SALAD_EFFECT_DESCRIPTION);

            }
            case GameConstants.POKE_BOWL_CARD -> {
                card.setManaCost(3);
                card.setAttack(2);
                card.setHealth(3);
                card.setEffectDescription(GameConstants.POKE_BOWL_EFFECT_DESCRIPTION);

            }
            case GameConstants.NOODLE_SOUP_CARD -> {
                card.setManaCost(4);
                card.setAttack(5);
                card.setHealth(3);
                card.setEffectDescription(GameConstants.NOODLE_SOUP_EFFECT_DESCRIPTION);

            }
            case GameConstants.CHICKEN_CURRY_CARD -> {
                card.setManaCost(6);
                card.setAttack(4);
                card.setHealth(4);
                card.setEffectDescription(GameConstants.CHICKEN_CURRY_EFFECT_DESCRIPTION);

            }
            case GameConstants.BEEF_BURGER_CARD -> {
                card.setManaCost(6);
                card.setAttack(8);
                card.setHealth(6);
                card.setEffectDescription(GameConstants.BEEF_BURGER_EFFECT_DESCRIPTION);
            }
        }
    }
}
