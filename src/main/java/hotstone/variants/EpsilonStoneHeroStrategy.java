package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.standard.GameConstants;

public class EpsilonStoneHeroStrategy implements HeroStrategy {
    @Override
    public String getType(Player who) {
        boolean playerIsFindus = Player.FINDUS == who;
        if(playerIsFindus) {
            return GameConstants.FRENCH_CHEF_HERO_TYPE;
        }
        return GameConstants.ITALIAN_CHEF_HERO_TYPE;
    }

    @Override
    public void useHeroPower(Game game, Player who) {

    }
}
