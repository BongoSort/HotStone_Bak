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
import hotstone.framework.strategies.CardStrategy;
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

  private CardStrategy cardStrategy;
  private int turnCounter;
  private HashMap<Player,ArrayList<Card>> playerDecks = new HashMap<>();
  private HashMap<Player,ArrayList<Card>> playerHands = new HashMap<>();
  private HashMap<Player,ArrayList<Card>> playerFields = new HashMap<>();
  private HashMap<Player, Hero> playerHero = new HashMap<>();

  /**
   * Initializes a new HotStone game
   * Also initializes heroes decks, hands and fields.
   */
  public StandardHotStoneGame(ManaProductionStrategy manaProduction, WinnerStrategy winnerStrategy, HeroStrategy heroStrategy, CardStrategy cardStrategy) {
    this.manaProduction = manaProduction;
    this.winnerStrategy = winnerStrategy;
    this.heroStrategy = heroStrategy;
    this.cardStrategy = cardStrategy;
    this.playerInTurn = Player.FINDUS;
    //initializing turnCounter
    this.turnCounter = 0;

    //initializing Findus Hero
    playerHero.put(Player.FINDUS, new StandardHotStoneHero(Player.FINDUS,true,
            manaProduction.calculateMana(turnCounter), heroStrategy.getType(Player.FINDUS))); //TODO: variability point gammastone

    //initializing Peddersen Hero
    playerHero.put(Player.PEDDERSEN, new StandardHotStoneHero(Player.PEDDERSEN,false,manaProduction.calculateMana(turnCounter), heroStrategy.getType(Player.PEDDERSEN)));

    //initializing deck for Findus
    playerDecks.put(Player.FINDUS,this.cardStrategy.deckInitialization(Player.FINDUS));
    //initializing deck for Peddersen
    playerDecks.put(Player.PEDDERSEN,this.cardStrategy.deckInitialization(Player.PEDDERSEN));

    //initializing starting Hand for Findus
    playerHands.put(Player.FINDUS,this.cardStrategy.handInitialization(playerDecks.get(Player.FINDUS)));
    //initializing starting Hand for Peddersen
    playerHands.put(Player.PEDDERSEN,this.cardStrategy.handInitialization(playerDecks.get(Player.PEDDERSEN)));

    //initializing Field for Findus
    playerFields.put(Player.FINDUS, new ArrayList<>());
    //initializing Field for Peddersen
    playerFields.put(Player.PEDDERSEN, new ArrayList<>());
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
    //Sets turn to be the other player and sets up their turn
    playerInTurn = Utility.computeOpponent(playerInTurn);

    turnCounter++;
    if(1 < turnCounter) { //no player draws a card during the first round
      drawCard(playerInTurn);
    }
    //Sets the player in turns hero to be active, and to reset mana
    StandardHotStoneHero hero = castHeroToStandardHotStoneHero(getHero(playerInTurn));
    hero.setActive(true);
    hero.setMana(manaProduction.calculateMana(turnCounter));

    //Sets each card in field for the player in turn to be active
    for(Card c : getField(playerInTurn)) {
      castCardToStandardHotStoneCard(c).setActive(true);
    }
  }

  /** Draws a card from the deck and puts it in the players hand
   *
   *  @param who the player that draws the card
   */
  private void drawCard(Player who) {
    if(playerDecks.get(who).size() == 0) {
      castHeroToStandardHotStoneHero(getHero(who)).reduceHealth(2);
    } else {
      Card res = playerDecks.get(playerInTurn).remove(0);
      playerHands.get(playerInTurn).add(0,res);
    }
  }

  @Override
  public Status playCard(Player who, Card card) {
    Status status = isOwner(who,card);
    //todo check also playerinturn
    if(status != Status.OK) {
      return status;
    }
    playerHands.get(who).remove(card);
    playerFields.get(who).add(0,card);
    castHeroToStandardHotStoneHero(getHero(who)).reduceHeroMana(card.getManaCost());
    return status;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    Status status = canCardAttack(playerAttacking,attackingCard,defendingCard);
    if(status != Status.OK) {return status;}
    executeAttack(attackingCard, defendingCard);
    return status;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    Player playerBeingAttacked = Utility.computeOpponent(playerAttacking);
    //Active minion attacks the opposite players hero
    if(playerAttacking != attackingCard.getOwner()) {
      return Status.NOT_OWNER;
    }
    if (attackingCard.isActive()) {
      //Opposite player's hero take damage equivalent to the minions attack value
      castHeroToStandardHotStoneHero(playerHero.get(playerBeingAttacked)).reduceHealth(attackingCard.getAttack());
      //Minion is then set to inactive state
      castCardToStandardHotStoneCard(attackingCard).setActive(false);
      return Status.OK;
    } else {
      return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    }
  }

  @Override
  public Status usePower(Player who) {
    Status status = isAllowedHeroPower(who);
    if (status != Status.OK) {
      return status;
    }
    executeHeroPower(who);
    return status;
  }

  private Status canCardAttack(Player attackingPlayer, Card attackingCard, Card defendingCard) {
    Status status = canCardBeUsed(attackingPlayer,attackingCard);
    if(status != Status.OK) {return status;}
    status = isOwner(attackingPlayer, attackingCard);
    if(status != Status.OK) {return status;}
    if (attackingPlayer == defendingCard.getOwner()) { return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION; }
    if (!attackingCard.isActive()) { return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION; }
    return status;
  }

  private Status isPlayerInTurn(Player who) {
    if(playerInTurn != who) { return Status.NOT_PLAYER_IN_TURN; }
    return Status.OK;
  }

  private Status isOwner(Player who, Card card) {
    if(who != card.getOwner()) { return Status.NOT_OWNER; }
    return Status.OK;
  }

  private Status enoughMana(Player who, Card card) {
    if(getHero(who).getMana() < card.getManaCost()) { return Status.NOT_ENOUGH_MANA;}
    return Status.OK;
  }

  private Status isMinionActive(Card card) {
    if(!card.isActive()) { return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION; }
    return Status.OK;
  }


  /**
   * Checks if the hero is allowed to use its hero power.
   * @param who the player who uses the hero power
   * @return status
   */
  private Status canHeroPowerBeUsed(Player who) {
    // if it is not this players turn
    if(playerInTurn != who) { return Status.NOT_PLAYER_IN_TURN; }
    // if this hero already has used hero power
    if(!getHero(who).isActive()) { return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND; }
    // if it is this players turn, and have not used hero power
    if (getHero(who).getMana() < GameConstants.HERO_POWER_COST) { return Status.NOT_ENOUGH_MANA; }
    return Status.OK;
  }

  /**
   * Executes an attack where a card attacks another card.
   * Attacking minion is set to inactive. Both minions health is recalculated, dead minions removed from the field.
   * @param attackingCard the attacking card
   * @param defendingCard the defending card
   */
  private void executeAttack(Card attackingCard, Card defendingCard) {
    //defending card loses health equal to attackingCards attack
    reduceMinionHealth(defendingCard,attackingCard);

    //Attacking card loses health equal to defending cards attack.
    reduceMinionHealth(attackingCard,defendingCard);
    castCardToStandardHotStoneCard(attackingCard).setActive(false);

    //removes Card if health is equal to or lower than zero
    removeCardIfNoHealth(defendingCard);
    removeCardIfNoHealth(attackingCard);

  }

  private void reduceMinionHealth(Card minionLosingHealth, Card minionAttacking) {
    castCardToStandardHotStoneCard(minionLosingHealth).reduceHealth(minionAttacking.getAttack());
  }

  private void removeCardIfNoHealth(Card card) {
    if(card.getHealth() <= 0) {
      playerFields.get(card.getOwner()).remove(card);
    }
  }

  /**  Casting a hero to StandardHotStoneHero
   *
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
    return (StandardHotStoneCard) card;}
}
