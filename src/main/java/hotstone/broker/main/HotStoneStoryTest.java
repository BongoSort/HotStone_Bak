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

package hotstone.broker.main;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.http.UriTunnelClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.common.BrokerConstants;
import hotstone.broker.doubles.StubCardForBroker;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;

public class HotStoneStoryTest {
  public static void main(String[] args)  {
    // Get the name of the host from the commandline parameters
    String host = args[0];
    // and execute the story test, talking to the server with that name
    new HotStoneStoryTest(host);
  }

  public HotStoneStoryTest(String host) {
    // Create the client side Broker roles
    UriTunnelClientRequestHandler clientRequestHandler
            = new UriTunnelClientRequestHandler(host, BrokerConstants.HOTSTONE_PORT,
            false, BrokerConstants.HOTSTONE_TUNNEL_PATH);
    Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

    Game game = new GameClientProxy(requestor);
    testSimpleMethods(game);
  }

  private void testSimpleMethods(Game game) {
    System.out.println("=== Testing pass-by-value methods of Game ===");
    System.out.println(" --> Game turnNumber            " + game.getTurnNumber());
    System.out.println(" --> Game playerInTurn          " + game.getPlayerInTurn());
    System.out.println(" --> Game winner                " + game.getWinner());
    System.out.println(" --> Game FindusDeckSize        " + game.getDeckSize(Player.FINDUS));
    System.out.println(" --> Game FindusHandSize        " + game.getHandSize(Player.FINDUS));
    System.out.println(" --> Game FindusFieldSize       " + game.getFieldSize(Player.FINDUS));
    System.out.println(" --> Game FindusPlaysCard       " + game.playCard(Player.FINDUS, new StubCardForBroker()));
    System.out.println(" --> Game FindusAttacksCard     " + game.attackCard(Player.FINDUS, new StubCardForBroker(), new StubCardForBroker()));
    System.out.println(" --> Game FindusAttacksHero     " + game.attackHero(Player.FINDUS, new StubCardForBroker()));
    System.out.println(" --> Game FindusUsesPower       " + game.usePower(Player.FINDUS));
    // TODO - add calls to the rest of the implemented methods
    System.out.println(" --> Game FindusHand            " + game.getHand(Player.FINDUS));
    System.out.println(" --> Game PeddersendHand        " + game.getHand(Player.PEDDERSEN));
    System.out.println(" --> Game FindusField           " + game.getField(Player.FINDUS));
    System.out.println(" --> Game PeddersendField       " + game.getField(Player.PEDDERSEN));
    System.out.println(" --> Game PeddersendField       " + game.getField(Player.PEDDERSEN));
    System.out.println(" --> Game FindusCardInField     " + game.getCardInField(Player.FINDUS, 0)); //TODO? index ok?
    System.out.println(" --> Game PeddersendCardInField " + game.getCardInField(Player.PEDDERSEN, 0)); //TODO? index ok?
    System.out.println("=== End ===");

    //TODO: en Player er fint
    //TODO: husk også at lave for Card og Hero ting.
  }
}
