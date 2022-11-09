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

package hotstone.figuretestcase;

import hotstone.figuretestcase.doubles.StubCard;
import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.view.figure.MinionFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.ZOrder;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.SelectionTool;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/** Visual Test of the MinionFigure. In addition, uses the
 * drawing.zOrder to bring minions to the top in the stack
 * of figures.
 */
public class ShowMinionFigure {

  public static void main(String[] args) {
    DrawingEditor editor =
      new MiniDrawApplication( "Showing Minions; can be moved, bringing the figure on top...",
                               new SimpleViewFactory() );
    editor.open();

    // Populate a list of cards
    List<Card> theList = new ArrayList<>();
    StubCard frenchFries = new StubCard(GameConstants.FRENCH_FRIES_CARD, Player.FINDUS, 5);
    frenchFries.setActiveTo(true);
    theList.add(frenchFries);

    theList.add(new StubCard(GameConstants.UNO_CARD, Player.FINDUS, 1));
    theList.add(new StubCard(GameConstants.DOS_CARD, Player.FINDUS, 2));
    theList.add(new StubCard(GameConstants.TRES_CARD, Player.FINDUS, 3));
    theList.add(new StubCard(GameConstants.CUATRO_CARD, Player.FINDUS, 4));
    theList.add(new StubCard(GameConstants.CINCO_CARD, Player.FINDUS, 5));
    theList.add(new StubCard(GameConstants.SEIS_CARD, Player.FINDUS, 6));
    theList.add(new StubCard(GameConstants.SIETE_CARD, Player.FINDUS, 7));

    theList.add(new StubCard(GameConstants.BROWN_RICE_CARD, Player.FINDUS, 2));
    theList.add(new StubCard(GameConstants.GREEN_SALAD_CARD, Player.FINDUS, 3));
    theList.add(new StubCard(GameConstants.TOMATO_SALAD_CARD, Player.FINDUS, 4));
    theList.add(new StubCard(GameConstants.POKE_BOWL_CARD, Player.FINDUS, 5));
    theList.add(new StubCard(GameConstants.PUMPKIN_SOUP_CARD, Player.FINDUS, 6));
    theList.add(new StubCard(GameConstants.NOODLE_SOUP_CARD, Player.FINDUS, 7));
    theList.add(new StubCard(GameConstants.SPRING_ROLLS_CARD, Player.FINDUS, 8));
    theList.add(new StubCard(GameConstants.BAKED_SALMON_CARD, Player.FINDUS, 7));
    theList.add(new StubCard(GameConstants.CHICKEN_CURRY_CARD, Player.FINDUS, 6));
    theList.add(new StubCard(GameConstants.BEEF_BURGER_CARD, Player.FINDUS, 5));
    theList.add(new StubCard(GameConstants.FILET_MIGNON_CARD, Player.FINDUS, 4));
    theList.add(new StubCard(GameConstants.SOVS_CARD, Player.FINDUS, 1));

    theList.add(new StubCard(GameConstants.MUSLI_BAR_CARD, Player.FINDUS, 0));
    theList.add(new StubCard(GameConstants.SHRIMP_COCKTAIL_CARD, Player.FINDUS, 3));
    theList.add(new StubCard(GameConstants.TZATZIKI_CARD, Player.FINDUS, 17));
    theList.add(new StubCard(GameConstants.LASAGNA_CARD, Player.FINDUS, 4) {
      @Override
      public int getAttack() {  return 9; }
    });

    // Perform a simple 'align minions nicely' algorithm
    // while creating the set of MinionFigures...
    int offset = 100; int y = 100; int count = 1;
    for (Card card : theList) {
      Figure f = new MinionFigure(card, new Point(offset, y));
      offset += 110;
      if (offset > 1000) {
        y = 100+200*count; offset = 100; count++;
      }
      editor.drawing().add(f);
    }

    // Just use the selection tool
    editor.setTool( new BringToFrontSelectionTool(editor));
  }

  private static class BringToFrontSelectionTool extends SelectionTool {
    public BringToFrontSelectionTool(DrawingEditor editor) {
      super(editor);
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
      // Iff there is a figure below (x,y)
      Figure f = editor().drawing().findFigure(x,y);
      // then bring it to the front in the Z order
      if (f != null) editor().drawing().zOrder(f, ZOrder.TO_TOP);
      super.mouseDown(e, x, y);
    }
  }
}
