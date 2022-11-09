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

package hotstone.view.figure;

import hotstone.framework.Card;

import java.awt.*;
import java.util.Map;

import static java.util.Map.entry;

/** Figure representing a HotStone card as it appears in the hand,
 * that is, with full card frame and mana cost.
 */
public class CardFigure extends HotStoneActorFigure implements HotStoneFigure  {
  private final TextFigure manaText;
  private final TextFigure effectText;

  public CardFigure(Card associatedCard, Point position) {
    super(HotStoneFigureType.CARD_FIGURE,
            associatedCard, position,
            "card-basis",
            Map.ofEntries(
                    entry(FigureType.MANA_FIGURE, new Point(20,20)),
                    entry(FigureType.ATTACK_FIGURE, new Point(20,188+22)),
                    entry(FigureType.HEALTH_FIGURE, new Point(149,190+22)),
                    entry(FigureType.EMBLEM_FIGURE, new Point(41,6)),
                    entry(FigureType.ACTIVE_FIGURE, new Point(52,110)),
                    entry(FigureType.EFFECT_FIGURE, new Point(55,170))));

    writeLock().lock();
    try {
      // Add the Mana label
      Point manaPos = (Point) position.clone();
      manaPos.translate(positions.get(FigureType.MANA_FIGURE).x,
              positions.get(FigureType.MANA_FIGURE).y);
      manaText = new TextFigure("" + associatedCard.getManaCost(),
              manaPos, Color.WHITE, 16);
      add(manaText);

      Point effectPos = (Point) position.clone();
      effectPos.translate(positions.get(FigureType.EFFECT_FIGURE).x,
              positions.get(FigureType.EFFECT_FIGURE).y);
      if(associatedCard.getEffectDescription() != null) {
        effectText = new TextFigure("" + associatedCard.getEffectDescription(),
                effectPos, Color.WHITE, 16);
      } else {
        effectText = new TextFigure("",
                effectPos, Color.WHITE, 16);
      }
      add(effectText);
      
    } finally {
      writeLock().unlock();
    }
  }
}
