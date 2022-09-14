package hotstone.framework.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;

public interface Winner {

    Player calculateWinner(StandardHotStoneGame game);

}
