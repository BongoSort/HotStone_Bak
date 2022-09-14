package hotstone.variants;

import hotstone.framework.ManaProduction;

public class ManaProductionAlphaStone implements ManaProduction {

    @Override
    public int calculateMana(int turnCounter) {
        return 3;
    }
}
