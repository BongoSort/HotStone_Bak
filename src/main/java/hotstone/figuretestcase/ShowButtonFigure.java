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

import hotstone.view.GfxConstants;
import hotstone.view.figure.ButtonFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Visual test to demonstrate the ButtonFigure. */
public class ShowButtonFigure {
  
  public static void main(String[] args) {

    DrawingEditor editor =
      new MiniDrawApplication( "Showing a ButtonFigure, Click anywhere to toggle text...",
                               new SimpleViewFactory() );
    editor.open();
    ButtonFigure buttonFigure = new ButtonFigure(
            HotStoneFigureType.TURN_BUTTON,
            GfxConstants.END_TURN_TEXT,
            GfxConstants.HOTSEAT_BUTTON_POSITION);

    editor.drawing().add(buttonFigure);

    editor.setTool( new ChangeButtonTextTool(buttonFigure) );
  }
}

class ChangeButtonTextTool extends NullTool {
  int count = 0;
  private ButtonFigure buttonFigure;

  public ChangeButtonTextTool(ButtonFigure buttonFigure) {

    this.buttonFigure = buttonFigure;
  }

  public void mouseUp(MouseEvent e, int x, int y) {
    count++;
    String text = count % 2 == 0 ? GfxConstants.END_TURN_TEXT :
            "Over to Other";
    buttonFigure.setText( text );
  }
}
