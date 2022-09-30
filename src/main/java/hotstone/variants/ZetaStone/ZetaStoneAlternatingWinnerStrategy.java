package hotstone.variants.ZetaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;
import hotstone.variants.BetaStone.BetaStoneWinnerStrategy;
import hotstone.variants.EpsilonStone.EpisilonStoneWinnerStrategy;

public class ZetaStoneAlternatingWinnerStrategy implements WinnerStrategy {
    private WinnerStrategy winsByHealthDepletion = new BetaStoneWinnerStrategy();
    private WinnerStrategy winsByMinionToMinionAttackOutput = new EpisilonStoneWinnerStrategy();

    @Override
    public Player calculateWinner(Game game) {
        if(game.getTurnNumber() > 11) {
            return winsByMinionToMinionAttackOutput.calculateWinner(game);
        } else {
            return winsByHealthDepletion.calculateWinner(game);
        }
    }

    @Override
    public void attackingMinionsAttackValue(Player who, int value) {
        winsByMinionToMinionAttackOutput.attackingMinionsAttackValue(who,value);
    }
}
