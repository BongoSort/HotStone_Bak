package hotstone.variants.DeltaStone;

import hotstone.framework.strategies.ManaProductionStrategy;

public class DeltaStoneManaProductionStrategy implements ManaProductionStrategy {
    @Override
    public int calculateMana(int turnCounter) {
        return 7;
    }
}
