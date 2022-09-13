package hotstone.variants;

import hotstone.framework.ManaProduction;

public class ManaProductionAlphaStone implements ManaProduction {

    @Override
    public int startMana() {
        return 3;
    }

    @Override
    public int calculateManaForTurn(int noOfTurns) {
        return 3;
    }
}
