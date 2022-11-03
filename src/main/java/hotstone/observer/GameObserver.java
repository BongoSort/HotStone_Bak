/*
 * Copyright (C) 2022. Henrik BÃ¦rbak Christensen, Aarhus University.
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

package hotstone.observer;

import hotstone.framework.Card;
import hotstone.framework.Player;

/** The role of an Observer on a Game (the Subject).
 * This observer interface follows the PUSH variant of
 * the observer pattern, that is, the observer receives
 * specific information about what exactly changed state
 * in the game.
 *
 * The convention used here is to start all Observer
 * update methods by the name 'on...', so an observer will be
 * notified by the 'onCardRemove()' method being called when
 * a card is removed in the game.
 *
 * Note also that this observer provides notification on
 * direct game mutation events besides notifications on
 * the side-effects. They are in section 'Direct mutator
 * events' in the interface below.
 *
 * Note also that the notifications should ONLY be sent
 * when the mutator actually executes - that is - if
 * a playCard() call is illegal (status != Status.OK)
 * then NO notification 'onCardPlay' should be sent (the
 * card was not played, right!)
 *
 * The reason for this design decision is to allow
 * a player to be notified about the actions of the
 * opponent, ala 'Findus is notified that Peddersen
 * played card X to the field'. In a remote game,
 * this is of course essential to know.
 */
public interface GameObserver {
    // === Direct mutator events

    /** Invoked when playCard() returns OK.
     *
     * @param who player who plays the card
     * @param card the card being played
     */
    void onCardPlay(Player who, Card card);

    /** Invoked when endTurn() returns OK.
     *
     * @param playerBecomingActive the player that
     *                             has become active
     */
    void onTurnChangeTo(Player playerBecomingActive);

    /** Invoked when attackCard() returns OK.
     *
     * @param playerAttacking the player making the attack
     * @param attackingCard attacking minion
     * @param defendingCard defending minion
     */
    void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard);

    /** Invoked when attackHero() returns OK.
     *
     * @param playerAttacking the player making the attack
     * @param attackingCard the minion attacking
     */
    void onAttackHero(Player playerAttacking, Card attackingCard);

    /** Invoked when usePower returns OK.
     *
     * @param who the player whose hero use its power
     */
    void onUsePower(Player who);

    // === Indirect events - state changes that are
    // an indirect consequence of a mutator call

    /** Invoked when a card is drawn from the deck and
     * inserted into a player's hand.
     * @param who the player receiving the card
     * @param drawnCard the card that has been drawn
     */
    void onCardDraw(Player who, Card drawnCard);

    /** Invoked when a minion (card in field) is
     * updated, i.e. health, attack, active status
     * are changed due to attack, hero power, etc.
     * @param card the affected minion
     */
    void onCardUpdate(Card card);

    /** Invoked when a minion (card in field) is
     * defeated and removed from the game play.
     * @param who the owner of the card
     * @param card the defeated card
     */
    void onCardRemove(Player who, Card card);

    /** Invoked when a hero is updated,
     * i.e. health, mana, active status are
     * changed due to attack, power use, etc.
     * @param who the player owning the hero
     */
    void onHeroUpdate(Player who);

    /** Invoked once when the game has determined
     * a winner.
     * @param playerWinning the player winning
     */
    void onGameWon(Player playerWinning);
}