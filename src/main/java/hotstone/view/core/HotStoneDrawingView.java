/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.view.core;

import hotstone.framework.Player;
import hotstone.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.StandardDrawingView;

import java.awt.*;

/** The drawing view of HotStone is extremely simple, providing
 * just a colored background, depending upon who is playing.
 */
public class HotStoneDrawingView extends StandardDrawingView {
  private final Color color;

  public HotStoneDrawingView(DrawingEditor editor, Player whoToPlay) {
    super(editor);
    if (whoToPlay == Player.FINDUS)
      color = GfxConstants.FIELD_COLOR_FINDUS;
    else
      color = GfxConstants.FIELD_COLOR_PEDDERSEN;
    setSize(GfxConstants.SCREEN_WIDTH_PIXELS, GfxConstants.SCREEN_HEIGHT_PIXELS);
  }

  @Override
  public void drawBackground(Graphics g) {
    g.setColor(color);
    g.fillRect(0, 0, getBounds().width, getBounds().height);
  }

  public void drawDrawing(Graphics g) {
    super.drawDrawing(g);
  }
}
