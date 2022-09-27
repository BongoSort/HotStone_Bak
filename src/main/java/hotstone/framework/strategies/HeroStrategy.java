package hotstone.framework.strategies;

import hotstone.framework.Game;
import hotstone.framework.Player;

public interface HeroStrategy {
    /**
     * Method for getting the Players hero type
     * @param who Player who owns the hero
     * @return Type of hero
     */
    String getType(Player who);

    /**
     * Uses hero power of player given.
     * @param game The current game
     * @param who the player which hero is going to use its power
     */
    void useHeroPower(Game game, Player who);

}
