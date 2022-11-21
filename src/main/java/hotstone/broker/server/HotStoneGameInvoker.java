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

package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.broker.doubles.StubCardForBroker;
import hotstone.broker.doubles.StubHeroForBroker;
import hotstone.framework.*;
import hotstone.standard.StandardHotStoneCard;

import javax.servlet.http.HttpServletResponse;

public class HotStoneGameInvoker implements Invoker {
  private final Game game;
  private final Gson gson;
  private Hero fakeitHero = new StubHeroForBroker();
  private Hero lookupHero(String ObjectId) {
    return fakeitHero;
  }

  private Card fakeitCard = new StubCardForBroker();
  private Card lookupCard(String ObjectId) {return fakeitCard;}

  public HotStoneGameInvoker(Game servant) {
    this.game = servant;
    gson = new Gson();
  }

  @Override
  public String handleRequest(String request) {
    // Do the demarshalling
    RequestObject requestObject =
            gson.fromJson(request, RequestObject.class);
    String objectId = requestObject.getObjectId();
    JsonArray array =
            JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

    ReplyObject reply;

    switch (requestObject.getOperationName()) {
      case OperationNames.GAME_GET_TURN_NUMBER -> {
        int turnNumber = game.getTurnNumber();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(turnNumber));
      }
      case OperationNames.GAME_GET_PLAYER_IN_TURN -> {
        Player player = game.getPlayerInTurn();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(player));
      }
      case OperationNames.GAME_GET_WINNER -> {
        Player player = game.getWinner();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(player));
      }
      case OperationNames.GAME_GET_DECK_SIZE -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int deckSize = game.getDeckSize(who);

        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(deckSize));
      }
      case OperationNames.GAME_GET_HAND_SIZE -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int handSize = game.getHandSize(who);

        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(handSize));
      }
      case OperationNames.GAME_GET_FIELD_SIZE -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        int fieldSize = game.getFieldSize(who);

        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(fieldSize));
      }
      case OperationNames.GAME_PLAY_CARD -> {
        Player who = gson.fromJson(array.get(0), Player.class);
        Card card = gson.fromJson(array.get(1), StandardHotStoneCard.class);

        Status playCardStatus = game.playCard(who, card);

        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(playCardStatus));
      }

      case OperationNames.GAME_ATTACK_CARD -> {
        Player playerAttacking = gson.fromJson(array.get(0), Player.class);
        Card attackingCard = gson.fromJson(array.get(1), StandardHotStoneCard.class);
        Card defendingCard = gson.fromJson(array.get(2), StandardHotStoneCard.class);

        Status status = game.attackCard(playerAttacking,attackingCard,defendingCard);

        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(status));
      }
      case OperationNames.GAME_ATTACK_HERO -> {
        Player playerAttacking = gson.fromJson(array.get(0), Player.class);
        Card attackingCard = gson.fromJson(array.get(1), StandardHotStoneCard.class);
        Status status = game.attackHero(playerAttacking, attackingCard);

        reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(status));
      }
      case OperationNames.GAME_USE_POWER -> {
        Player who = gson.fromJson(array.get(0), Player.class);

        Status usePowerStatus = game.usePower(who);

        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(usePowerStatus));
      }
      case OperationNames.HERO_GET_MANA -> {
        int mana = fakeitHero.getMana();
        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(mana));
      }
      case OperationNames.HERO_GET_HEALTH -> {
        Hero hero = lookupHero(objectId);
        int health = hero.getHealth();
        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(health));
      }
      case OperationNames.HERO_IS_ACTIVE -> {
        Hero hero = lookupHero(objectId);
        boolean active = hero.isActive();
        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(active));
      }
      case OperationNames.HERO_GET_TYPE -> {
        Hero hero = lookupHero(objectId);
        String type = hero.getType();
        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(type));
      }
      case OperationNames.HERO_GET_OWNER -> {
        Hero hero = lookupHero(objectId);
        Player owner = hero.getOwner();
        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(owner));
      }
      case OperationNames.HERO_GET_EFFECT_DESCRIPTION -> {
        Hero hero = lookupHero(objectId);
        String effectDesc = hero.getEffectDescription();
        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(effectDesc));
      }
      case OperationNames.CARD_GET_NAME -> {
        Card card = lookupCard(objectId);
        String cardName = card.getName();
        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(cardName));
      }
      case OperationNames.CARD_GET_MANA_COST -> {
        Card card = lookupCard(objectId);
        int cardManaCost = card.getManaCost();
        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(cardManaCost));
      }
      case OperationNames.CARD_GET_ATTACK -> {
        Card card = lookupCard(objectId);
        int cardAttackPower = card.getAttack();
        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(cardAttackPower));
      }
      default -> reply = null;
    }
    return  gson.toJson(reply);
  }
}
