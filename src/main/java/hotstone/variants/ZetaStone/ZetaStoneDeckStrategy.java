package hotstone.variants.ZetaStone;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;

import java.util.ArrayList;

public class ZetaStoneDeckStrategy implements DeckStrategy {
    @Override
    public ArrayList<Card> deckInitialization(Player who) {
        ArrayList<Card> deck = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            deck.add(new StandardHotStoneCard(GameConstants.CINCO_CARD,who));
        }
        return deck;
    }
}
