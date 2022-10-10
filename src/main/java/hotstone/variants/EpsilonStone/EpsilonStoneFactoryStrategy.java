package hotstone.variants.EpsilonStone;

import hotstone.framework.strategies.*;
import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;
import hotstone.variants.AlphaStone.AlphaStoneCardEffectStrategy;
import hotstone.variants.AlphaStone.AlphaStoneDeckStrategy;
import hotstone.variants.AlphaStone.AlphaStoneHeroStrategy;
import hotstone.variants.AlphaStone.AlphaStoneManaProductionStrategy;
import hotstone.variants.BetaStone.BetaStoneManaProductionStrategy;
import hotstone.variants.BetaStone.BetaStoneWinnerStrategy;

public class EpsilonStoneFactoryStrategy implements FactoryStrategy {
    IndexStrategy indexStrategy;
    public EpsilonStoneFactoryStrategy(IndexStrategy indexStrategy) {
        this.indexStrategy = indexStrategy;
    }

    @Override
    public CardEffectStrategy createCardEffectStrategy() {
        return new AlphaStoneCardEffectStrategy();
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new AlphaStoneDeckStrategy();
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new EpsilonStoneHeroStrategy(indexStrategy);
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new AlphaStoneManaProductionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new EpsilonStoneWinnerStrategy();
    }
}