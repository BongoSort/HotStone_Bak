package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StandardHotStoneHero implements Hero {
    private int health;
    private Player owner;
    private String type;
    private boolean isActive;
    private int mana;

    /** Constructor for a Hero
     *  @param owner the player which owns the hero
     * @param isActive true if the hero is allowed to use its hero power, false if not.
     * @param type
     */
    public StandardHotStoneHero(Player owner, boolean isActive, int mana, String type) {
        this.owner = owner;
        this.type = type;
        this.isActive = isActive;
        this.mana = mana;
        this.health = GameConstants.HERO_MAX_HEALTH;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getHealth() {
        return health;
    }

    /** Reduce the health of a hero
     *  Hero looses health equal to the attacking minions attackvalue
     * @param damage the attackvalue of the attacking minion
     */
    public void reduceHealth(int damage) {
        this.health -= damage;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    /** Changes status of a hero
     * An active hero can use its heropower, an inactive hero cannot use its heropower
     * @param active sets a hero to be active (true) or inactive (false)
     */
    public void setActive(Boolean active) {
        this.isActive = active;
    }

    /** Reduce the mana of a hero
     *
     * @param mana is the amount of mana removed from the hero
     */
    public void reduceHeroMana(int mana) {
        this.mana -= mana;
    }

    /**
     *  Reset the mana of a hero
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
}
