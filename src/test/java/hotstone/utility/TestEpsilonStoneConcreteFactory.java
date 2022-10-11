package hotstone.utility;

import hotstone.framework.strategies.*;
import hotstone.variants.AlphaStone.AlphaStoneCardEffectStrategy;
import hotstone.variants.AlphaStone.AlphaStoneDeckStrategy;
import hotstone.variants.AlphaStone.AlphaStoneManaProductionStrategy;
import hotstone.variants.EpsilonStone.EpsilonStoneConcreteFactory;
import hotstone.variants.EpsilonStone.EpsilonStoneHeroStrategy;
import hotstone.variants.EpsilonStone.EpsilonStoneWinnerStrategy;

public class TestEpsilonStoneConcreteFactory extends EpsilonStoneConcreteFactory {

    private FixedIndexStrategy fixedIndexStrategy;

    public TestEpsilonStoneConcreteFactory(FixedIndexStrategy fixedIndexStrategy) {
        this.fixedIndexStrategy = fixedIndexStrategy;
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new EpsilonStoneHeroStrategy(fixedIndexStrategy);
    }
}