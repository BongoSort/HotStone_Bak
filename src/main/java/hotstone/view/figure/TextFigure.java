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

import minidraw.standard.AbstractFigure;

import java.awt.*;

/** A figure representing a text. Is hard-wired to the HotStone systems'
 * requirements.
 */
public class TextFigure extends AbstractFigure {
  protected Color color;
  protected final Font fFont;
  protected boolean fSizeIsDirty;
  protected int fOriginX, fOriginY;
  protected String fText;
  protected int fWidth;
  protected int fHeight;

  public TextFigure(String text, Point position, Color color, int fontSize) {
    fFont = new Font("Serif", Font.BOLD, fontSize);
    fText = text;
    fOriginX = position.x;
    fOriginY = position.y;
    fSizeIsDirty = true;
    this.color = color;
  }

  protected void basicMoveBy(int x, int y) {
    fOriginX += x;
    fOriginY += y;
  }

  public Rectangle displayBox() {
    Dimension extent = textExtent();
    return new Rectangle(fOriginX, fOriginY,
            extent.width, extent.height);
  }

  public void setText(String newText) {
    if (!newText.equals(fText)) {
      willChange();
      fText = newText;
      fSizeIsDirty = true;
      changed();
    }
  }

  public void draw(Graphics g) {
    Rectangle bb = displayBox();
    if (g.getClipBounds().intersects(bb)) {
      Graphics2D g2 = (Graphics2D) g;

      g2.setFont(fFont);
      FontMetrics metrics = g2.getFontMetrics(fFont);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);
      // draw the text
      if (color != Color.BLACK) {
        g2.setPaint(Color.BLACK);
        g2.drawString(fText, fOriginX + 1, fOriginY + 1 + metrics.getAscent());
      }
      g2.setPaint(color);
      g2.drawString(fText, fOriginX, fOriginY + metrics.getAscent());
    }
  }

  protected Dimension textExtent() {
    if (!fSizeIsDirty)
      return new Dimension(fWidth, fHeight);
    FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(fFont);
    fWidth = metrics.stringWidth(fText);
    fHeight = metrics.getHeight();
    fSizeIsDirty = false;
    return new Dimension(metrics.stringWidth(fText), metrics.getHeight());
  }

  public void setColor(Color newColor) {
    if (newColor != color) {
      willChange();
      color = newColor;
      changed();
    }
  }
}
