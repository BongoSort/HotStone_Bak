package hotstone.framework;

public interface ManaProduction {
    /** integer representing the start mana for a hero.
     * @return the start mana for a hero
     */
    int startMana();

    int calculateManaForTurn(int noOfTurns);

}
