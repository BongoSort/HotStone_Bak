package hotstone.utility;

import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.standard.GameConstants;

import java.util.ArrayList;

public class TestSemiStoneHeroes implements HeroStrategy {

    private int index;

    @Override
    public String getType(Player who) {
        ArrayList<String> heroTypes = new ArrayList<>();
        heroTypes.add(GameConstants.THAI_CHEF_HERO_TYPE);
        heroTypes.add(GameConstants.DANISH_CHEF_HERO_TYPE);
        heroTypes.add(GameConstants.FRENCH_CHEF_HERO_TYPE);
        heroTypes.add(GameConstants.ITALIAN_CHEF_HERO_TYPE);
        return heroTypes.get(index);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void useHeroPower(Game game, Player who) {
        //we don't need to test this yet
    }
}
