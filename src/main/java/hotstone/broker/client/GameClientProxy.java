/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.broker.client;

import com.google.gson.reflect.TypeToken;
import frds.broker.ClientProxy;
import frds.broker.IPCException;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.*;
import hotstone.observer.GameObserver;
import hotstone.standard.StandardHotStoneCard;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/** Template/starter code for your ClientProxy of Game.
 */
public class GameClientProxy implements Game, ClientProxy {

  public static final String GAME_OBJECTID = "singleton";
  private Requestor requestor;

  public GameClientProxy(Requestor requestor) {
    this.requestor = requestor;
  }

  @Override
  public int getTurnNumber() {
    int turnNumber = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_GET_TURN_NUMBER,
            Integer.class);
    return turnNumber;
  }

  @Override
  public Player getPlayerInTurn() {
    Player player = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_GET_PLAYER_IN_TURN, Player.class);
    return player;
  }

  @Override
  public Hero getHero(Player who) {
    String id = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_GET_HERO, String.class,who);
    return new HeroClientProxy(id,requestor);
  }

  @Override
  public Player getWinner() {
    Player player = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_GET_WINNER, Player.class);
    return player;
  }

  @Override
  public int getDeckSize(Player who) {
    int deckSize = requestor.sendRequestAndAwaitReply(
            GAME_OBJECTID,
            OperationNames.GAME_GET_DECK_SIZE,
            Integer.class,
            who);
    return deckSize;
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    String id = requestor.sendRequestAndAwaitReply(
            GAME_OBJECTID,
            OperationNames.GAME_GET_CARD_IN_HAND,
            String.class,
            who,
            indexInHand);
    CardClientProxy cardClientProxy = new CardClientProxy(id, requestor);
    return cardClientProxy;
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    Type collectionType = new TypeToken<List<String>>(){}.getType();
    List<String> theIDList = requestor.sendRequestAndAwaitReply(
            GAME_OBJECTID,
            OperationNames.GAME_GET_HAND,
            collectionType,
            who);

    List<CardClientProxy> list = new ArrayList<>();
    for(String s : theIDList) {
      CardClientProxy cardClientProxy = new CardClientProxy(s, requestor);
      list.add(cardClientProxy);
    }

    return list;
  }


  @Override
  public int getHandSize(Player who) {
    int handSize = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_GET_HAND_SIZE,
            Integer.class, who);
    return handSize;
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    String id = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_GET_CARD_IN_FIELD, String.class, who, indexInField);
    return new CardClientProxy(id, requestor);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    Type collectionType = new TypeToken<List<String>>(){}.getType();
    List<String> theIDList = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_GET_FIELD,
            collectionType,
            who);
    List<CardClientProxy> list = new ArrayList<>();
    for(String s : theIDList) {
      CardClientProxy cardClientProxy = new CardClientProxy(s, requestor);
      list.add(cardClientProxy);
    }

    return list;
  }

  @Override
  public int getFieldSize(Player who) {
    int fieldSize = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_GET_FIELD_SIZE,
            Integer.class, who);
    return fieldSize;
  }

  @Override
  public void endTurn() {
    requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_END_OF_TURN,void.class);
  }

  @Override
  public Status playCard(Player who, Card card) {
    Status playCardStatus = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_PLAY_CARD,
            Status.class, who, card.getId());
    return playCardStatus;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    Status status = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_ATTACK_CARD,
            Status.class, playerAttacking, attackingCard.getId(), defendingCard.getId());
    return status;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    Status attackCardAllowed = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_ATTACK_HERO, Status.class, playerAttacking, attackingCard.getId());
    return attackCardAllowed;
  }

  @Override
  public Status usePower(Player who) {
    Status usePowerStatus = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
            OperationNames.GAME_USE_POWER,
            Status.class, who);
    return usePowerStatus;
  }

  @Override
  public void addObserver(GameObserver observer) {

  }
}
