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

package hotstone.view.message;

import hotstone.view.GfxConstants;
import minidraw.standard.AbstractFigure;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/** A MiniDraw Figure that shows a message in a semi transparent 
    box. Use a Messenger as wrapper to provide a message box that
    disappears automatically.
*/
public class MessageFigure extends AbstractFigure {

  protected Font fFont;
  protected String fText;
  protected int fOriginX, fOriginY;
  protected int fWidth, fHeight;
  protected boolean fSizeIsDirty;
  protected Insets extraSpace;

  public MessageFigure() {
    fFont = new Font("SansSerif", Font.PLAIN, 14);
    fText = "";
    fOriginX = fOriginY = 0;
    fSizeIsDirty = true;
    extraSpace = new Insets(5,20,5,20);
  }

  protected void basicMoveBy(int x, int y) {
    fOriginX += x;
    fOriginY += y;
  }
  
  public void basicDisplayBox(Point newOrigin, Point newCorner) {
    fOriginX = newOrigin.x;
    fOriginY = newOrigin.y;
  }

  public Rectangle displayBox() {
    Dimension extent = textExtent();
    return new Rectangle(fOriginX - extraSpace.left, 
                         fOriginY - extraSpace.top, 
                         extent.width + extraSpace.left+extraSpace.right, 
                         extent.height + extraSpace.top+extraSpace.bottom);
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
    // bounding box
    Rectangle bb = displayBox();
    if (g.getClipBounds().intersects(bb)) {
      Graphics2D g2 = (Graphics2D) g;

      g2.setFont(fFont);
      FontMetrics metrics = g2.getFontMetrics(fFont);
      //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      //                    RenderingHints.VALUE_ANTIALIAS_ON);
      Composite
              transparent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f),
              full = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);

      RoundRectangle2D box;
      box = new RoundRectangle2D.Double(bb.x, bb.y, bb.width - 1, bb.height - 1,
              25, 25);
      // draw the text box
      g2.setComposite(transparent);
      g2.setPaint(GfxConstants.MESSAGE_BLUE);
      g2.fill(box);
      g2.setComposite(full);
      g2.draw(box);

      g2.setRenderingHint(
              RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);

      // draw the text
      g2.setPaint(GfxConstants.MESSAGE_SHADOW);
      g2.drawString(fText, fOriginX + 1, fOriginY + 1 + metrics.getAscent());
      g2.setPaint(Color.white);
      g2.drawString(fText, fOriginX, fOriginY + metrics.getAscent());
    }
  }

  private Dimension textExtent() {
    if (!fSizeIsDirty)
      return new Dimension(fWidth, fHeight);
    FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(fFont);
    fWidth = metrics.stringWidth(fText);
    fHeight = metrics.getHeight();
    fSizeIsDirty = false;
    return new Dimension(metrics.stringWidth(fText), metrics.getHeight());
  }

  private void markDirty() {
    fSizeIsDirty = true;
  }
}
