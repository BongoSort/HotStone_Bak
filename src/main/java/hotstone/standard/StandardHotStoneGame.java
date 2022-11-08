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
import hotstone.framework.strategies.*;
import hotstone.observer.GameObserver;
import hotstone.observer.Observable;
import hotstone.observer.ObserverHandler;

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
public class StandardHotStoneGame implements Game, MutableGame {
  private Player playerInTurn;
  private ManaProductionStrategy manaProductionStrategy;
  private WinnerStrategy winnerStrategy;
  private HeroStrategy heroStrategy;
  private DeckStrategy deckStrategy;
  private CardEffectStrategy cardEffectStrategy;
  private int turnNumber;
  private HashMap<Player,ArrayList<Card>> playerDecks = new HashMap<>();
  private HashMap<Player,ArrayList<Card>> playerHands = new HashMap<>();
  private HashMap<Player,ArrayList<Card>> playerFields = new HashMap<>();
  private HashMap<Player, MutableHero> playerHero = new HashMap<>();
  private ObserverHandler observerHandler;


  /**
   * Initializes a new HotStone game
   * Also initializes heroes decks, hands and fields.
   */
  public StandardHotStoneGame(AbstractFactory abstractFactory) {
    this.manaProductionStrategy = abstractFactory.createManaProductionStrategy();
    this.winnerStrategy = abstractFactory.createWinnerStrategy();
    this.heroStrategy = abstractFactory.createHeroStrategy();
    this.deckStrategy = abstractFactory.createDeckStrategy();
    this.cardEffectStrategy = abstractFactory.createCardEffectStrategy();
    this.playerInTurn = Player.FINDUS;
    this.turnNumber = 0;
    this.observerHandler = new ObserverHandler();

    initializeDeckHeroHandAndFieldForPlayer(Player.FINDUS);
    initializeDeckHeroHandAndFieldForPlayer(Player.PEDDERSEN);
  }

  private void initializeDeckHeroHandAndFieldForPlayer(Player who) {
    playerHero.put(who, new StandardHotStoneHero(who, manaProductionStrategy.calculateMana(turnNumber), heroStrategy.getType(who)));
    playerDecks.put(who, deckStrategy.deckInitialization(who));
    playerFields.put(who, new ArrayList<>());
    playerHands.put(who,new ArrayList<>());
    makeHand(who);
  }

  /**
   * Makes the start hand of a player
   * @param who Player which hand is being made
   */
  private void makeHand(Player who) {
    for(int i = 0 ; i < 3 ; i++) {
      drawCard(who);
    }
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
    return turnNumber;
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
    observerHandler.notifyTurnChangeTo(playerInTurn);

    turnNumber++;
    drawCard(playerInTurn);
    setupHeroForNewTurn(playerInTurn);
    setupMinionsOnFieldForNewTurn(playerInTurn);

    observerHandler.notifyHeroUpdate(playerInTurn);
  }

  /**
   * Sets each minion on the chosen players field to be active
   * @param who The player who owns the field.
   */
  private void setupMinionsOnFieldForNewTurn(Player who) {
    for(Card c : getField(who)) {
      setMinionActive(c,true);
    }
  }

  /**
   * Sets up the hero to be active and resets its mana
   * @param who the player for the hero.
   */
  private void setupHeroForNewTurn(Player who) {
    MutableHero hero = playerHero.get(who);
    hero.setActive(true);
    hero.setMana(manaProductionStrategy.calculateMana(turnNumber));
    observerHandler.notifyHeroUpdate(who);
  }

  @Override
  public void drawCard(Player who) {
    boolean playersDeckSizeIsGreaterThanZero = playerDecks.get(who).size() > 0;
    if(!playersDeckSizeIsGreaterThanZero) {
      reduceHeroHealth(who, GameConstants.HERO_HEALTH_PENALTY_ON_EMPTY_DECK);
    } else {
      Card res = playerDecks.get(who).remove(0);
      playerHands.get(who).add(0,res);

      observerHandler.notifyCardDraw(who,res);
    }
  }

  @Override
  public Status playCard(Player who, Card card) {
    Status status = canCardBeUsed(who, card);
    boolean statusIsOk = status == Status.OK;
    if(!statusIsOk) {
      return status;
    }
    boolean heroHasEnoughMana = getHero(who).getMana() >= card.getManaCost();
    if(!heroHasEnoughMana) {
      return Status.NOT_ENOUGH_MANA;
    }

    reduceHeroMana(who, card.getManaCost());
    cardEffectStrategy.useCardEffect(this,who,card);
    addNewCardToField(who, card);

    for(Card c : getField(who)) {
      observerHandler.notifyCardUpdate(c);
    }

    Player opponent = Utility.computeOpponent(who);
    for(Card c : getField(opponent)) {
      observerHandler.notifyCardUpdate(c);
    }

    observerHandler.notifyPlayCard(who, card);

    return status;
  }

  /**
   * Removes a card from the hand and puts it in the field
   * @param who the current player's hand
   * @param card the card, that is removed from the hand and put onto the field
   */
  private void addNewCardToField(Player who, Card card){
    playerHands.get(who).remove(card);
    playerFields.get(who).add(0,card);
  }

  /**
   * Reduces the hero's mana
   * @param who the player who owns the hero
   * @param manaCost the value that's subtracted from the hero's mana.
   */
  private void reduceHeroMana(Player who, int manaCost){
    playerHero.get(who).reduceMana(manaCost);
    observerHandler.notifyHeroUpdate(who);
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    Status status = canAttackBeDone(playerAttacking,attackingCard,defendingCard);
    boolean statusIsOk = status == Status.OK;
    if(!statusIsOk) { return status; }

    executeAttack(attackingCard, defendingCard);

    winnerStrategy.attackingMinionsAttackValue(playerAttacking, this, attackingCard.getAttack());

    observerHandler.notifyAttackCard(playerAttacking,attackingCard,defendingCard);

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

    observerHandler.notifyAttackHero(playerAttacking, attackingCard);
    observerHandler.notifyHeroUpdate(playerBeingAttacked);

    return Status.OK;
  }

  /**
   * Reduces a hero's health
   * @param who which player the hero belongs to
   * @param value the value the health is reduced by
   */
  private void reduceHeroHealth(Player who, int value) {
    playerHero.get(who).reduceHealth(value);

    observerHandler.notifyHeroUpdate(who);
  }


  @Override
  public Status usePower(Player who) {
    Status status = canHeroPowerBeUsed(who);

    boolean heroIsAllowedToUsePower = status == Status.OK;
    if (!heroIsAllowedToUsePower) {
      return status;
    }

    executeHeroPower(who);
    observerHandler.notifyUsePower(who);
    return status;
  }

  @Override
  public void removeCardFromFieldIfHealthIsZeroOrBelow(Card card) {
    boolean cardHasZeroOrBelowHealth = card.getHealth() <= 0;
    if(cardHasZeroOrBelowHealth) {
      playerFields.get(card.getOwner()).remove(card);
      observerHandler.notifyCardRemove(card.getOwner(),card);
    }
  }


  /**
   * Executing the hero power, spending mana and setting hero to inactive.
   * @param who the player that uses the heropower
   */
  private void executeHeroPower(Player who) {
    MutableHero hero = playerHero.get(who);
    heroStrategy.useHeroPower(this,who);
    hero.reduceMana(GameConstants.HERO_POWER_COST);
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
    if(! cardCanBeUsed) { return status; }

    boolean playerAttackingIsDefendingCardsOwner = playerAttacking == defendingCard.getOwner();
    if (playerAttackingIsDefendingCardsOwner) { return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION; }

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
    reduceMinionHealth(defendingCard,attackingCard.getAttack());
    reduceMinionHealth(attackingCard,defendingCard.getAttack());

    setMinionActive(attackingCard,false);

    removeCardFromFieldIfHealthIsZeroOrBelow(defendingCard);
    removeCardFromFieldIfHealthIsZeroOrBelow(attackingCard);
  }

  private void setMinionActive(Card card, boolean active) {
    ((MutableCard) card).setActive(active);
    observerHandler.notifyCardUpdate(card);
  }

  /**
   * Reduces a minions health
   * @param minion The minion losing health
   * @param amount The amount of health minion is losing
   */
  private void reduceMinionHealth(Card minion, int amount) {
    observerHandler.notifyCardUpdate(minion);
    ((MutableCard) minion).reduceHealth(amount);
  }

  /**
   * Add an observer to a game.
   *
   * @param observer the observer to add
   */
  @Override
  public void addObserver(GameObserver observer) {
    observerHandler.addObserver(observer);
  }
}
