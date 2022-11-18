package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Hero;
import hotstone.framework.Player;

public class HeroClientProxy implements Hero, ClientProxy {
    private static String HERO_ID = "HeroIDPending";
    private Requestor requestor;

    public HeroClientProxy(Requestor requestor) {
        this.requestor = requestor;
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
        return false;
    }

    @Override
    public String getType() {
        return null;
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
