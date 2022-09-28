package hotstone.variants.AlphaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;
import hotstone.standard.StandardHotStoneGame;

public class AlphaStoneWinnerStrategy implements WinnerStrategy {

    @Override
    public Player calculateWinner(Game game) {
        boolean turnNumberIsEight = game.getTurnNumber() == 8;
        if(turnNumberIsEight) {
          return Player.FINDUS;
        } else {
            return null;
        }
    }
}
