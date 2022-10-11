package hotstone.utility;

import hotstone.framework.strategies.*;
import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;
import hotstone.framework.strategies.IndexDecisionStrategies.RandomIndexStrategy;
import hotstone.variants.BetaStone.BetaStoneManaProductionStrategy;
import hotstone.variants.BetaStone.BetaStoneWinnerStrategy;
import hotstone.variants.EtaStone.EtaStoneCardEffectStrategy;
import hotstone.variants.EtaStone.EtaStoneDeckStrategy;
import hotstone.variants.SemiStone.SemiStoneConcreteFactory;
import hotstone.variants.SemiStone.SemiStoneHeroStrategy;

public class TestSemiStoneConcreteFactory extends SemiStoneConcreteFactory {

    private FixedIndexStrategy fixedIndexStrategy;

    public TestSemiStoneConcreteFactory(FixedIndexStrategy fixedIndexStrategy) {
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


}
