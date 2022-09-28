package hotstone.variants.AlphaStone;

import hotstone.framework.strategies.ManaProductionStrategy;

public class AlphaStoneManaProductionStrategy implements ManaProductionStrategy {

    @Override
    public int calculateMana(int turnCounter) {
        return 3;
    }
}
