package hotstone.variants.SemiStone;

import hotstone.framework.strategies.*;
import hotstone.framework.strategies.IndexDecisionStrategies.RandomIndexStrategy;
import hotstone.variants.BetaStone.BetaStoneManaProductionStrategy;
import hotstone.variants.BetaStone.BetaStoneWinnerStrategy;
import hotstone.variants.EtaStone.EtaStoneCardEffectStrategy;
import hotstone.variants.EtaStone.EtaStoneDeckStrategy;

public class SemiStoneConcreteFactory implements AbstractFactory {
    @Override
    public CardEffectStrategy createCardEffectStrategy() {
        return new EtaStoneCardEffectStrategy(new RandomIndexStrategy());
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new EtaStoneDeckStrategy();
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new SemiStoneHeroStrategy(new RandomIndexStrategy());
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new BetaStoneManaProductionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new BetaStoneWinnerStrategy();
    }
}
