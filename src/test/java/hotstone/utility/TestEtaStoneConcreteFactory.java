package hotstone.utility;

import hotstone.framework.strategies.*;
import hotstone.variants.AlphaStone.AlphaStoneHeroStrategy;
import hotstone.variants.AlphaStone.AlphaStoneWinnerStrategy;
import hotstone.variants.DeltaStone.DeltaStoneManaProductionStrategy;
import hotstone.variants.EtaStone.EtaStoneCardEffectStrategy;
import hotstone.variants.EtaStone.EtaStoneConcreteFactory;

public class TestEtaStoneConcreteFactory extends EtaStoneConcreteFactory {

    private FixedIndexStrategy fixedIndexStrategy = new FixedIndexStrategy();

    @Override
    public CardEffectStrategy createCardEffectStrategy() {
        return new EtaStoneCardEffectStrategy(fixedIndexStrategy);
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new TestDishDeckStrategy();
    }

    public FixedIndexStrategy getFixedIndexStrategy() {
        return fixedIndexStrategy;
    }
}