package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StandardHotStoneHero implements Hero {
    private Player owner;
    private String type;
    private boolean isActive;

    public StandardHotStoneHero(Player owner, boolean isActive) {
        this.owner = owner;
        this.type = GameConstants.BABY_HERO_TYPE;
        this.isActive = isActive;
    }

    @Override
    public int getMana() {
        return 0;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public void setStatus(Boolean active) {
        this.isActive = active;
    }
    //vi må gerne lave en sætter metode for isactive her.

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
}
