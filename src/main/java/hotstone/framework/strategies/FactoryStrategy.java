package hotstone.framework.strategies;

public interface FactoryStrategy {

    CardEffectStrategy createCardEffectStrategy();

    DeckStrategy createDeckStrategy();

    HeroStrategy createHeroStrategy();

    ManaProductionStrategy createManaProductionStrategy();

    WinnerStrategy createWinnerStrategy();
}
