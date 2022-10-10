package hotstone.utility;

import hotstone.framework.strategies.*;
import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;
import hotstone.framework.strategies.IndexDecisionStrategies.RandomIndexStrategy;
import hotstone.variants.AlphaStone.AlphaStoneCardEffectStrategy;
import hotstone.variants.AlphaStone.AlphaStoneDeckStrategy;
import hotstone.variants.AlphaStone.AlphaStoneManaProductionStrategy;
import hotstone.variants.EpsilonStone.EpsilonStoneHeroStrategy;
import hotstone.variants.EpsilonStone.EpsilonStoneWinnerStrategy;

public class TestEpsilonStoneFactoryStrategy implements FactoryStrategy {

    private FixedIndexStrategy fixedIndexStrategy = new FixedIndexStrategy();

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
        return new EpsilonStoneHeroStrategy(fixedIndexStrategy);
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new AlphaStoneManaProductionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new EpsilonStoneWinnerStrategy();
    }

    public FixedIndexStrategy getFixedIndexStrategy() {
        return fixedIndexStrategy;
    }
}