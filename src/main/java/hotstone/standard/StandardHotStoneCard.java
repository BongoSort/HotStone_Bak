package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

import java.util.Objects;

public class StandardHotStoneCard implements Card {
    private String cardName;
    private int manaCost;
    private int attack;
    private int health;
    private boolean isActive;
    private Player owner;


    public StandardHotStoneCard(String cardName, Player owner) {
        this.cardName = cardName;
        this.isActive = false;
        this.owner = owner;
        initializeCardFromSpanishDeck(cardName);
    }

    private void initializeCardFromSpanishDeck(String cardName) {
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
            case GameConstants.CUATRO_CARD -> {
                this.manaCost = 2;
                this.attack = 3;
                this.health = 1;
            }
            case GameConstants.CINCO_CARD -> {
                this.manaCost = 3;
                this.attack = 5;
                this.health = 1;
            }
            case GameConstants.SEIS_CARD -> {
                this.manaCost = 2;
                this.attack = 1;
                this.health = 3;
            }
            case GameConstants.SIETE_CARD -> {
                this.manaCost = 3;
                this.attack = 2;
                this.health = 4;
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

    public void reduceHealth(int health) {
        this.health -= health;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    /** Set a cards status
     * @param isActive sets a card to be active (true) or inactive (false)
     */
    public void SetActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
}
