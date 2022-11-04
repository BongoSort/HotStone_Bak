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

import hotstone.framework.Card;
import hotstone.view.GfxConstants;
import minidraw.framework.Figure;
import minidraw.standard.CompositeFigure;

import java.awt.*;
import java.util.Map;

/** The Actor figure class representing both minions and cards
 * in the hand. While it IS a MiniDraw Figure, there is a
 * tradition to call graphical objects that move around in 2D
 * for 'actors' in game engines (for instance in LibGDX) so
 * we follow that tradition here.
 *
 * And yes - inheritance provides value here :) - there is
 * behavioural difference between card and minion actors.
 *
 * A card/minion is basically a Composite Pattern in that it
 * is a composition of the card frame, the 'emblem' (the
 * center image), perhaps a Z image to show active status,
 * and texts to show health, attack etc.
 */
public abstract class HotStoneActorFigure extends CompositeFigure
        implements HotStoneFigure {

  protected final Figure minion;
  protected final Figure frame;

  protected final Rectangle displayBox;

  protected TextFigure healthText;
  protected TextFigure attackText;

  protected final Card associatedCard;

  protected Map<FigureType, Point> positions;
  private final HotStoneFigureType type;

  public HotStoneActorFigure(HotStoneFigureType type, Card associatedCard,
                             Point position,
                             String frameImageName,
                             Map<FigureType, Point> positions) {
    this.type = type;
    this.associatedCard = associatedCard;
    this.positions = positions;

    writeLock().lock();
    try {
      // Add minion emblem
      Point emblemPos = (Point) position.clone();
      emblemPos.translate(positions.get(FigureType.EMBLEM_FIGURE).x,
              positions.get(FigureType.EMBLEM_FIGURE).y);
      minion = new QuarterImage(associatedCard.getName(), emblemPos);
      add(minion);

      // Add frame on top
      frame = new QuarterImage(frameImageName, position);
      add(frame);

      // Add attack gfx
      Point attackPos = (Point) position.clone();
      attackPos.translate(positions.get(FigureType.ATTACK_FIGURE).x,
              positions.get(FigureType.ATTACK_FIGURE).y);
      attackText = new TextFigure("" + associatedCard.getAttack(),
              attackPos, Color.WHITE, GfxConstants.SMALL_FONT_SIZE);
      add(attackText);

      // Add health gfx
      Point healthPos = (Point) position.clone();
      healthPos.translate(positions.get(FigureType.HEALTH_FIGURE).x,
              positions.get(FigureType.HEALTH_FIGURE).y);
      healthText = new TextFigure("" + associatedCard.getHealth(),
              healthPos, Color.YELLOW, GfxConstants.SMALL_FONT_SIZE);
      add(healthText);

      // Adjust displaybox
      displayBox = new Rectangle(position.x, position.y,
              frame.displayBox().width + 1, frame.displayBox().height);
    } finally {
      writeLock().unlock();
    }
  }

  @Override
  public Rectangle displayBox() {
    return displayBox;
  }

  @Override
  protected void basicMoveBy(int dx, int dy) {
    super.basicMoveBy(dx, dy);
    displayBox.translate(dx, dy);
  }

  /** Override to provide 'poor mans animation'. */
  @Override
  public void moveTo(int absoluteX, int absoluteY) {

    // Alternative 1 algorithm
    super.moveTo(absoluteX, absoluteY);

    // Alternative2: Use a poor-man's-animation
    /*
    Thread animator = new Thread( () -> {
      int dx = absoluteX - displayBox.x ;
      int dy = absoluteY - displayBox.y;

      double resolution = 25.0;
      double dxtenth = dx / resolution;
      double dytenth = dy / resolution;
      for (int i = 0; i < resolution; i++) {
        moveBy((int)dxtenth, (int)dytenth);
        try {
          Thread.sleep(10);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      // Ensure hitting the target
      dx = absoluteX - displayBox.x ;
      dy = absoluteY - displayBox.y;
      moveBy(dx, dy); // ensure to trigger observers

    });
    animator.start(); */
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);

    // Debug gfx: turn on the bounding box
    /*
    g.setColor(Color.ORANGE);
    g.drawRect(displayBox().x, displayBox().y,
            displayBox().width - 1, displayBox().height - 1);
        */
  }

  public Card getAssociatedCard() {
    return associatedCard;
  }

  @Override
  public HotStoneFigureType getType() { return type; }

  /** Redraw health etc */
  public void updateStats() {
    writeLock().lock();
    try {
      int attack = associatedCard.getAttack();
      String attackTextValue = "" + attack;
      attackText.setText(attackTextValue);

      healthText.setText("" + associatedCard.getHealth());
    } finally {
      writeLock().unlock();
    }
  }
}
