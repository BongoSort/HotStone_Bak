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

  private int turnCounter;
  private HashMap<Player,ArrayList<Card>> playerDecks = new HashMap<>();
  private HashMap<Player,ArrayList<Card>> playerHands = new HashMap<>();
  private HashMap<Player,ArrayList<Card>> playerFields = new HashMap<>();
  private HashMap<Player, Hero> playerHero = new HashMap<>();

  public StandardHotStoneGame() {
    this.playerInTurn = Player.FINDUS;
    //initializing turnCounter
    this.turnCounter = 0;

    //initializing Findus Hero
    playerHero.put(Player.FINDUS, new StandardHotStoneHero(Player.FINDUS,true));

    //initializing Peddersen Hero
    playerHero.put(Player.PEDDERSEN, new StandardHotStoneHero(Player.PEDDERSEN,false));

    //initializing starting Hand for Findus
    playerHands.put(Player.FINDUS,fillHand());
    //initializing starting Hand for Peddersen
    playerHands.put(Player.PEDDERSEN,fillHand());

    //initializing map for decks:
    playerDecks.put(Player.FINDUS,fillDeck());
    playerDecks.put(Player.PEDDERSEN,fillDeck());

    playerFields.put(Player.FINDUS, new ArrayList<>());
    playerFields.put(Player.PEDDERSEN, new ArrayList<>());
  }

  /** Fill the hand of a player
   *  Used for setting the game up initially, granting each player tres, dos, uno
   *
   * @return the filled hand
   */
  private ArrayList<Card> fillHand() {
    ArrayList<Card> hand = new ArrayList<>();
    hand.add(new StandardHotStoneCard(GameConstants.TRES_CARD));
    hand.add(new StandardHotStoneCard(GameConstants.DOS_CARD));
    hand.add(new StandardHotStoneCard(GameConstants.UNO_CARD));
    return hand;
  }

  /** Fill the deck of a player
   *  Used for setting the game up initially, putting cuatro, cinco, seis, siete into the players deck
   *
   * @return the filled deck
   */
  private ArrayList<Card> fillDeck(Player who) {
    ArrayList<Card> deck = new ArrayList<>();
    deck.add(new StandardHotStoneCard(GameConstants.CUATRO_CARD));
    deck.add(new StandardHotStoneCard(GameConstants.CINCO_CARD));
    deck.add(new StandardHotStoneCard(GameConstants.SEIS_CARD));
    deck.add(new StandardHotStoneCard(GameConstants.SIETE_CARD));
    return deck;
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
    if(turnCounter == 8) {
      return Player.FINDUS;
    } else {
      return null;
    }
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
    //Sets current players hero to be inactive
    castHeroToStandardHotStoneHero(getHero(playerInTurn)).setStatus(false);

    //Sets turn to be the other player and sets up their turn
    playerInTurn = Utility.computeOpponent(playerInTurn);

    turnCounter++;
    if(1 < turnCounter) { //no player draws a card during the first round
      drawCard();
    }
    //Sets each card in field for the player in turn to be active
    for(Card c : getField(playerInTurn)) {
      castCardToStandardHotStoneCard(c).SetActive(true);
    }
    //Sets the player in turns hero to be active, and to reset mana
    StandardHotStoneHero hero = castHeroToStandardHotStoneHero(getHero(playerInTurn));
    hero.setStatus(true);
    hero.resetMana();

  }


  /**
   *  Draws a card from the deck and puts it in the players hand
   */
  private void drawCard() {
    Player who = getPlayerInTurn();
    Card res = playerDecks.get(who).remove(0);
    playerHands.get(who).add(0,res);
  }

  @Override
  public Status playCard(Player who, Card card) {
    if (who != playerInTurn) {
      return Status.NOT_PLAYER_IN_TURN;
    }

    if(getHero(who).getMana() < card.getManaCost()) {
      return Status.NOT_ENOUGH_MANA;
    }

    playerHands.get(who).remove(card);
    playerFields.get(who).add(0,card);
    castHeroToStandardHotStoneHero(getHero(who)).reduceHeroMana(card.getManaCost());

    return Status.OK;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
     if(playerAttacking == defendingCard.getOwner()) {
       return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION;
     } else {

       return Status.OK;
     }
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    Player playerBeingAttacked = Utility.computeOpponent(playerAttacking);
    //Active minion attacks the opposite players hero
    if (attackingCard.isActive()) {
      //Opposite players hero take damage equivalent to the minions attack value
      castHeroToStandardHotStoneHero(playerHero.get(playerBeingAttacked)).reduceHealth(attackingCard.getAttack());
      //Minion is then set to inactive state
      castCardToStandardHotStoneCard(attackingCard).SetActive(false);
      return Status.OK;
    } else {
      return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    }
  }

  @Override
  public Status usePower(Player who) {
    // if it is not this players turn
    if(playerInTurn != who) {
      return Status.NOT_PLAYER_IN_TURN;
    }
    StandardHotStoneHero hero = castHeroToStandardHotStoneHero(playerHero.get(who));
    // if this hero already has used hero power
    if(!hero.isActive()) {
      return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }
    // is it is this players turn, and have not used hero power
    if(hero.getMana() < GameConstants.HERO_POWER_COST) {
      return Status.NOT_ENOUGH_MANA;
    }
    hero.setStatus(false);
    hero.reduceHeroMana(GameConstants.HERO_POWER_COST); //Since the only hero in Alphastone is Baby, we don't need to check for other heroes.

    return Status.OK;
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
   * @param card the hero being casted
   * @return the casted card
   */
  private StandardHotStoneCard castCardToStandardHotStoneCard(Card card) {
    return (StandardHotStoneCard) card;}
}
