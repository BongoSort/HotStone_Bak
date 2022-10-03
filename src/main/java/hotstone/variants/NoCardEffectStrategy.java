package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.CardEffectStrategy;

public class NoCardEffectStrategy implements CardEffectStrategy {

    @Override
    public void useCardEffect(Game game, Player who, Card card) {
        //this is supposed to do nothing. Muhahahahaha
    }
}
