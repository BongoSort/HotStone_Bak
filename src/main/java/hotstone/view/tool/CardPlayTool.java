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

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.view.GfxConstants;
import hotstone.view.figure.HotStoneActorFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.ZOrder;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Almost complete implementation of CardPlayTool: a MiniDraw tool
 * to play a card.
 *
 * Much of the behaviour in this tool revolves around being able to
 * move the card figure BACK in case the game tells us that the
 * card could NOT be played (status != Status.OK).
 */

public class CardPlayTool extends NullTool {
  private DrawingEditor editor;
  private Game game;
  private HotStoneActorFigure draggedActor;
  private int lastX;
  private int lastY;
  private int orgX;
  private int orgY;
  private Player whoAmIPlaying;

  public CardPlayTool(DrawingEditor editor, Game game, Player whoAmIPlaying) {
    this.editor = editor;
    this.game = game;
    this.whoAmIPlaying = whoAmIPlaying;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    Drawing model = editor.drawing();
    Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
    draggedActor = (HotStoneActorFigure) figureAtPosition;
    model.zOrder(draggedActor, ZOrder.TO_TOP);
    lastX = x;
    lastY = y;
    orgX = x;
    orgY = y;
  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y) {
    // compute relative movement
    draggedActor.moveBy(x - lastX, y - lastY);
    // update last position
    lastX = x;
    lastY = y;
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    // Invoke related facade method, if figure is a card
    boolean isDraggingAnActor = draggedActor != null;
    boolean isHittingField = y < GfxConstants.Y_LIMIT_OF_FIELD;
    boolean moveCardBack = true;

    if (isDraggingAnActor && isHittingField) {
      Card associatedCard = draggedActor.getAssociatedCard();
      Status status = game.playCard(associatedCard.getOwner(), associatedCard);
      if (status == Status.OK) {
        moveCardBack = false;
      }
      editor.showStatus("Draw card from hand. Result =" + status);
      if (moveCardBack) {
        // move back to original position
        draggedActor.moveBy(orgX - x, orgY - y);
      }
      draggedActor = null;
    }
  }
}
