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

package hotstone.visualtestcase;

import hotstone.figuretestcase.doubles.FakeObjectGame;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.AlphaStoneConcreteFactory;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Visual tests of the ability of HotStoneDrawing to respond to
 * observer events notified by the Game instance - i.e. the Domain
 * to the UI flow of events.
 */
public class ShowUpdate {
  public static void main(String[] args) {
    //Game game = new FakeObjectGame();
    Game game = new StandardHotStoneGame(new AlphaStoneConcreteFactory());

    DrawingEditor editor =
      new MiniDrawApplication( "Click anywhere to progress in an update sequence...",
                               new HotStoneFactory(game, Player.FINDUS,
                                       HotStoneDrawingType.OPPONENT_MODE) );
    editor.open();
    editor.setTool( new TriggerGameUpdateTool(editor, game) );
  }
}

class TriggerGameUpdateTool extends NullTool {
  private DrawingEditor editor;
  private Game game;
  private int count;

  public TriggerGameUpdateTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;
    count = 0;
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    switch (count) {
      case 0 -> {
        editor.showStatus("Playing Findus Tres Card");
        Card c = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, c);
      }
      case 1 -> {
        editor.showStatus("Findus ends turn");
        game.endTurn();
      }
      case 2 -> {
        editor.showStatus("Playing Peddersens Uno and Dos Card");
        Card c1 = game.getCardInHand(Player.PEDDERSEN, 3);
        Card c2 = game.getCardInHand(Player.PEDDERSEN, 2);
        game.playCard(Player.PEDDERSEN, c1);
        game.playCard(Player.PEDDERSEN, c2);
      }
      case 3 -> {
        editor.showStatus("Peddersen ends turn");
        game.endTurn();
      }
      case 4 -> {
        editor.showStatus("Findus attacks Peddersens card Uno with Tres");
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        Card peddersensCard = game.getCardInField(Player.PEDDERSEN, 1);
        game.attackCard(Player.FINDUS, findusCard, peddersensCard);
      }
      case 5 -> {
        editor.showStatus("Advance game one round so it is findus turn again");
        game.endTurn();
        game.endTurn();
      }
      case 6 -> {
        editor.showStatus("Findus attacks peddersenshero with Tres");
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        game.attackHero(Player.FINDUS, findusCard);
      }
      case 7 -> {
        editor.showStatus("Findus uses hero power");
        game.usePower(Player.FINDUS);
        break;
      }
    }
    count++;
  }
}
