package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class StandardHotStoneCard implements Card {
    private String cardName;

    public StandardHotStoneCard(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String getName() {
        return cardName;
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Player getOwner() {
        return null;
    }
}
