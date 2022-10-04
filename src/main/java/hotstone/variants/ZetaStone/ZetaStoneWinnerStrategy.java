package hotstone.variants.ZetaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;
import hotstone.variants.BetaStone.BetaStoneWinnerStrategy;
import hotstone.variants.EpsilonStone.EpisilonStoneWinnerStrategy;

public class ZetaStoneWinnerStrategy implements WinnerStrategy {
    private WinnerStrategy winsByHealthDepletion = new BetaStoneWinnerStrategy();
    private WinnerStrategy winsByMinionToMinionAttackOutput = new EpisilonStoneWinnerStrategy();

    @Override
    public Player calculateWinner(Game game) {
        return setStrategyBasedOnTurnNumber(game.getTurnNumber()).calculateWinner(game);
    }

    @Override
    public void attackingMinionsAttackValue(Player who, Game game, int value) {
        setStrategyBasedOnTurnNumber(game.getTurnNumber()).attackingMinionsAttackValue(who, game, value);
    }

    private WinnerStrategy setStrategyBasedOnTurnNumber(int turnNumber) {
        if(turnNumber < 11) {
            return winsByHealthDepletion;
        } else {
            return winsByMinionToMinionAttackOutput;
        }
    }
}
