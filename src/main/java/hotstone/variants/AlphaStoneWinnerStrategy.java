package hotstone.variants;

import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;
import hotstone.standard.StandardHotStoneGame;

public class AlphaStoneWinnerStrategy implements WinnerStrategy {

    @Override
    public Player calculateWinner(StandardHotStoneGame game) {
        if(game.getTurnNumber() == 8) {
          return Player.FINDUS;
        } else {
            return null;
        }
    }
}
