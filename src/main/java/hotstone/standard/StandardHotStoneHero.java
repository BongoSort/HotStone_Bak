package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StandardHotStoneHero implements Hero {
    private int health;
    private Player owner;
    private String type;
    private boolean isActive;
    private int mana;

    public StandardHotStoneHero(Player owner, boolean isActive) {
        this.owner = owner;
        this.type = GameConstants.BABY_HERO_TYPE;
        this.isActive = isActive;
        this.mana = 3;
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

    public void reduceHealth(int attack) {
        this.health -= attack;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public void setStatus(Boolean active) {
        this.isActive = active;
    }

    public void reduceHeroMana(int mana) {
        this.mana -= mana;
    }

    public void resetMana() {
        this.mana = 3;
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
