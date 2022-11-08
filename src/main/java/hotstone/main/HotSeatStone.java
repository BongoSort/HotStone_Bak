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

package hotstone.main;

import hotstone.figuretestcase.doubles.FakeObjectGame;
import hotstone.framework.Game;
import hotstone.framework.Player;
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
import hotstone.view.tool.HotSeatStateTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.SelectionTool;

/** A single jvm application which uses a 'hotseat' to allow both players to
 * alternate play.
 */
public class HotSeatStone {
  public static void main(String[] args) {


    System.out.println("=== Starting HotSeat on game variant: " + args[0] + " ===");
    Game game;
    switch (args[0]) {
      case "alpha" -> game = new StandardHotStoneGame(new AlphaStoneConcreteFactory());
      case "beta" -> game = new StandardHotStoneGame(new BetaStoneConcreteFactory());
      case "delta" -> game = new StandardHotStoneGame(new DeltaStoneConcreteFactory());
      case "epsilon" -> game = new StandardHotStoneGame(new EpsilonStoneConcreteFactory());
      case "eta" -> game = new StandardHotStoneGame(new EtaStoneConcreteFactory());
      case "gamma" -> game = new StandardHotStoneGame(new GammaStoneConcreteFactory());
      case "zeta" -> game = new StandardHotStoneGame(new ZetaStoneConcreteFactory());
      case "semi" -> game = new StandardHotStoneGame(new SemiStoneConcreteFactory());
      default -> game = null;
    };


    DrawingEditor editor =
            new MiniDrawApplication( "HotSeat: Variant " + args[0],
                    new HotStoneFactory(game, Player.FINDUS,
                            HotStoneDrawingType.HOTSEAT_MODE) );
    editor.open();
    editor.setTool(new HotSeatStateTool(editor,game));
  }
}
