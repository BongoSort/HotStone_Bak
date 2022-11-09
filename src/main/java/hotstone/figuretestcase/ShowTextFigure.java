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
import hotstone.view.figure.TextFigure;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

/** Visual test of the TextFigure. */

public class ShowTextFigure {
  
  public static void main(String[] args) {
    DrawingEditor editor =
      new MiniDrawApplication( "Showing a TextFigure",
                               new SimpleViewFactory() );
    editor.open();
    TextFigure textFigure = new TextFigure("Click anywhere to change the text...",
                                   new Point(200,200),
            Color.YELLOW, GfxConstants.LARGE_FONT_SIZE);
    editor.drawing().add(textFigure);
    editor.setTool( new ChangeTextTool(textFigure) );
  }
}

// A test stub tool.
class ChangeTextTool extends NullTool {
  private TextFigure textFigure;
  private String[] theTexts = { "Uno", "Dos", "Tres", "Cuatro", "Cinco",
          "Seis", "Siete"};

  public ChangeTextTool(TextFigure tf) {
    textFigure = tf;
  }

  int count = 0;
  public void mouseUp(MouseEvent e, int x, int y) {
    count++;
    textFigure.setText( theTexts[count % 7] );
  }
}
