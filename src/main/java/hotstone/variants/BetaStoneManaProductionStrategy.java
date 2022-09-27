package hotstone.variants;

import hotstone.framework.strategies.ManaProductionStrategy;

public class BetaStoneManaProductionStrategy implements ManaProductionStrategy {

    /**
     *
     * @param turnCounter the turn the calculation uses. Starting at 0 for turn one, 1 for turn two, ect.
     * @return The amount of mana available at start of turn based on turnCounter.
     */
    @Override
    public int calculateMana(int turnCounter) {
        boolean turnCounterIsGreaterToOrEqualTo14 = 14 <= turnCounter;
        if(turnCounterIsGreaterToOrEqualTo14) {
            return 7;
        }
        return (turnCounter / 2) + 1;
    }
}
