package hotstone.variants;

import hotstone.framework.ManaProduction;
import hotstone.standard.GameConstants;

public class ManaProductionBetaStone implements ManaProduction {

    @Override
    public int calculateMana(int turnCounter) {
        if(turnCounter <= 1) {
            return 1;
        }else if(16 <= turnCounter) {
            return 7;
        }
        return turnCounter / 2;
    }
}
