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

/** Enumeration of the different types of interactive figures that MiniDraw
 * must handle in the HotStone UI.
 */
public enum HotStoneFigureType {
  // Figure types representing game concepts
  CARD_FIGURE, HERO_FIGURE, MINION_FIGURE,
  // Figures representing buttons on the UI
  TURN_BUTTON, SWAP_BUTTON, WIN_BUTTON, OPPONENT_ACTION_BUTTON
}
