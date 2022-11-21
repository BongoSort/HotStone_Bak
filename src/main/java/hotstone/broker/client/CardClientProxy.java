package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;

public class CardClientProxy implements Card, ClientProxy {

    private static final String CARD_OBJECTID = "CardIDPending";
    private Requestor requestor;

    public CardClientProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public String getName() {
        String name = requestor.sendRequestAndAwaitReply(CARD_OBJECTID,
                OperationNames.CARD_GET_NAME, String.class);
        return name;
    }

    @Override
    public int getManaCost() {
        int cardManaCost = requestor.sendRequestAndAwaitReply(CARD_OBJECTID,
                OperationNames.CARD_GET_MANA_COST,Integer.class);
        return cardManaCost;
    }

    @Override
    public int getAttack() {
        int cardAttackPower = requestor.sendRequestAndAwaitReply(CARD_OBJECTID,
                OperationNames.CARD_GET_ATTACK, Integer.class);
        return cardAttackPower;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public String getEffectDescription() {
        return null;
    }
}
