package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.standard.StandardHotStoneHero;

public class HeroStrategyAlphaStone implements HeroStrategy {
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
