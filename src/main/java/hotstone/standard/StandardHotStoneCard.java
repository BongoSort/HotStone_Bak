package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class StandardHotStoneCard implements Card {
    private String cardName;

    public StandardHotStoneCard(String cardName) {
        this.cardName = cardName;
        switch (cardName) {
            case GameConstants.UNO_CARD -> {
                this.manaCost = 1;
                this.attack = 1;
                this.health = 1;
            }
            case GameConstants.DOS_CARD -> {
                this.manaCost = 2;
                this.attack = 2;
                this.health = 2;
            }
            case GameConstants.TRES_CARD -> {
                this.manaCost = 3;
                this.attack = 3;
                this.health = 3;
            }
        }
    }

    @Override
    public String getName() {
        return cardName;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
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
