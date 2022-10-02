package hotstone.variants.AlphaStone;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;

import java.util.ArrayList;

public class AlphaStoneDeckStrategy implements DeckStrategy {

    @Override
    public ArrayList<Card> deckInitialization(Player who) {
        ArrayList<Card> deck = new ArrayList<>();
        deck.add(new StandardHotStoneCard(GameConstants.TRES_CARD,who,3,3,3));
        deck.add(new StandardHotStoneCard(GameConstants.DOS_CARD,who,2,2,2));
        deck.add(new StandardHotStoneCard(GameConstants.UNO_CARD,who,1,1,1));
        deck.add(new StandardHotStoneCard(GameConstants.CUATRO_CARD, who,2,3,1));
        deck.add(new StandardHotStoneCard(GameConstants.CINCO_CARD, who,3,5,1));
        deck.add(new StandardHotStoneCard(GameConstants.SEIS_CARD, who,2,1,3));
        deck.add(new StandardHotStoneCard(GameConstants.SIETE_CARD, who,3,2,4));
        return deck;
    }
}
