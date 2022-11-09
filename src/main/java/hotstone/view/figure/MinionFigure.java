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

import java.awt.*;
import java.util.Map;

import static java.util.Map.entry;

/** Figure representing the card as a minion on the field,
 * that is, with a small frame and no mana cost, but
 * a Z figure to indicate if the minion is 'not active'.
 */
public class MinionFigure extends HotStoneActorFigure {
  private QuarterImage inactiveImage;

  public MinionFigure(Card associatedCard, Point position) {
    super(HotStoneFigureType.MINION_FIGURE,
            associatedCard, position,
            "frame",
            Map.ofEntries(
                    entry(FigureType.MANA_FIGURE, new Point(0,0)),
                    entry(FigureType.ATTACK_FIGURE, new Point(20,106)),
                    entry(FigureType.HEALTH_FIGURE, new Point(90,106)),
                    entry(FigureType.EMBLEM_FIGURE, new Point(10,9)),
                    entry(FigureType.ACTIVE_FIGURE, new Point(52,110)))
            );
    // Create the 'active' image (the green Z)
    inactiveImage = new QuarterImage("Z", new Point(0,0));

    // Force the ZZZ in case it is necessary
    updateStats();
  }

  @Override
  public void updateStats() {
    super.updateStats();
    writeLock().lock();
    try {
      if (!associatedCard.isActive()) {
        Point Zpos = new Point(displayBox.x, displayBox.y);
        Zpos.translate(positions.get(FigureType.ACTIVE_FIGURE).x,
                positions.get(FigureType.ACTIVE_FIGURE).y);
        inactiveImage.moveTo(Zpos.x, Zpos.y);
        add(inactiveImage);
      } else {
        remove(inactiveImage);
      }
    } finally {
      writeLock().unlock();
    }
  }
}
