/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hotstone.standard;

import hotstone.framework.*;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.framework.strategies.ManaProductionStrategy;
import hotstone.framework.strategies.WinnerStrategy;

import java.util.ArrayList;
import java.util.HashMap;

/** This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 *
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management.
 *
 * Start solving the AlphaStone exercise by
 * following the TDD rhythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 *
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */
public class StandardHotStoneGame implements Game {
  private Player playerInTurn;
  private ManaProductionStrategy manaProduction;
  private WinnerStrategy winnerStrategy;
  private HeroStrategy heroStrategy;

  private DeckStrategy deckStrategy;
  private int turnCounter;
  private HashMap<Player,ArrayList<Card>> playerDecks = new HashMap<>();
  private HashMap<Player,ArrayList<Card>> playerHands = new HashMap<>();
  private HashMap<Player,ArrayList<Card>> playerFields = new HashMap<>();
  private HashMap<Player, Hero> playerHero = new HashMap<>();

  /**
   * Initializes a new HotStone game
   * Also initializes heroes decks, hands and fields.
   */
  public StandardHotStoneGame(ManaProductionStrategy manaProduction, WinnerStrategy winnerStrategy, HeroStrategy heroStrategy, DeckStrategy deckStrategy) {
    this.manaProduction = manaProduction;
    this.winnerStrategy = winnerStrategy;
    this.heroStrategy = heroStrategy;
    this.deckStrategy = deckStrategy;
    this.playerInTurn = Player.FINDUS;
    //initializing turnCounter
    this.turnCounter = 0;

    //initializing Findus Hero
    playerHero.put(Player.FINDUS, new StandardHotStoneHero(Player.FINDUS,true,
            manaProduction.calculateMana(turnCounter), heroStrategy.getType(Player.FINDUS)));

    //initializing Peddersen Hero
    playerHero.put(Player.PEDDERSEN, new StandardHotStoneHero(Player.PEDDERSEN,false,manaProduction.calculateMana(turnCounter), heroStrategy.getType(Player.PEDDERSEN)));

    //initializing deck for Findus
    playerDecks.put(Player.FINDUS,this.deckStrategy.deckInitialization(Player.FINDUS));
    //initializing deck for Peddersen
    playerDecks.put(Player.PEDDERSEN,this.deckStrategy.deckInitialization(Player.PEDDERSEN));

    //initializing starting Hand for Findus
    playerHands.put(Player.FINDUS,makeHand(Player.FINDUS));
    //initializing starting Hand for Peddersen
    playerHands.put(Player.PEDDERSEN,makeHand(Player.PEDDERSEN));

    //initializing Field for Findus
    playerFields.put(Player.FINDUS, new ArrayList<>());
    //initializing Field for Peddersen
    playerFields.put(Player.PEDDERSEN, new ArrayList<>());
  }

  /**
   * Makes the start hand of a player
   * @param who Player which hand is being made
   * @return the complete start hand
   */
  private ArrayList<Card> makeHand(Player who) {
    ArrayList<Card> hand = new ArrayList<>();
    for(int i = 0 ; i < 3 ; i++) {
      Card card = playerDecks.get(who).remove(0);
      hand.add(card);
    }
    return hand;
  }

  @Override
  public Player getPlayerInTurn() {
    return playerInTurn;
  }

  @Override
  public Hero getHero(Player who) {
    return playerHero.get(who);
  }

  @Override
  public Player getWinner() {
    return winnerStrategy.calculateWinner(this);
  }

  @Override
  public int getTurnNumber() {
    return turnCounter;
  }

  @Override
  public int getDeckSize(Player who) {
    return playerDecks.get(who).size();
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return playerHands.get(who).get(indexInHand);
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    return playerHands.get(who);
  }

  @Override
  public int getHandSize(Player who) {
    return playerHands.get(who).size();
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return playerFields.get(who).get(indexInField);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    return playerFields.get(who);
  }

  @Override
  public int getFieldSize(Player who) {
    return playerFields.get(who).size();
  }

  @Override
  public void endTurn() {
    playerInTurn = Utility.computeOpponent(playerInTurn);
    turnCounter++;
    drawCard(playerInTurn);
    setupHeroForNewTurn(playerInTurn);
    setupFieldForNewTurn(playerInTurn);
  }

  /**
   * Sets each minion in the chosen players field to be active
   * @param who The player who owns the field.
   */
  private void setupFieldForNewTurn(Player who) {
   for(Card c : getField(who)) {
    setMinionActive(c,true);
   }
  }

  /**
   * Sets up the hero to be active and resets its mana
   * @param who the player for the hero.
   */
  private void setupHeroForNewTurn(Player who){
    StandardHotStoneHero hero = castHeroToStandardHotStoneHero(getHero(who));
    hero.setActive(true);
    hero.setMana(manaProduction.calculateMana(turnCounter));
  }

  /**
   *  Draws a card from the deck and puts it in the players hand
   *  @param who the player that draws the card
   */
  private void drawCard(Player who) {
    boolean playersDeckSizeisGreaterThan0 = playerDecks.get(who).size() > 0;
    if(!playersDeckSizeisGreaterThan0) {
      reduceHeroHealth(who,2);
    } else {
      Card res = playerDecks.get(who).remove(0);
      playerHands.get(who).add(0,res);
    }
  }

  @Override
  public Status playCard(Player who, Card card) { //TODO: denne skal refaktoreres
    Status status = canCardBeUsed(who,card);
    boolean statusIsOk = status == Status.OK;
    if(!statusIsOk) {
      return status;
    }
    boolean heroHasEnoughMana = getHero(who).getMana() >= card.getManaCost();
    if(!heroHasEnoughMana) {
      return Status.NOT_ENOUGH_MANA;
    }

    playerHands.get(who).remove(card);
    playerFields.get(who).add(0,card);
    castHeroToStandardHotStoneHero(getHero(who)).reduceHeroMana(card.getManaCost());
    return status;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    Status status = canAttackBeDone(playerAttacking,attackingCard,defendingCard);

    boolean statusIsOk = status == Status.OK;
    if(!statusIsOk) { return status; }

    executeAttack(attackingCard, defendingCard);

    return status;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    Player playerBeingAttacked = Utility.computeOpponent(playerAttacking);
    //Active minion attacks the opposite players hero
    Status status = canCardBeUsed(playerAttacking, attackingCard);

    boolean statusIsOk = status == Status.OK;
    if(!statusIsOk) {
      return status;
    }
    boolean attackingCardIsActive = attackingCard.isActive();
    if(!attackingCardIsActive) {
      return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    }
    //Opposite players hero take damage equivalent to the minions attack value
    reduceHeroHealth(playerBeingAttacked,attackingCard.getAttack());

    //Minion is then set to inactive state
    setMinionActive(attackingCard,false);
    return Status.OK;
  }

  private void reduceHeroHealth(Player who, int damage) {
    castHeroToStandardHotStoneHero(getHero(who)).reduceHealth(damage);
  }


  @Override
  public Status usePower(Player who) {
    Status status = canHeroPowerBeUsed(who);

    boolean heroIsAllowedToUsePower = status == Status.OK;
    if (!heroIsAllowedToUsePower) {
      return status;
    }

    executeHeroPower(who);

    return status;
  }

  /**
   * Executing the hero power, spending mana and setting hero to inactive.
   * @param who the player that uses the heropower
   */
  private void executeHeroPower(Player who) {
    StandardHotStoneHero hero = castHeroToStandardHotStoneHero(playerHero.get(who));
    heroStrategy.useHeroPower(this,who);
    hero.reduceHeroMana(GameConstants.HERO_POWER_COST);
    hero.setActive(false);
  }

  /**
   * A validation method for checking whether a Card(Minion) can attack another Card
   * @param playerAttacking The attacking player
   * @param attackingCard The Card(Minion) doing the attack
   * @param defendingCard The Card(Minion) being attacked
   * @return the Status of the validation
   */
  private Status canAttackBeDone(Player playerAttacking, Card attackingCard, Card defendingCard) {
    Status status = canCardBeUsed(playerAttacking, attackingCard);

    boolean cardCanBeUsed = status == Status.OK;
    if(!cardCanBeUsed) { return status; }

    boolean playerIsDefendingCardsOwner = playerAttacking == defendingCard.getOwner();
    if (playerIsDefendingCardsOwner) { return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION; }

    boolean attackingCardIsActive = attackingCard.isActive();
    if (! attackingCardIsActive) { return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION; }

    return status;
  }

  /**
   * A validation method used to check whether you can use a card or not
   * @param who The Player trying to use a Card
   * @param card The Card being used.
   * @return the Status of the validation.
   */
  private Status canCardBeUsed(Player who, Card card) {
    boolean playerIsOwner =  who == card.getOwner();
    if(!playerIsOwner) { return Status.NOT_OWNER; }

    boolean playerIsInTurn = playerInTurn == who;
    if(!playerIsInTurn) { return Status.NOT_PLAYER_IN_TURN; }

    return Status.OK;
  }

  /**
   * Checks if the hero is allowed to use its hero power.
   * @param who the player who uses the hero power
   * @return status
   */
  private Status canHeroPowerBeUsed(Player who) {
    boolean PlayerIsInTurn = playerInTurn == who;
    if(!PlayerIsInTurn) { return Status.NOT_PLAYER_IN_TURN; }

    boolean heroIsActive = getHero(who).isActive();
    if(!heroIsActive) { return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND; }

    boolean heroHasEnoughManaToUsePower = getHero(who).getMana() >= GameConstants.HERO_POWER_COST;
    if (!heroHasEnoughManaToUsePower) { return Status.NOT_ENOUGH_MANA; }

    return Status.OK;
  }

  /**
   * Executes an attack where a card attacks another card.
   * Attacking minion is set to inactive. Both minions health is recalculated, dead minions removed from the field.
   * @param attackingCard the attacking card
   * @param defendingCard the defending card
   */
  private void executeAttack(Card attackingCard, Card defendingCard) {
    reduceMinionHealth(defendingCard,attackingCard);
    reduceMinionHealth(attackingCard,defendingCard);

    setMinionActive(attackingCard,false);

    removeCardIfMinionIsDead(defendingCard);
    removeCardIfMinionIsDead(attackingCard);
  }

  private void setMinionActive(Card card, boolean active) {
    castCardToStandardHotStoneCard(card).setActive(active);
  }

  /**
   * Reduces a minions health
   * @param minionLosingHealth The minion losing health
   * @param minionAttacking The minion attacking
   */
  private void reduceMinionHealth(Card minionLosingHealth, Card minionAttacking) {
    castCardToStandardHotStoneCard(minionLosingHealth).reduceHealth(minionAttacking.getAttack());
  }

  /**
   * Removes a card(minion) from the field if the card has 0 or less health
   * @param card The minion on the field
   */
  private void removeCardIfMinionIsDead(Card card) {
    boolean cardHasMoreThan0Health = card.getHealth() > 0;
    if(!cardHasMoreThan0Health) {
      playerFields.get(card.getOwner()).remove(card);
    }
  }

  /**  Casting a hero to StandardHotStoneHero
   * @param hero the hero being casted
   * @return the casted hero
   */
  private StandardHotStoneHero castHeroToStandardHotStoneHero(Hero hero) {
    return (StandardHotStoneHero) hero;
  }

  /**  Casting a card to StandardHotStoneCard
   *
   * @param card the card being casted
   * @return the casted card
   */
  private StandardHotStoneCard castCardToStandardHotStoneCard(Card card) {
    return (StandardHotStoneCard) card;
  }
}
