package hotstone.standard;

import hotstone.framework.*;

public abstract class StandardHotStoneGameDecorator implements Game {
    private Game game;

    public StandardHotStoneGameDecorator(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Hero getHero(Player who) {
        return game.getHero(who);
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getTurnNumber() {
        return game.getTurnNumber();
    }

    @Override
    public int getDeckSize(Player who) {
        return game.getDeckSize(who);
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return game.getCardInHand(who, indexInHand);
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return game.getHand(who);
    }

    @Override
    public int getHandSize(Player who) {
        return game.getHandSize(who);
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return game.getCardInField(who,indexInField);
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return game.getField(who);
    }

    @Override
    public int getFieldSize(Player who) {
        return game.getFieldSize(who);
    }

    @Override
    public void endTurn() {
        game.endTurn();
    }

    @Override
    public Status playCard(Player who, Card card) {
        return game.playCard(who, card);
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        return game.attackCard(playerAttacking, attackingCard, defendingCard);
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        return game.attackHero(playerAttacking, attackingCard);
    }

    @Override
    public Status usePower(Player who) {
        return game.usePower(who);
    }

}
