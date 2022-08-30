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

package hotstone.framework;

/** The role of a single HotStone game, allowing clients to access and
 * modify the state of a game.
 */
public interface Game {
  // === Accessors for Game state

  /** Get the player who currently can make actions (call mutators) on
   * game.
   *
   * @return the player in turn.
   */
  Player getPlayerInTurn();

  /** Get the hero for the given player.
   *
   * @param who the owning player
   * @return the hero
   */
  Hero getHero(Player who);

  /** Get who has won the game.
   *
   * @return null if game is still progressing, or the winning player
   * if the game has been won.
   */
  Player getWinner();

  /** Get the number of turns played. Starts at turn 0 for Findus,
   * then 1 for Peddersen, 2 for Findus, and so on.
   *
   * @return the turn number of the game progress
   */
  int getTurnNumber();

  /** Get the number of cards still in the drawing deck for a given
   * player.
   *
   * @param who the player whose deck to inspect
   * @return number of cards left in the deck
   */
  int getDeckSize(Player who);

  // === Accessors for Hand

  /** Get the card at a given index in the hand.  Index goes from 0 up
   * till 'getHandSize()-1'.  If a card is added to the hand, it is
   * put into position 0, all other cards are pushed one position 'to
   * the right'.  PRECONDITION: indexInHand MUST be in interval
   * 0..handsize-1.
   *
   * @param who the player whose hand to inspect
   * @param indexInHand the index of the card to retrieve. MUST be
   *                    0..handsize-1.
   * @return the card in the hand at that position.
   */
  Card getCardInHand(Player who, int indexInHand);

  /** Get an iterable over the cards in the hand. Convenience method
   * to allow writing code ala 
   * 'for (Card c: game.getHand(Player.FINDUS)) { ... }'.
   *
   * @param who the player owning the hand
   * @return an iterable over the cards in the hand
   */
  Iterable<? extends Card> getHand(Player who);

  /** Get the number of cards in the hand.
   *
   * @param who the player owning the hand
   * @return the number of cards in hand
   */
  int getHandSize(Player who);

  // === Accessors for Field

  /** Get the card at a given index on the field.  Index goes from 0
   * up till 'getFieldSize()-1'.  If a card is added to the field, it
   * is put into position 0, all other cards are pushed one position
   * 'to the right'.  PRECONDITION: indexInField MUST be in interval
   * 0..fieldsize-1.
   *
   * @param who the player whose field to inspect
   * @param indexInField the index of the card to retrieve. MUST be
   *                    0..fieldsize-1.
   * @return the card on the field at that position.
   */

  Card getCardInField(Player who, int indexInField);
  
  /** Get an iterable over the cards on the field. Convenience method
   * to allow writing code ala 
   * 'for (Card c: game.getField(Player.FINDUS)) { ... }'.
   *
   * @param who the player owning the field
   * @return an iterable over the cards on the field
   */

  Iterable<? extends Card> getField(Player who);
  
  /** Get the number of cards on the field.
   *
   * @param who the player owning the field
   * @return the number of cards on field
   */
  int getFieldSize(Player who);

  // === Mutators

  /** Perform end of turn for current player, to prepare for the
   * opponent's turn.  PRECONDITION: The client MUST ensure that
   * endTurn() is never called by the player which is NOT in turn.
   */
  void endTurn();

  /** Play a card from the hand to the field.  PRECONDITION: the card
   * is a non-null card retrieved by the 'getCardInHand()' method.
   *
   * @param who the player playing the card
   * @param card the card to play to the field.
   * @return a status identifying the outcome of the play, which is
   *    either OK or some value explaining why the action was invalid.
   */
  Status playCard(Player who, Card card);

  /** Attack one card with another on the fields.  PRECONDITION: both
   * cards are a non-null card retrieved by the 'getCardInField()'
   * method.
   *
   * @param playerAttacking the player making the attack
   * @param attackingCard the card attacking
   * @param defendingCard the card defending
   * @return a status identifying the outcome of the attack, which is
   *    either OK or some value explaining why the action was invalid.
   */
  Status attackCard(Player playerAttacking,
                    Card attackingCard, Card defendingCard);

  /** Attack a hero with a card in the field.  PRECONDITION: the card
   * is a non-null card retrieved by the 'getCardInField()' method.
   *
   * @param playerAttacking the player making the attack
   * @param attackingCard the card attacking
   * @return a status identifying the outcome of the attack, which is
   *    either OK or some value explaining why the action was invalid.
   */
  Status attackHero(Player playerAttacking, Card attackingCard);

  /** Use a hero's special power/effect.
   *
   * @param who the player using his/her hero's power
   * @return a status identifying the outcome of the power use, which
   *    is either OK or some value explaining why the action was
   *    invalid.
   */
  Status usePower(Player who);
}
