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
        return true;
    }

    @Override
    public String getType() {
        return "StubHero123";
    }

    @Override
    public Player getOwner() {
        return Player.PEDDERSEN;
    }

    @Override
    public String getEffectDescription() {
        return "Crushing skulls of their enemies, MUHAHAHA";
    }
}
