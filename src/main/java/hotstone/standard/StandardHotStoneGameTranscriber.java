package hotstone.standard;

import hotstone.framework.*;

import java.util.ArrayList;

public class StandardHotStoneGameTranscriber extends StandardHotStoneGameDecorator{
    private ArrayList<String> list;
    public StandardHotStoneGameTranscriber(Game game) {
        super(game);
        list = new ArrayList<>();
    }

    /**
     * EndTurn
     */
    public void endTurn() {
        list.add(super.getPlayerInTurn() + " ended their turn. Starting turn " + super.getTurnNumber());
    }

    public Status playCard(Player who, Card card) {
        Status status = super.playCard(who, card);
        if(status == Status.OK) {
            list.add(who.name() + " played card " + card.getName());
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
            list.add(playerAttacking.name() + " has attacked " + opponent.name() + " hero with minion " + attackingCard.getName());
        }
        return status;
    }


    /**
     * AttackCard
     */
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        Status status = super.attackCard(playerAttacking, attackingCard, defendingCard);
        if(status == Status.OK) {
            list.add(playerAttacking.name() + " used minion " + attackingCard.getName() + " to attack minion " + defendingCard.getName());
        }
        return status;
    }

    /**
     * UsePower
     */
    public Status usePower(Player who) {
        Status status = super.usePower(who);
        if (status == Status.OK) {
            list.add(who.name() + " used their hero power");
        }
        return status;
    }

}
