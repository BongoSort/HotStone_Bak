package hotstone.broker.doubles;

import frds.broker.Servant;
import hotstone.framework.Card;
import hotstone.framework.Player;

public class StubCardForBroker implements Card, Servant {
    @Override
    public String getName() {
        return "I have a name";
    }

    @Override
    public int getManaCost() {
        return 6969;
    }

    @Override
    public int getAttack() {
        return 80085;
    }

    @Override
    public int getHealth() {
        return 10101;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public Player getOwner() {
        return Player.PEDDERSEN;
    }

    @Override
    public String getEffectDescription() {
        return "I am the GOAT";
    }

    @Override
    public String getId() {
        return null;
    }
}
