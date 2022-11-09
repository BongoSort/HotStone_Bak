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

package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** MiniDraw Tool to 'end the turn' */
public class EndTurnTool extends NullTool {
  protected final HotStoneDrawing model;
  private Game game;

  public EndTurnTool(DrawingEditor editor, Game game) {
    // Note - we need access to the specialized 'hotseat'
    // behaviour, to swap UI in the model, alas a hard
    // coupling, made by casting...
    model = (HotStoneDrawing) editor.drawing();
    this.game = game;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {}

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    // Find the button below
    Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
    if (figureAtPosition instanceof HotStoneFigure) {
      HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
      if (hsf.getType() == HotStoneFigureType.TURN_BUTTON) {
        game.endTurn();
        // The Drawing will receive the end of turn event and
        // initiate the display with the hot seat swap button
      } else if (hsf.getType() == HotStoneFigureType.SWAP_BUTTON) {
        model.endHotSeatState();
      }
    }
  }
}
