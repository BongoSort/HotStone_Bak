package hotstone.variants;

import hotstone.framework.FatigueDamage;

public class FatigueDamageBetaStone implements FatigueDamage {


    @Override
    public int calculateFatigueDamage() {
        return 2;
    }
}
