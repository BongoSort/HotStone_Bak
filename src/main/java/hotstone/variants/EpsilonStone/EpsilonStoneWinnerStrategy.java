package hotstone.variants.EpsilonStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;

import java.util.HashMap;

public class EpsilonStoneWinnerStrategy implements WinnerStrategy {
    private HashMap<Player,Integer> playersAttackSum = new HashMap<>();

    public EpsilonStoneWinnerStrategy() {
        playersAttackSum.put(Player.FINDUS,0);
        playersAttackSum.put(Player.PEDDERSEN,0);
    }

    @Override
    public Player calculateWinner(Game game) {
        if(playersAttackSum.get(Player.FINDUS) > 7) {
            return Player.FINDUS;
        } else if(playersAttackSum.get(Player.PEDDERSEN) > 7) {
            return Player.PEDDERSEN;
        }
        return null;
    }

    @Override
    public void attackingMinionsAttackValue(Player who, Game game, int value) {
        playersAttackSum.computeIfPresent(who, (k,v) -> v + value);
    }
}
