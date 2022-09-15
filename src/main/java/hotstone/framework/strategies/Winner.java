package hotstone.framework.strategies;

import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;

public interface Winner {

    Player calculateWinner(StandardHotStoneGame game);

}
