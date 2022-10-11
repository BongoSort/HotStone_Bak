package hotstone.variants.GammaStone;

import hotstone.framework.strategies.*;
import hotstone.variants.AlphaStone.AlphaStoneCardEffectStrategy;
import hotstone.variants.AlphaStone.AlphaStoneDeckStrategy;
import hotstone.variants.AlphaStone.AlphaStoneManaProductionStrategy;
import hotstone.variants.AlphaStone.AlphaStoneWinnerStrategy;

public class GammaStoneConcreteFactory implements AbstractFactory {
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
        return new GammaStoneHeroStrategy();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new AlphaStoneManaProductionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new AlphaStoneWinnerStrategy();
    }
}
