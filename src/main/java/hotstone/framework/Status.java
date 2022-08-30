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

package hotstone.framework;

/** The status enumeration defines the set of outcomes arising from
 * any mutator call to the Game.  Generally, if a mutation was
 * executed, then OK is returned, otherwise a more detailed failure
 * code is returned.
 */

public enum Status {
  // Everything went OK
  OK,
  // Codes for failure situations

  // Not enough mana to play card, use power, etc.
  NOT_ENOUGH_MANA,
  // Trying to attack with a minion that is not active
  ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION,
  // Trying to 'fiddle' with a card or hero owned by opponent
  NOT_OWNER,
  // Trying to do an action, but it is not the player's turn
  NOT_PLAYER_IN_TURN,
  // Trying to use hero power twice or more in a turn
  POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND,
  // Trying to attack your own minion (? should this be allowed)
  ATTACK_NOT_ALLOWED_ON_OWN_MINION,

  // The status code below is only relevant for a remote game:
  // If Findus acts on Peddersen's cards/hero while it is Peddersen's turn
  NOT_ALLOWED_TO_ACT_ON_BEHALF_OF_OPPONENT,

  // The status code below is only relevant if you implement ThetaStone
  // If attacking hero or non-taunt minion while taunt minion on field
  ATTACK_NOT_ALLOWED_ON_NON_TAUNT_MINION,
}
