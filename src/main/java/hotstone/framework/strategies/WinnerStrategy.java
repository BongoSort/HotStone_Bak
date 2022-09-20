package hotstone.framework.strategies;

import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;

public interface WinnerStrategy {
    /**
     * Calculates the winner of the game
     * @param game is the current game
     * @return the player who won
     */
    Player calculateWinner(StandardHotStoneGame game);

}
