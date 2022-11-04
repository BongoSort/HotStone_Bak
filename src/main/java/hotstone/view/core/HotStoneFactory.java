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

package hotstone.view.core;

import hotstone.framework.Game;
import hotstone.framework.Player;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;

import javax.swing.*;

/** A MiniDraw factory that creates the delegates that binds
 * the minidraw UI together with the domain roles.
 */
public class HotStoneFactory implements Factory {
  private Game game;
  private Player operatingPlayer;
  private HotStoneDrawingType uiType;
  private HotStoneDrawing theHotStoneDrawing;

  /**
   * Construct factory for minidraw coupled with a HotStone game.
   * @param game The game to be associated with
   * @param operatingPlayer The player that this UI represents
   * @param uiType The type of UI to visualize - either a
   *               HotSeat type (both players use the same UI) or
   *               an Opponent type (each player has own UI).
   */
  public HotStoneFactory(Game game, Player operatingPlayer,
                         HotStoneDrawingType uiType) {
    this.game = game;
    this.operatingPlayer = operatingPlayer;
    this.uiType = uiType;
  }

  @Override
  public DrawingView createDrawingView(DrawingEditor editor) {
    return new HotStoneDrawingView(editor, operatingPlayer);
  }

  @Override
  public Drawing createDrawing(DrawingEditor editor) {
    theHotStoneDrawing = new HotStoneDrawing(editor, game, operatingPlayer, uiType);
    return theHotStoneDrawing;
  }

  @Override
  public JTextField createStatusField(DrawingEditor editor) {

    return new JTextField("Welcome to HotStone. Only for personal use: "
            + "Card graphics is Copyright of Blizzard Entertainment.");
  }
}
