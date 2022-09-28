package hotstone.variants.EpsilonStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinnerStrategy;
import hotstone.standard.StandardHotStoneGame;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.Map.entry;

public class EpisilonStoneWinnerStrategy implements WinnerStrategy {
    private HashMap<Player,Integer> playersAttackSum = new HashMap<>();

    public EpisilonStoneWinnerStrategy() {
        playersAttackSum.put(Player.FINDUS,0);
        playersAttackSum.put(Player.PEDDERSEN,0);
    }

    @Override
    public Player calculateWinner(Game game) {
        StandardHotStoneGame standardGame = ((StandardHotStoneGame) game);
        if(playersAttackSum.get(Player.FINDUS) > 7) {
            return Player.FINDUS;
        } else if(playersAttackSum.get(Player.PEDDERSEN) > 7) {
            return Player.PEDDERSEN;
        }
        return null;
    }

    @Override
    public void attackingMinionsAttackValue(Player who, int value) {
        playersAttackSum.computeIfPresent(who, (k,v) -> v + value);
    }


}
