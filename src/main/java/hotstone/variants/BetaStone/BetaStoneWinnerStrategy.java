package hotstone.variants.BetaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;

public class BetaStoneWinnerStrategy implements WinnerStrategy {

    @Override
    public Player calculateWinner(Game game) {
        boolean findusHeroHealthIsBelowOrEqualTo0 = game.getHero(Player.FINDUS).getHealth() <= 0;
        boolean peddersenHeroHealthIsBelowOrEqualTo0 = game.getHero(Player.PEDDERSEN).getHealth() <= 0;
        if(findusHeroHealthIsBelowOrEqualTo0) {
            return Player.PEDDERSEN;
        } else if(peddersenHeroHealthIsBelowOrEqualTo0) {
            return Player.FINDUS;
        }
        return null;
    }

    @Override
    public void attackingMinionsAttackValue(Player who, Game game, int value) {

    }
}
