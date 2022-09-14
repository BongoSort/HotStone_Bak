package hotstone.variants;

import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.framework.variants.Winner;
import hotstone.standard.StandardHotStoneGame;

public class WinnerAlphaStone implements Winner {

    @Override
    public Player calculateWinner(StandardHotStoneGame game) {
        if(game.getTurnNumber() == 8) {
          return Player.FINDUS;
        } else {
            return null;
        }
    }
}
