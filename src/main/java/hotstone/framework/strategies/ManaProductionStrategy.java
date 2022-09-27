package hotstone.framework.strategies;

public interface ManaProductionStrategy {

    /** Calculates the mana for a hero
     * @param turnNumber the turn the calculation uses. Starting at 0 for turn one, 1 for turn two, ect.
     * @return the mana
     */
    int calculateMana(int turnNumber);

}
