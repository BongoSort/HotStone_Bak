package hotstone.variants.EtaStone;

import hotstone.framework.strategies.*;
import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;
import hotstone.variants.AlphaStone.AlphaStoneHeroStrategy;
import hotstone.variants.AlphaStone.AlphaStoneWinnerStrategy;
import hotstone.variants.DeltaStone.DeltaStoneManaProductionStrategy;

public class EtaStoneFactory implements FactoryStrategy {
    private IndexStrategy indexStrategy;

    public EtaStoneFactory(IndexStrategy indexStrategy) {
        this.indexStrategy = indexStrategy;
    }

    @Override
    public CardEffectStrategy createCardEffectStrategy() {
        return new EtaStoneCardEffectStrategy(indexStrategy);
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new EtaStoneDeckStrategy();
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
}