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
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Template for the State tool - similar to MiniDraw SelectionTool
 * it is a tool that delegates all mouse events to a subtool, and
 * the kind of subtool to use is determined by what is clicked in the
 * mouse down event. If it is a button, then delegate to ButtonTool,
 * if it is a card, delegate to PlayCardTool, etc.
 *
 * Quite a lot of the code is complete - fill in the missing pieces...
 */
public class HotSeatStateTool extends NullTool {
  private final Tool theNullTool;
  private final Drawing model;
  private Tool state;
  private DrawingEditor editor;
  private Game game;

  public HotSeatStateTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;
    model = editor.drawing();
    state = theNullTool = new NullTool();
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
    if (figureAtPosition instanceof HotStoneFigure) {
      HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
      // TODO: Complete this state selection
      switch (hsf.getType()) {
        case CARD_FIGURE -> state = new CardPlayTool(editor, game, game.getPlayerInTurn());
        case TURN_BUTTON, SWAP_BUTTON -> state = new EndTurnTool(editor, game);
        case MINION_FIGURE -> state = new MinionAttackTool(editor,game,game.getPlayerInTurn());
        case HERO_FIGURE -> state = new UsePowerTool(editor,game);
        case WIN_BUTTON -> state = theNullTool;
      }


      /*if (hsf.getType() == HotStoneFigureType.CARD_FIGURE) {
        state = new CardPlayTool(editor, game, game.getPlayerInTurn());
      } else if (hsf.getType() == HotStoneFigureType.TURN_BUTTON ||
              hsf.getType() == HotStoneFigureType.SWAP_BUTTON) {
        state = new EndTurnTool(editor, game);
      } else if (hsf.getType() == HotStoneFigureType.MINION_FIGURE) {
        state = theNullTool;
      } else if (hsf.getType() == HotStoneFigureType.HERO_FIGURE) {
        state = theNullTool;
      } else if (hsf.getType() == HotStoneFigureType.WIN_BUTTON) {
        state = theNullTool; // Have to kill the window to restart.
      }*/


    }
    state.mouseDown(e, x, y);
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    state.mouseUp(e, x, y);
    state = theNullTool;
  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y) {
    state.mouseDrag(e, x, y);
  }

  @Override
  public void mouseMove(MouseEvent e, int x, int y) {
    state.mouseMove(e, x, y);
  }

}
