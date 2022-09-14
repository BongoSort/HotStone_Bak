package hotstone.framework.variants;

public interface ManaProduction {

    /** Calculates the mana for a hero
     * @param turnCounter the turn the calculation uses. Starting at 0 for turn one, 1 for turn two, ect.
     * @return the mana
     */
    int calculateMana(int turnCounter);

}
