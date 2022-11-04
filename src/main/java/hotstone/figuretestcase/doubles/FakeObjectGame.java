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

package hotstone.figuretestcase.doubles;

import hotstone.framework.*;
import hotstone.observer.GameObserver;
import hotstone.observer.Observable;
import hotstone.observer.ObserverHandler;

import java.util.ArrayList;
import java.util.List;

/** A Test Double Fake-Object implementation of Game. Use this fake to test
 * and develop the UI by modifying/adding simple behaviour that mimic
 * a real game's behaviour and verify that the UI responds correctly.
 */

public class FakeObjectGame implements Game, Observable {
  private List<Card> findusHand = new ArrayList<>();
  private Hero findus;

  private List<Card> findusField = new ArrayList<>();
  private List<StubCard> peddersenField = new ArrayList<>();

  private ObserverHandler observerHandler;

  public FakeObjectGame() {
    findusHand.add(0, new StubCard("Uno", Player.FINDUS, 4));
    findusHand.add(0, new StubCard("Tres", Player.FINDUS, 3));
    findusHand.add(0, new StubCard("Dos", Player.FINDUS, 2));

    StubCard pCard = new StubCard("Siete", Player.PEDDERSEN, 7);
    peddersenField.add(0, pCard);

    findus = new StubHero();

    observerHandler = new ObserverHandler();
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    assert who == Player.FINDUS;
    return findusHand.get(indexInHand);
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    assert who == Player.FINDUS;
    return findusHand;
  }

  @Override
  public int getHandSize(Player who) {
    assert who == Player.FINDUS;
    return findusHand.size();
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    if (who == Player.PEDDERSEN) return peddersenField.get(indexInField);
    return findusField.get(indexInField);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    if (who == Player.PEDDERSEN) return peddersenField;
    return findusField;
  }

  @Override
  public int getFieldSize(Player who) {
    if (who == Player.PEDDERSEN) return peddersenField.size();
    return findusField.size();
  }

  @Override
  public Player getPlayerInTurn() {
    return Player.FINDUS;
  }

  @Override
  public Hero getHero(Player who) {
    return findus;
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public int getTurnNumber() {
    return 0;
  }

  @Override
  public int getDeckSize(Player who) {
    return 16;
  }

  @Override
  public void endTurn() {
    observerHandler.notifyTurnChangeTo(Player.FINDUS);
  }

  @Override
  public Status playCard(Player who, Card card) {
    System.out.println(" FakeObjectGame:: playCard(" + who + ", " + card.getName() + ") called...");
    findusHand.remove(card);
    findusField.add(card);
    observerHandler.notifyPlayCard(who, card);
    return Status.OK;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    System.out.println(" FakeObjectGame:: attackCard(" + playerAttacking + ", "
            + attackingCard.getName() + ", "
            + defendingCard.getName() +") called...");
    System.out.println(" FakeObjectGame:: REMOVING Findus Attacking Card");
    observerHandler.notifyAttackCard(playerAttacking, attackingCard, defendingCard);

    findusField.remove(attackingCard);
    observerHandler.notifyCardRemove(playerAttacking, attackingCard);

    System.out.println(" FakeObjectGame:: REDUCING health of Defending Card");
    ((StubCard) defendingCard).setHealth(3);
    observerHandler.notifyCardUpdate(defendingCard);
    return Status.OK;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    observerHandler.notifyAttackHero(playerAttacking, attackingCard);
    return Status.OK;
  }

  @Override
  public Status usePower(Player who) {
    observerHandler.notifyUsePower(who);
    return Status.OK;
  }

  @Override
  public void addObserver(GameObserver observer) {
    observerHandler.addObserver(observer);
  }
}
