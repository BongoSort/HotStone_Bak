package hotstone.variants;

import hotstone.framework.variants.FatigueDamage;
import hotstone.standard.GameConstants;

public class FatigueDamageBetaStone implements FatigueDamage {

    @Override
    public int calculateFatigueDamage() {
        return GameConstants.HERO_HEALTH_PENALTY_ON_EMPTY_DECK;
    }
}
