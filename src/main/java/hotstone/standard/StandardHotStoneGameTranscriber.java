package hotstone.standard;

import hotstone.framework.*;
import hotstone.observer.GameObserver;


public class StandardHotStoneGameTranscriber extends StandardHotStoneGameDecorator{
    public StandardHotStoneGameTranscriber(Game game) {
        super(game);
    }

    /**
     * EndTurn
     */
    public void endTurn() {
        Player playerBefore = super.getPlayerInTurn();
        super.endTurn();
        System.out.println(playerBefore.toString() + " ended their turn. Starting turn " + super.getTurnNumber());
    }

    /**
     * PlayCard
     * @param who the player playing the card
     * @param card the card to play to the field.
     * @return status
     */
    public Status playCard(Player who, Card card) {
        Status status = super.playCard(who, card);
        if(status == Status.OK) {
            System.out.println(who.toString() + " played card " + card.getName());
        }
        return status;
    }

    /**
     * AttackHero
     */
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        Status status = super.attackHero(playerAttacking, attackingCard);
        Player opponent = Utility.computeOpponent(playerAttacking);
        if(status == Status.OK) {
            System.out.println(playerAttacking.toString() + " has attacked " + opponent.name() + " hero with minion " + attackingCard.getName());
        }
        return status;
    }


    /**
     * AttackCard
     */
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        Status status = super.attackCard(playerAttacking, attackingCard, defendingCard);
        if(status == Status.OK) {
            System.out.println(playerAttacking.toString() + " used minion " + attackingCard.getName() + " to attack minion " + defendingCard.getName());
        }
        return status;
    }

    /**
     * UsePower
     */
    public Status usePower(Player who) {
        Status status = super.usePower(who);
        if (status == Status.OK) {
            System.out.println(who.toString() + " used their hero power");
        }
        return status;
    }

    @Override
    public void addObserver(GameObserver observer) {
        super.addObserver(observer);
    }
}
