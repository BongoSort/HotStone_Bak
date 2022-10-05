package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

import java.util.ArrayList;

public class StandardHotStoneCard implements Card {
    private String cardName;
    private int manaCost;
    private int attack;
    private int health;
    private boolean isActive;
    private Player owner;

    /** Constructor for a card
     *
     * @param cardName the name of the card
     * @param owner which player the card belongs to
     */
    public StandardHotStoneCard(String cardName, Player owner, int manaCost, int attack, int health) {
        this.cardName = cardName;
        this.isActive = false;
        this.owner = owner;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
    }

    @Override
    public String getName() {
        return cardName;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void increaseAttack(int amount) {
        attack += amount;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /** Reduces health from a minion
     * @param health the amount of health reduced from initial healthvalue
     */
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
    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
}
