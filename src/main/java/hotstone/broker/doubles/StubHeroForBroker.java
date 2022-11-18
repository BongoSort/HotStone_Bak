package hotstone.broker.doubles;

import frds.broker.Servant;
import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StubHeroForBroker implements Hero, Servant {
    @Override
    public int getMana() {
        return 1234;
    }

    @Override
    public int getHealth() {
        return 2345;
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
