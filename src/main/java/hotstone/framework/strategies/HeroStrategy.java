package hotstone.framework.strategies;

import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.standard.StandardHotStoneHero;

public interface HeroStrategy {
    String getType(Player who);

    void useHeroPower(Game game, Player who);
}
