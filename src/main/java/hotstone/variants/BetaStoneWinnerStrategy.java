package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;
import hotstone.standard.StandardHotStoneGame;

public class BetaStoneWinnerStrategy implements WinnerStrategy {

    @Override
    public Player calculateWinner(Game game) { //TODO Lav booleans her c:
        if(game.getHero(Player.FINDUS).getHealth() <= 0) {
            return Player.PEDDERSEN;
        } else if(game.getHero(Player.PEDDERSEN).getHealth() <= 0) {
            return Player.FINDUS;
        }
        return null;
    }
}
