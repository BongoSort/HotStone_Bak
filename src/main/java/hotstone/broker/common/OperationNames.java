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

package hotstone.broker.common;

/** Operation names for methods in HotStone Game, Card, Hero. */

public class OperationNames {

  // Separator between type and method
  public static final char SEPARATOR = '_';

  // Types
  public static final String GAME_PREFIX = "game";
  public static final String CARD_PREFIX = "card";
  public static final String HERO_PREFIX = "hero";

  // Game methods
  public static final String GAME_GET_WINNER = GAME_PREFIX + SEPARATOR + "get-winner";
  public static final String GAME_GET_HAND_SIZE = GAME_PREFIX + SEPARATOR + "get-hand-size";
  public static final String GAME_GET_CARD_IN_HAND = GAME_PREFIX + SEPARATOR + "get-card-in-hand";
  public static final String GAME_GET_HAND = GAME_PREFIX + SEPARATOR + "get-hand";
  public static final String GAME_GET_PLAYER_IN_TURN = GAME_PREFIX + SEPARATOR + "get-player-in-turn";
  public static final String GAME_PLAY_CARD = GAME_PREFIX + SEPARATOR + "play-card";
  public static final String GAME_GET_DECK_SIZE = GAME_PREFIX + SEPARATOR + "get-deck-size";
  public static final String GAME_GET_TURN_NUMBER = GAME_PREFIX + SEPARATOR + "get-turn-number";
  public static final String GAME_GET_CARD_IN_FIELD = GAME_PREFIX + SEPARATOR + "get-card-in-field";
  public static final String GAME_GET_FIELD_SIZE = GAME_PREFIX + SEPARATOR + "get-field-size";
  public static final String GAME_GET_FIELD = GAME_PREFIX + SEPARATOR + "get-field";
  public static final String GAME_GET_HERO = GAME_PREFIX + SEPARATOR + "get-hero";

  public static final String GAME_END_OF_TURN = GAME_PREFIX + SEPARATOR + "end-of-turn";
  public static final String GAME_ATTACK_CARD = GAME_PREFIX + SEPARATOR + "attack-card";
  public static final String GAME_ATTACK_HERO = GAME_PREFIX + SEPARATOR + "attack-hero";
  public static final String GAME_USE_POWER = GAME_PREFIX + SEPARATOR + "use-power";

  // Card methods
  public static final String CARD_GET_NAME = CARD_PREFIX + SEPARATOR + "get-name";
  public static final String CARD_GET_MANA_COST = CARD_PREFIX + SEPARATOR + "get-mana-cost";
  public static final String CARD_GET_ATTACK = CARD_PREFIX + SEPARATOR + "get-attack";
  public static final String CARD_GET_HEALTH = CARD_PREFIX + SEPARATOR + "get-health";
  public static final String CARD_IS_ACTIVE = CARD_PREFIX + SEPARATOR + "is-active";
  public static final String CARD_GET_OWNER = CARD_PREFIX + SEPARATOR + "get-owner";

  // Hero methods
  public static final String HERO_GET_TYPE = HERO_PREFIX + SEPARATOR + "get-type";
  public static final String HERO_GET_MANA = HERO_PREFIX + SEPARATOR + "get-mana";
  public static final String HERO_GET_HEALTH = HERO_PREFIX + SEPARATOR + "get-health";
  public static final String HERO_IS_ACTIVE = HERO_PREFIX + SEPARATOR + "is-active";
  public static final String HERO_GET_OWNER = HERO_PREFIX + SEPARATOR + "get-owner";

}
