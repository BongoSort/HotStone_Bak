package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Hero;
import hotstone.framework.Player;

public class HeroClientProxy implements Hero, ClientProxy {
    private String HERO_ID;
    private Requestor requestor;

    public HeroClientProxy(String objectId, Requestor requestor) {
        this.requestor = requestor;
        this.HERO_ID = objectId;
    }

    @Override
    public int getMana() {
        int mana = requestor.sendRequestAndAwaitReply(HERO_ID,
                OperationNames.HERO_GET_MANA,Integer.class);
        return mana;
    }

    @Override
    public int getHealth() {
        int health = requestor.sendRequestAndAwaitReply(HERO_ID,
                OperationNames.HERO_GET_HEALTH,Integer.class);
        return health;
    }

    @Override
    public boolean isActive() {
        boolean active = requestor.sendRequestAndAwaitReply(HERO_ID,
                OperationNames.HERO_IS_ACTIVE, Boolean.class);
        return active;
    }

    @Override
    public String getType() {
        String type = requestor.sendRequestAndAwaitReply(HERO_ID,
                OperationNames.HERO_GET_TYPE, String.class);
        return type;
    }

    @Override
    public Player getOwner() {
        Player player = requestor.sendRequestAndAwaitReply(HERO_ID,
                OperationNames.HERO_GET_OWNER, Player.class);
        return player;
    }

    @Override
    public String getEffectDescription() {
        String effectDesc = requestor.sendRequestAndAwaitReply(HERO_ID,
                OperationNames.HERO_GET_EFFECT_DESCRIPTION, String.class);
        return effectDesc;
    }

    @Override
    public String getId() {
        return null;
    }
}
