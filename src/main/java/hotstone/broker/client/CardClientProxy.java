package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;

public class CardClientProxy implements Card, ClientProxy {

    private String CARD_OBJECTID;
    private Requestor requestor;

    public CardClientProxy(String objectId, Requestor requestor) {
        this.requestor = requestor;
        this.CARD_OBJECTID = objectId;
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
        int cardHealth = requestor.sendRequestAndAwaitReply(CARD_OBJECTID,
                OperationNames.CARD_GET_HEALTH, Integer.class);
        return cardHealth;
    }

    @Override
    public boolean isActive() {
        boolean active = requestor.sendRequestAndAwaitReply(CARD_OBJECTID,
                OperationNames.CARD_IS_ACTIVE, Boolean.class);
        return active;
    }

    @Override
    public Player getOwner() {
        Player player = requestor.sendRequestAndAwaitReply(CARD_OBJECTID,
                OperationNames.CARD_GET_OWNER, Player.class);
        return player;
    }

    @Override
    public String getEffectDescription() {
        String effectDescription = requestor.sendRequestAndAwaitReply(CARD_OBJECTID,
                OperationNames.CARD_GET_EFFECT_DESCRIPTION, String.class);
        return effectDescription;
    }

    @Override
    public String getId() {
        return CARD_OBJECTID;
    }
}
