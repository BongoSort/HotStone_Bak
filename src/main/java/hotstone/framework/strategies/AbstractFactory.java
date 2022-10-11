package hotstone.framework.strategies;

public interface AbstractFactory {

    CardEffectStrategy createCardEffectStrategy();

    DeckStrategy createDeckStrategy();

    HeroStrategy createHeroStrategy();

    ManaProductionStrategy createManaProductionStrategy();

    WinnerStrategy createWinnerStrategy();
}
