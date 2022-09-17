package hotstone.variants;

import hotstone.framework.strategies.ManaProductionStrategy;

public class AlphaStoneManaProduction implements ManaProductionStrategy {

    @Override
    public int calculateMana(int turnCounter) {
        return 3;
    }
}
