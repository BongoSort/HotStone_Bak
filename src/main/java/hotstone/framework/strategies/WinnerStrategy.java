package hotstone.framework.strategies;

import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;

public interface WinnerStrategy {

    Player calculateWinner(StandardHotStoneGame game);

}
