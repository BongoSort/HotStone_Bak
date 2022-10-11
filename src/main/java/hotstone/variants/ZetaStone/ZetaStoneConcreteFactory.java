package hotstone.variants.ZetaStone;

import hotstone.framework.strategies.*;
import hotstone.variants.AlphaStone.AlphaStoneCardEffectStrategy;
import hotstone.variants.AlphaStone.AlphaStoneHeroStrategy;
import hotstone.variants.AlphaStone.AlphaStoneManaProductionStrategy;

public class ZetaStoneConcreteFactory implements AbstractFactory {

    @Override
    public CardEffectStrategy createCardEffectStrategy() {
        return new AlphaStoneCardEffectStrategy();
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new ZetaStoneDeckStrategy();
    }

    @Override
    public HeroStrategy createHeroStrategy() {
        return new AlphaStoneHeroStrategy();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new AlphaStoneManaProductionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ZetaStoneWinnerStrategy();
    }
}
