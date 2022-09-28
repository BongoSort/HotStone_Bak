package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StandardHotStoneHero implements Hero {
    private int health;
    private Player owner;
    private String type;
    private boolean isActive;
    private int mana;
    private String effectDescription;

    /** Constructor for a Hero
     *  @param owner the player which owns the hero
     *  @param type The type of Hero
     */
    public StandardHotStoneHero(Player owner, int mana, String type) {
        this.owner = owner;
        this.type = type;
        this.isActive = true;
        this.mana = mana;
        this.health = GameConstants.HERO_MAX_HEALTH;
        this.effectDescription = generateEffectDescription();
    }

    private String generateEffectDescription() {
        return switch (type) {
            case GameConstants.BABY_HERO_TYPE -> GameConstants.BABY_EFFECT_DESCRIPTION;
            case GameConstants.THAI_CHEF_HERO_TYPE -> GameConstants.THAI_CHEF_EFFECT_DESCRIPTION;
            case GameConstants.DANISH_CHEF_HERO_TYPE -> GameConstants.DANISH_CHEF_EFFECT_DESCRIPTION;
            case GameConstants.FRENCH_CHEF_HERO_TYPE -> GameConstants.FRENCH_CHEF_EFFECT_DESCRIPTION;
            case GameConstants.ITALIAN_CHEF_HERO_TYPE -> GameConstants.ITALIAN_CHEF_EFFECT_DESCRIPTION;
            default -> "Effect does not exist";
        };
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

    @Override
    public String getEffectDescription() {
        return effectDescription;
    }
}
