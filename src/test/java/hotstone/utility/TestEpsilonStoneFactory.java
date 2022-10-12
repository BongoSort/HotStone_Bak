package hotstone.utility;

import hotstone.framework.strategies.*;
import hotstone.variants.EpsilonStone.EpsilonStoneConcreteFactory;
import hotstone.variants.EpsilonStone.EpsilonStoneHeroStrategy;

public class TestEpsilonStoneFactory extends EpsilonStoneConcreteFactory {

    private FixedIndexStrategy fixedIndexStrategy;

    public TestEpsilonStoneFactory(FixedIndexStrategy fixedIndexStrategy) {
        this.fixedIndexStrategy = fixedIndexStrategy;
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new EpsilonStoneHeroStrategy(fixedIndexStrategy);
    }
}