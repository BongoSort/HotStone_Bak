package hotstone.utility;

import hotstone.framework.strategies.*;
import hotstone.variants.AlphaStone.AlphaStoneHeroStrategy;
import hotstone.variants.AlphaStone.AlphaStoneWinnerStrategy;
import hotstone.variants.DeltaStone.DeltaStoneManaProductionStrategy;
import hotstone.variants.EtaStone.EtaStoneCardEffectStrategy;

public class TestEtaStoneConcreteFactory implements AbstractFactory {

    private FixedIndexStrategy fixedIndexStrategy = new FixedIndexStrategy();

    @Override
    public CardEffectStrategy createCardEffectStrategy() {
        return new EtaStoneCardEffectStrategy(fixedIndexStrategy);
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new TestDishDeckStrategy();
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new AlphaStoneHeroStrategy();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new DeltaStoneManaProductionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new AlphaStoneWinnerStrategy();
    }

    public FixedIndexStrategy getFixedIndexStrategy() {
        return fixedIndexStrategy;
    }
}