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

package hotstone.view;

import java.awt.*;

/** A collection of graphical constants for the layout of
 * the UI of HotStone.
 */
public class GfxConstants {
  // Set the windows size. DO NOT CHANGE THESE - ALL OTHER GFX POSITIONS ASSUME
  // THESE VALUES TO BE CONSTANT AT (1200, 900). SORRY - MiniDraw has no
  // viewport handling, it operates in raw window pixels positions.
  public static final int SCREEN_WIDTH_PIXELS = 1200;
  public static final int SCREEN_HEIGHT_PIXELS = 900;

  // Position of central graphical actors
  public static final Point MY_HERO_POSITION = new Point(1000,676);
  public static final Point MY_HERO_POWER_DESCRIPTION_POSITION = new Point(840,870);
  public static final Point OPPONENT_HERO_POSITION = new Point(0, 0);
  public static final Point OPPONENT_SUMMARY_POSITION = new Point(180, 0);
  public static final Point OPPONENT_HERO_POWER_DESCRIPTION_POSITION = new Point(180, 20);

  public static final Point TURN_BUTTON_POSITION = new Point(1050,20);  //  or y = 420
  public static final Point HOTSEAT_BUTTON_POSITION = new Point(840,20);
  public static final Point WIN_BUTTON_POSITION = new Point(500, 450);

  // If y is above this limit, then the card play tool will consider the card
  // dropped upon the player's field (i.e. call game.playCard())
  public static final int Y_LIMIT_OF_FIELD = 600;

  // The two font sizes used
  public static final int SMALL_FONT_SIZE = 16;
  public static final int LARGE_FONT_SIZE = 20;

  // The distance between fielded minions
  public static final int FIELDED_CARD_DISTANCE = 150;

  // And distances in the hand
  public static final int HAND_CARD_DISTANCE = 100;
  public static final int HAND_CARD_OFFSET = 10;
  // Y coordinate of where my hand cards are positioned
  public static final int MY_HAND_POSITION_Y = 660;

  // The Y coordinate of the minions of the field for me and opponent respectively
  public static final int MY_FIELD_Y_POSITION = 500;
  public static final int OPPONENT_FIELD_Y_POSITION = 300;
  // X offset used in calculating center position for minions
  public static final int X_FIELD_OFFSET = 30;

  // The offsets of the Hero's 'Z' text when inactive
  public static final int HERO_IS_NOT_ACTIVE_X = 90;
  public static final int HERO_IS_NOT_ACTIVE_Y = 170;

  // Text on buttons
  public static final String END_TURN_TEXT = "End Turn";
  public static final String NEXT_ACTION_TEXT = "Next Opp Act";

  // Message system
  public static final int DISPLAY_TIME_MESSAGES_MS = 3000;
  public static final int MESSAGE_X = 800;
  public static final int MESSAGE_Y = 80;
  public static final int MESSAGE_HEIGHT = 28;

  // Colors in the message system
  public final static Color MESSAGE_BLUE = new Color(0,123,198);
  public final static Color MESSAGE_SHADOW = new Color(70,88,101);

  // Colors used for field, text etc
  public static final Color YELLOW_COLOR = new Color(200,200,0);
  public static final Color GRAY_COLOR = Color.DARK_GRAY;
  public static final Color FIELD_COLOR_FINDUS = new Color(0, 77, 25);
  public static final Color FIELD_COLOR_PEDDERSEN = new Color(118, 65, 0);
}
