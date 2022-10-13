package hotstone.utility;

import hotstone.framework.strategies.*;
import hotstone.variants.EtaStone.EtaStoneCardEffectStrategy;
import hotstone.variants.SemiStone.SemiStoneConcreteFactory;
import hotstone.variants.SemiStone.SemiStoneHeroStrategy;

public class TestSemiStoneFactory extends SemiStoneConcreteFactory {

    private FixedIndexStrategy fixedIndexStrategy;

    public TestSemiStoneFactory(FixedIndexStrategy fixedIndexStrategy) {
        this.fixedIndexStrategy = fixedIndexStrategy;
    }

    @Override
    public CardEffectStrategy createCardEffectStrategy() {
        return new EtaStoneCardEffectStrategy(fixedIndexStrategy);
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new SemiStoneHeroStrategy(fixedIndexStrategy);
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new TestEtaStoneDeck();
    }


}
