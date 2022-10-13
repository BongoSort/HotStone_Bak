package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;

public class StandardHotStoneHero implements Hero, MutableHero {
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

    @Override
    public void reduceHealth(int damage) {
        this.health -= damage;
    }
    @Override
    public void increaseHealth(int amount) {
        this.health += amount;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(Boolean active) {
        this.isActive = active;
    }

    @Override
    public void reduceMana(int amount) {
        this.mana -= amount;
    }

    @Override
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
