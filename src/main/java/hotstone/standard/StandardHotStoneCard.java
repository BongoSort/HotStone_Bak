package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

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
    public StandardHotStoneCard(String cardName, Player owner) {
        this.cardName = cardName;
        this.isActive = false;
        this.owner = owner;
        initializeCardFromSpanishDeck(cardName);
        initializeSpecialCard(cardName);
        initializeDishDeck(cardName);
    }

    /** Initializes the Spanish Deck
     * @param cardName is the name of the card
     */
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

    private void initializeSpecialCard(String cardName) {
        if(cardName.equals(GameConstants.SOVS_CARD)) {
            this.attack = 1;
            this.health = 1;
        }
    }

    private void initializeDishDeck(String cardName) {
        switch (cardName) {
            case GameConstants.BROWN_RICE_CARD -> {
                this.manaCost = 1;
                this.attack = 1;
                this.health = 2;
            }
            case GameConstants.FRENCH_FRIES_CARD-> {
                this.manaCost = 1;
                this.attack = 2;
                this.health = 1;
            }
            case GameConstants.GREEN_SALAD_CARD-> {
                this.manaCost = 2;
                this.attack = 2;
                this.health = 3;
            }
            case GameConstants.TOMATO_SALAD_CARD-> {
                this.manaCost = 2;
                this.attack = 3;
                this.health = 2;
            }
            case GameConstants.POKE_BOWL_CARD-> {
                this.manaCost = 3;
                this.attack = 2;
                this.health = 4;
            }
            case GameConstants.PUMPKIN_SOUP_CARD-> {
                this.manaCost = 4;
                this.attack = 2;
                this.health = 7;
            }
            case GameConstants.NOODLE_SOUP_CARD-> {
                this.manaCost = 4;
                this.attack = 5;
                this.health = 3;
            }
            case GameConstants.SPRING_ROLLS_CARD-> {
                this.manaCost = 5;
                this.attack = 3;
                this.health = 7;
            }
            case GameConstants.BAKED_SALMON_CARD-> {
                this.manaCost = 5;
                this.attack = 8;
                this.health = 2;
            }
            case GameConstants.CHICKEN_CURRY_CARD-> {
                this.manaCost = 6;
                this.attack = 8;
                this.health = 4;
            }
            case GameConstants.BEEF_BURGER_CARD-> {
                this.manaCost = 6;
                this.attack = 5;
                this.health = 6;
            }
            case GameConstants.FILET_MIGNON_CARD-> {
                this.manaCost = 7;
                this.attack = 9;
                this.health = 5;
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

    public void increaseAttack(int amount) {
        attack += amount;
    }

    @Override
    public int getHealth() {
        return health;
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
