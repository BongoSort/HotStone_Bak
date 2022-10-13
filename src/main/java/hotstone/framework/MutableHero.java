package hotstone.framework;

public interface MutableHero extends Hero{

    /**
     *  Reduce the health of a hero equal to the amount specified
     * @param amount the amount of health the hero should lose
     */
    void reduceHealth(int amount);

    void increaseHealth(int amount);

    /** Changes status of a hero
     * An active hero can use its heropower, an inactive hero cannot use its heropower
     * @param active sets a hero to be active (true) or inactive (false)
     */
    void setActive(Boolean active);

    /** Reduce the mana of a hero
     *
     * @param amount is the amount of mana removed from the hero
     */
    void reduceMana(int amount);

    /**
     * Sets the heros mana
     * @param amount the amount of mana the Hero should have
     */
    void setMana(int amount);

}
