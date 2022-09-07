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
import java.util.List;

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

  private List<Card> findusHand = new ArrayList<>();
  private List<Card> peddersenHand = new ArrayList<>();
  private List<Card> findusField = new ArrayList<>();
  private List<Card> peddersenField = new ArrayList<>();
  private List<Card> findusDeck = new ArrayList<>();
  private List<Card> peddersenDeck = new ArrayList<>();
  private int turnCounter;
  private int powerCounter;
  private Hero findusHero;
  private Hero peddersenHero;


  public StandardHotStoneGame() {
    this.playerInTurn = Player.FINDUS;
    //initializing turnCounter
    this.turnCounter = 0;

    //initializing powerCounter
    this.powerCounter = 0;

    //initializing FindusHero
    this.findusHero = new StandardHotStoneHero(Player.FINDUS);

    //initializing FindusHero
    this.peddersenHero = new StandardHotStoneHero(Player.PEDDERSEN);

    //initializing starting Hand for Findus
    fillHand(findusHand);
    //initializing starting Hand for Peddersen
    fillHand(peddersenHand);

    //initializing Findus deck:
    fillDeck(findusDeck);
    //initializing Peddersen deck:
    fillDeck(peddersenDeck);
  }

  private void fillHand(List<Card> hand) {
    hand.add(new StandardHotStoneCard(GameConstants.TRES_CARD));
    hand.add(new StandardHotStoneCard(GameConstants.DOS_CARD));
    hand.add(new StandardHotStoneCard(GameConstants.UNO_CARD));
  }

  private void fillDeck(List<Card> deck) {
    deck.add(new StandardHotStoneCard(GameConstants.SIETE_CARD));
    deck.add(new StandardHotStoneCard(GameConstants.SEIS_CARD));
    deck.add(new StandardHotStoneCard(GameConstants.CINCO_CARD));
    deck.add(new StandardHotStoneCard(GameConstants.CUATRO_CARD));
  }


  @Override
  public Player getPlayerInTurn() {
    return playerInTurn;
  }

  @Override
  public Hero getHero(Player who) {
    if(who == Player.FINDUS) {
      return findusHero;
    } else {
      return peddersenHero;
    }
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
    if(Player.FINDUS == who) {
      return findusDeck.size();
    } else {
      return peddersenDeck.size();
    }
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    if(who == Player.FINDUS) {
      return findusHand.get(indexInHand);
    } else {
      return peddersenHand.get(indexInHand);
    }
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    if(Player.FINDUS == who) {
      return findusHand;
    } else {
      return peddersenHand;
    }
  }

  @Override
  public int getHandSize(Player who) {
    if (who == Player.FINDUS) {
      return findusHand.size();
    } else {
      return peddersenHand.size();
    }
  }
  @Override
  public Card getCardInField(Player who, int indexInField) {
    if(who == Player.FINDUS) {
      return findusField.get(indexInField);
    } else {
      return peddersenField.get(indexInField);
    }
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    return null;
  }

  @Override
  public int getFieldSize(Player who) {
    if (who == Player.FINDUS) {
      return findusField.size();
    } else {
      return peddersenField.size();
    }

  }

  @Override
  public void endTurn() {
    playerInTurn = Utility.computeOpponent(playerInTurn);
    turnCounter++;
    if(1 < turnCounter) {
      drawCard();
    }
    setHeroStatus();
  }

  private void setHeroStatus() {
    if(playerInTurn == Player.PEDDERSEN) {
      StandardHotStoneHero hero = (StandardHotStoneHero) getHero(Player.PEDDERSEN);
      hero.setStatus(true);
      StandardHotStoneHero hero2 = (StandardHotStoneHero) getHero(Player.FINDUS);
      hero2.setStatus(false);
    } else {
      StandardHotStoneHero hero = (StandardHotStoneHero) getHero(Player.FINDUS);
      hero.setStatus(true);
      StandardHotStoneHero hero2 = (StandardHotStoneHero) getHero(Player.PEDDERSEN);
      hero2.setStatus(false);
    }
  }

  private void drawCard() {
    if (turnCounter % 2 == 0) {
      Card card = findusDeck.remove(findusDeck.size() - 1);
      findusHand.add(0,card);
    } else {
      Card card1 = peddersenDeck.remove(peddersenDeck.size() - 1);
      peddersenHand.add(0,card1);
    }
  }

  @Override
  public Status playCard(Player who, Card card) {
    if (who != playerInTurn) {
      return Status.NOT_PLAYER_IN_TURN;
    }
    switch(who.ordinal()) {
      case 0 -> { //0 is ordinal for Player.FINDUS
        int cardIndex = findusHand.indexOf(card);
        Card temp = findusHand.remove(cardIndex);
        findusField.add(temp);
      }
      case 1 -> { //1 is ordinal for Player.PEDDERSEN
        int cardIndex = peddersenHand.indexOf(card);
        Card temp = peddersenHand.remove(cardIndex);
        peddersenField.add(temp);
      }
    }
    return Status.OK;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    return null;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    return null;
  }

  @Override
  public Status usePower(Player who) {
    powerCounter++;
    if(1 < powerCounter) {
      return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }
    return Status.OK;
      // hvis spilleren er aktiv, så sæt til inaktiv og retuner ok
    // hvis spilleren er inaktiv så retuner NEJ
  }
}
