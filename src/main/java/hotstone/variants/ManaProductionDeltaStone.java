package hotstone.variants;

import hotstone.framework.strategies.ManaProductionStrategy;

public class ManaProductionDeltaStone implements ManaProductionStrategy {
    @Override
    public int calculateMana(int turnCounter) {
        return 7;
    }
}
