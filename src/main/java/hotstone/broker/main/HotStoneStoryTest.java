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
import hotstone.broker.client.CardClientProxy;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.client.HeroClientProxy;
import hotstone.broker.common.BrokerConstants;
import hotstone.broker.doubles.StubCardForBroker;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.AlphaStoneConcreteFactory;
import hotstone.variants.BetaStone.BetaStoneConcreteFactory;
import hotstone.variants.DeltaStone.DeltaStoneConcreteFactory;
import hotstone.variants.EpsilonStone.EpsilonStoneConcreteFactory;
import hotstone.variants.EtaStone.EtaStoneConcreteFactory;
import hotstone.variants.GammaStone.GammaStoneConcreteFactory;
import hotstone.variants.SemiStone.SemiStoneConcreteFactory;
import hotstone.variants.ZetaStone.ZetaStoneConcreteFactory;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import hotstone.view.tool.DualUserInterfaceTool;
import hotstone.view.tool.HotSeatStateTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

public class HotStoneStoryTest {
  public static void main(String[] args)  {
    // Get the name of the host from the commandline parameters
    String host = args[0];
    Player whoToPlay;
    if(args[1].equals("findus") || args[1].equals("Findus")) {
      whoToPlay = Player.FINDUS;
    } else {
      whoToPlay = Player.PEDDERSEN;
    }
    // and execute the story test, talking to the server with that name
    new HotStoneStoryTest(host,whoToPlay);
  }

  public HotStoneStoryTest(String host, Player whoToPlay) {
    // Create the client side Broker roles
    UriTunnelClientRequestHandler clientRequestHandler
            = new UriTunnelClientRequestHandler(host, BrokerConstants.HOTSTONE_PORT,
            false, BrokerConstants.HOTSTONE_TUNNEL_PATH);
    Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

    Game game = new GameClientProxy(requestor);

    DrawingEditor editor =
            new MiniDrawApplication( "HotSeat: Variant semi",
                    new HotStoneFactory(game, whoToPlay,
                            HotStoneDrawingType.OPPONENT_MODE));
    editor.open();
    editor.setTool(new DualUserInterfaceTool(editor,game,whoToPlay));
  }
}
