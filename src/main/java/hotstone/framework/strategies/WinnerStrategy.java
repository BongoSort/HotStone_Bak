package hotstone.framework.strategies;

import hotstone.framework.Game;
import hotstone.framework.Player;

public interface WinnerStrategy {
    /**
     * Calculates the winner of the game
     * @param game is the current game
     * @return the player who won
     */
    Player calculateWinner(Game game);

}
