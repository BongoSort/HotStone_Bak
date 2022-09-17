package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.standard.GameConstants;

public class AlphaStoneHeroStrategy implements HeroStrategy {
    @Override
    public String getType(Player who) {
        return GameConstants.BABY_HERO_TYPE;
    }

    @Override
    public void useHeroPower(Game game, Player who) {
        if(game.getHero(who).getType().equals(GameConstants.BABY_HERO_TYPE)) {
            //Baby's hero power does nothing, and it seems weird to keep this if, but i guess it should be here?
        }
    }
}
