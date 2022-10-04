package hotstone.variants.AlphaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;

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

    @Override
    public void attackingMinionsAttackValue(Player who, Game game, int value) {

    }
}
