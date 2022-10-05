package hotstone.framework.strategies;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;

public interface CardEffectStrategy {
    void useCardEffect(Game game, Player who, Card card);
}
