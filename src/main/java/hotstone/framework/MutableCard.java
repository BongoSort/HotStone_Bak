package hotstone.framework;

public interface MutableCard extends Card {

    void setManaCost(int manaCost);

    void setAttack(int attack);

    void increaseAttack(int amount);

    void setHealth(int health);

    /** Reduces health from a minion
     * @param health the amount of health reduced from initial healthvalue
     */
    void reduceHealth(int health);

    /** Set a cards status
     * @param isActive sets a card to be active (true) or inactive (false)
     */
    void setActive(Boolean isActive);

    void setEffectDescription(String effectDescription);
}
