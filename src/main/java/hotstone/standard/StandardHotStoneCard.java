package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.MutableCard;
import hotstone.framework.Player;

import java.util.ArrayList;
import java.util.UUID;

public class StandardHotStoneCard implements Card, MutableCard {
    private String cardId;
    private String cardName;
    private int manaCost;
    private int attack;
    private int health;
    private boolean isActive;
    private Player owner;

    private String effectDescription;

    public StandardHotStoneCard(String cardName, Player owner, int manaCost, int attack, int health, String effectDescription) {
        this(cardName, owner, manaCost, attack, health);
        this.effectDescription = effectDescription;
    }

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
        this.cardId = UUID.randomUUID().toString();
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

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void increaseAttack(int amount) {
        attack += amount;
    }

    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void reduceHealth(int health) {
        this.health -= health;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getEffectDescription() {
        return effectDescription;
    }

    @Override
    public String getId() {
        return cardId;
    }
}
