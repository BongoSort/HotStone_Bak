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
  private List<Card> findusDeck = new ArrayList<>();
  private List<Card> peddersenDeck = new ArrayList<>();
  private List<Card> findusHand = new ArrayList<>();
  private List<Card> peddersenHand = new ArrayList<>();
  private List<Card> findusField = new ArrayList<>();
  //private List<Card> peddersenField = new ArrayList<>();
  private int turnCounter;

  public StandardHotStoneGame() {
    this.playerInTurn = Player.FINDUS;

    //initializing turnCounter
    this.turnCounter = 0;

    //initializing starting Hand for Findus
    findusHand.add(new StandardHotStoneCard(GameConstants.TRES_CARD));
    findusHand.add(new StandardHotStoneCard(GameConstants.DOS_CARD));
    findusHand.add(new StandardHotStoneCard(GameConstants.UNO_CARD));
    //now for Peddersen
    peddersenHand.add(new StandardHotStoneCard(GameConstants.TRES_CARD));
    peddersenHand.add(new StandardHotStoneCard(GameConstants.DOS_CARD));
    peddersenHand.add(new StandardHotStoneCard(GameConstants.UNO_CARD));

/*
    //DETTE ER ET ALTERNATIV; SNAK MED INSTRUKTOR
    fillHand(findusHand);
    fillHand(peddersenHand);*/
  }

  //Dette er en test metode.
/*
  public void fillHand(List<Card> hand) {
    hand.add(new StandardHotStoneCard(GameConstants.TRES_CARD));
    hand.add(new StandardHotStoneCard(GameConstants.DOS_CARD));
    hand.add(new StandardHotStoneCard(GameConstants.UNO_CARD));
  }*/

  @Override
  public Player getPlayerInTurn() {
    return playerInTurn;
  }

  @Override
  public Hero getHero(Player who) {
    return null;
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
    return 0;
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
    return null;
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
    //Card card = new StandardHotStoneCard(GameConstants.UNO_CARD);
    //return card;
    return findusField.get(indexInField);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    return null;
  }

  @Override
  public int getFieldSize(Player who) {
    return findusField.size();
  }

  @Override
  public void endTurn() {
    playerInTurn = Utility.computeOpponent(playerInTurn);
    turnCounter++;
  }

  @Override
  public Status playCard(Player who, Card card) {
    if (who == playerInTurn) {
      if(who == Player.FINDUS) {
        int cardIndex = findusHand.indexOf(card);
        Card temp = findusHand.remove(cardIndex);
        findusField.add(temp);
      }
      return Status.OK;
    } else {
      return Status.NOT_PLAYER_IN_TURN;
    }
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
    return null;
  }
}
