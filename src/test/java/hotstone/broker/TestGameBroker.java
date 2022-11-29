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
package hotstone.broker;

import hotstone.broker.service.CardNameServiceImpl;
import hotstone.broker.service.HeroNameServiceImpl;
import hotstone.figuretestcase.doubles.StubCard;
import hotstone.framework.*;

import hotstone.standard.StandardHotStoneCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;

import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;

/** Skeleton test case for a 'depth-first' test-driven
 * development process to develop the Broker implementation
 * of client proxies and invokers, for all 'primitive' methods
 * in Game, that is, methods that do NOT take objects as
 * parameters, only primitive types and Strings.
 */
public class TestGameBroker {
  private Game game;

  @BeforeEach
  public void setup() {
    // === We start at the server side of the Broker pattern:
    // define the servant, next the invoker

    // Given a Servant game, here a test stub with canned output
    Game servant = new StubGameForBroker();
    // Which is injected into the dedicated Invoker which you must
    // develop
    Invoker invoker = new HotStoneGameInvoker(servant, new CardNameServiceImpl(), new HeroNameServiceImpl());

    // === Next define the client side of the pattern:
    // the client request handler, the requestor, and the client proxy

    // Instead of a network-based client- and server request handler
    // we make a fake object CRH that talks directly with the injected
    // invoker
    ClientRequestHandler crh =
            new LocalMethodClientRequestHandler(invoker);

    // Which is injected into the standard JSON requestor of the
    // FRDS.Broker library
    Requestor requestor = new StandardJSONRequestor(crh);

    // Which is finally injected into the GameClientProxy that
    // you must develop...
    game = new GameClientProxy(requestor);
  }


  @Test
  public void shouldHaveTurnNumber312() {
    // Test stub hard codes the turn number to 312
    assertThat(game.getTurnNumber(), is(312));
  }

  @Test
  public void playerInTurnShouldBeFindus() {
    assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
  }

  @Test
  public void winnerShoudlBePeddersen() {
    assertThat(game.getWinner(), is(Player.PEDDERSEN));
  }

  @Test
  public void deckSizeShouldBe500() {
    assertThat(game.getDeckSize(Player.PEDDERSEN), is(500));
  }

  @Test
  public void handSizeShouldBe10() {
    assertThat(game.getHandSize(Player.FINDUS),is(10));
  }

  @Test
  public void fieldSizeShouldBe11() {
    assertThat(game.getFieldSize(Player.FINDUS), is(11));
  }

  @Test
  public void findusAttacksPeddersensHero() {
    Card card = new StandardHotStoneCard("faceSmasher", Player.PEDDERSEN, 0,2,2);

    assertThat(game.attackHero(Player.FINDUS, card), is(Status.NOT_PLAYER_IN_TURN));
  }

  @Test
  public void peddersenIsAllowedToUseHeroPower() {
    assertThat(game.usePower(Player.PEDDERSEN), is(Status.NOT_ENOUGH_MANA));
  }

  @Test
  public void afterEndTurnTurnNumberIs313() {
    game.endTurn();
    assertThat(game.getTurnNumber(), is(313));
  }

}
