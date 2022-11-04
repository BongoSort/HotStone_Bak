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

package hotstone.view.figure;

import hotstone.view.GfxConstants;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/** A MiniDraw figure defining a 'button' that users can press to
 * interact with the game flow.
 *
 * It is a rather stupid button as no visual clues are given that the
 * button has indeed been pressed. Maybe I will implement that next
 * year?
 */
public class ButtonFigure extends TextFigure
        implements HotStoneFigure {
  private final Insets extraSpace;
  private final HotStoneFigureType type;
  private Color buttonBackgroundColor;

  public ButtonFigure(HotStoneFigureType buttonType, String text, Point position) {
    super(text, position, Color.WHITE, GfxConstants.LARGE_FONT_SIZE);
    this.type = buttonType;
    extraSpace = new Insets(5,15,5,15);
    computeBackgroundColor();
  }

  public void setText(String newText) {
    super.setText(newText);
    computeBackgroundColor();
  }

  public Rectangle displayBox() {
    Dimension extent = textExtent();
    return new Rectangle(fOriginX - extraSpace.left,
            fOriginY - extraSpace.top,
            extent.width + extraSpace.left+extraSpace.right,
            extent.height + extraSpace.top+extraSpace.bottom);
  }

  // Hack: Hardcoding the background color to specific texts
  private void computeBackgroundColor() {
    if (fText.equals(GfxConstants.END_TURN_TEXT))
      buttonBackgroundColor = GfxConstants.YELLOW_COLOR;
    else
      buttonBackgroundColor = GfxConstants.GRAY_COLOR;
  }

  @Override
  public void draw(Graphics g) {
    Rectangle bb = displayBox();
    if (g.getClipBounds().intersects(bb)) {
      Graphics2D g2 = (Graphics2D) g;

      RoundRectangle2D box;
      box = new RoundRectangle2D.Double(bb.x, bb.y,
              bb.width - 1, bb.height - 1,
              15, 15);
      // draw the text box

      g2.setPaint(buttonBackgroundColor);
      g2.fill(box);

      g2.setPaint(Color.BLACK);
      g2.draw(box);

      super.draw(g);
    }
  }

  @Override
  public HotStoneFigureType getType() {
    return type;
  }
}
