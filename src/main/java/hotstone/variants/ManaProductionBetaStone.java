package hotstone.variants;

import hotstone.framework.ManaProduction;
import hotstone.standard.GameConstants;

public class ManaProductionBetaStone implements ManaProduction {

    @Override
    public int startMana() {
        return 1;
    }

    @Override
    public int calculateManaForTurn(int noOfTurns) {
        if(noOfTurns <= 1) {
            return 1;
        }else if(16 <= noOfTurns) {
            return 7;
        }
        return noOfTurns / 2;
    }
}
