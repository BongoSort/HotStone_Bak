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

/** Role of a Card in HotStone. The present interface presents a
 * read-only view of the card to respect that a client may only alter
 * the internal state of a card through the Game's mutator methods.
 *
 * Note that a card played onto the (battle) field is often called a
 * 'minion' in the comments and associated documentation, but it _is_
 * a card, and the interfaces only talks in terms of cards.
 */

public interface Card {
  /** Get the name of the card.
   *
   * @return the name
   */
  String getName();

  /** Get the cost of the card (in mana)
   * for playing the card from the hand to the
   * field.
   *
   * @return cost in mana
   */
  int getManaCost();

  /** Get the value of the attack of this
   * card.
   *
   * @return the attack value
   */
  int getAttack();

  /** Get the value of the health of
   * this card
   *
   * @return the health value
   */
  int getHealth();

  /** Get that active status, only
   * if active is true is the card/minion
   * allowed to attack.
   *
   * @return boolean active state
   */
  boolean isActive();

  /** Get the owner of this card
   *
   * @return owner
   */
  Player getOwner();
}
