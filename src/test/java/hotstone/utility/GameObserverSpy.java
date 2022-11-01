package hotstone.utility;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.observer.GameObserver;

import java.util.ArrayList;
import java.util.List;

public class GameObserverSpy implements GameObserver {
    private ArrayList<String> methodsCalled = new ArrayList<>();

    /**
     * Invoked when playCard() returns OK.
     *
     * @param who  player who plays the card
     * @param card the card being played
     */
    @Override
    public void onCardPlay(Player who, Card card) {
        methodsCalled.add("onCardPlay");
    }

    /**
     * Invoked when endTurn() returns OK.
     *
     * @param playerBecomingActive the player that
     *                             has become active
     */
    @Override
    public void onTurnChangeTo(Player playerBecomingActive) {
        methodsCalled.add("onTurnChangeTo");
    }

    /**
     * Invoked when attackCard() returns OK.
     *
     * @param playerAttacking the player making the attack
     * @param attackingCard   attacking minion
     * @param defendingCard   defending minion
     */
    @Override
    public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        methodsCalled.add("onAttackCard");
    }

    /**
     * Invoked when attackHero() returns OK.
     *
     * @param playerAttacking the player making the attack
     * @param attackingCard   the minion attacking
     */
    @Override
    public void onAttackHero(Player playerAttacking, Card attackingCard) {
        methodsCalled.add("onAttackHero");
    }

    /**
     * Invoked when usePower returns OK.
     *
     * @param who the player whose hero use its power
     */
    @Override
    public void onUsePower(Player who) {
        methodsCalled.add("onUsePower");
    }

    /**
     * Invoked when a card is drawn from the deck and
     * inserted into a player's hand.
     *
     * @param who       the player receiving the card
     * @param drawnCard the card that has been drawn
     */
    @Override
    public void onCardDraw(Player who, Card drawnCard) {
        methodsCalled.add("onCardDraw");
    }

    /**
     * Invoked when a minion (card in field) is
     * updated, i.e. health, attack, active status
     * are changed due to attack, hero power, etc.
     *
     * @param card the affected minion
     */
    @Override
    public void onCardUpdate(Card card) {
        methodsCalled.add("onCardUpdate");
    }

    /**
     * Invoked when a minion (card in field) is
     * defeated and removed from the game play.
     *
     * @param who  the owner of the card
     * @param card the defeated card
     */
    @Override
    public void onCardRemove(Player who, Card card) {
        methodsCalled.add("onCardRemove");
    }

    /**
     * Invoked when a hero is updated,
     * i.e. health, mana, active status are
     * changed due to attack, power use, etc.
     *
     * @param who the player owning the hero
     */
    @Override
    public void onHeroUpdate(Player who) {
        methodsCalled.add("onHeroUpdate");
    }

    /**
     * Invoked once when the game has determined
     * a winner.
     *
     * @param playerWinning the player winning
     */
    @Override
    public void onGameWon(Player playerWinning) {
        methodsCalled.add("OnGameWon");
    }

    public ArrayList<String> getLastMethodCalled() {
        return methodsCalled;
    }



}
