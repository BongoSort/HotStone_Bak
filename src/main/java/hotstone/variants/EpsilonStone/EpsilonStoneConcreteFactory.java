package hotstone.variants.EpsilonStone;

import hotstone.framework.strategies.*;
import hotstone.framework.strategies.IndexDecisionStrategies.RandomIndexStrategy;
import hotstone.variants.AlphaStone.AlphaStoneCardEffectStrategy;
import hotstone.variants.AlphaStone.AlphaStoneDeckStrategy;
import hotstone.variants.AlphaStone.AlphaStoneManaProductionStrategy;

public class EpsilonStoneConcreteFactory implements AbstractFactory {
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
        return new EpsilonStoneHeroStrategy(new RandomIndexStrategy());
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