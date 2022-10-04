package hotstone.variants.ZetaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;
import hotstone.variants.BetaStone.BetaStoneWinnerStrategy;
import hotstone.variants.EpsilonStone.EpsilonStoneWinnerStrategy;

public class ZetaStoneWinnerStrategy implements WinnerStrategy {
    private WinnerStrategy betaStoneWinnerStrategy = new BetaStoneWinnerStrategy();
    private WinnerStrategy epsilonWinnerStrategy = new EpsilonStoneWinnerStrategy();

    @Override
    public Player calculateWinner(Game game) {
        return getStrategyBasedOnTurnNumber(game.getTurnNumber()).calculateWinner(game);
    }

    @Override
    public void attackingMinionsAttackValue(Player who, Game game, int value) {
        getStrategyBasedOnTurnNumber(game.getTurnNumber()).attackingMinionsAttackValue(who, game, value);
    }

    private WinnerStrategy getStrategyBasedOnTurnNumber(int turnNumber) {
        if(turnNumber <= 11) {
            return betaStoneWinnerStrategy;
        } else {
            return epsilonWinnerStrategy;
        }
    }
}
