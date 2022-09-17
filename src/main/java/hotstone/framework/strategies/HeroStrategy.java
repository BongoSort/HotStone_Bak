package hotstone.framework.strategies;

import hotstone.framework.Game;
import hotstone.framework.Player;

public interface HeroStrategy {
    String getType(Player who);

    void useHeroPower(Game game, Player who);
}
