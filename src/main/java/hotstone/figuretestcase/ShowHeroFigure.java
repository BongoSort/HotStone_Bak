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

import hotstone.figuretestcase.doubles.StubHero;
import hotstone.framework.Hero;
import hotstone.view.GfxConstants;
import hotstone.view.figure.ButtonFigure;
import hotstone.view.figure.HeroFigure;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

/** Visual test of the Figure that represents a Hero, as well as code
 * (in the tool) for how to use the HotStoneFigureType to infer which
 * kind of figure you are interacting with and thus determine the
 * right cause of actions that user want.
 */
public class ShowHeroFigure {
  public static void main(String[] args) {
    DrawingEditor editor =
      new MiniDrawApplication( "Showing HeroFigure, Click Hero (only!) to change stats...",
                               new SimpleViewFactory() );
    editor.open();
    StubHero myHero = new StubHero();
    HeroFigure myHeroFigure = new HeroFigure(myHero, GfxConstants.MY_HERO_POSITION);

    Hero oppHero = new StubHero();
    HeroFigure oppHeroFigure = new HeroFigure(oppHero, GfxConstants.OPPONENT_HERO_POSITION);

    editor.drawing().add(myHeroFigure);
    editor.drawing().add(oppHeroFigure);

    editor.drawing().add(new ButtonFigure(HotStoneFigureType.TURN_BUTTON,
            "Press Me - but nothing happens", new Point(700,100)));

    editor.setTool(new ChangeHeroStatsTool(editor));
  }
}

// A tool to force state changes in a hero - using that our
// Stub hero has mutator methods available...
class ChangeHeroStatsTool extends NullTool {

  private DrawingEditor editor;

  public ChangeHeroStatsTool(DrawingEditor editor) {

    this.editor = editor;
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    // Find the figure just below the mouse (x,y)
    Figure figure = editor.drawing().findFigure(x,y);
    // Bail out fast, if there is none
    if (figure == null) {
      System.out.println("No Hero below your (x,y) = ("
              + x + ", " + y + ")");
      return;
    }
    // Bail out if figure is NOT a HotStoneFigure
    if (! (figure instanceof HotStoneFigure)) {
      System.out.println("Hm? A figure that is not a hot stone figure?");
      return;
    }

    // Bail out if figure is NOT a HeroFigure
    HotStoneFigureType type = ((HotStoneFigure)figure).getType();
    if (type != HotStoneFigureType.HERO_FIGURE) {
      System.out.println("Not a hero, the type was: " + type);
      return;
    }
    // We are now safe to cast and get the associated hero
    HeroFigure myHeroFigure = (HeroFigure)figure;
    Hero hero = myHeroFigure.getAssociatedHero();
    StubHero myHero = (StubHero) hero;
    // and can modify its state and tell the figure to update
    myHero.deltaMana( +1);
    myHero.setActive(myHero.getMana() % 2 == 1);
    myHeroFigure.updateStats();
  }
}
