package hotstone.variants.GammaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Utility;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;
import hotstone.standard.StandardHotStoneHero;

public class GammaStoneHeroStrategy implements HeroStrategy {

    @Override
    public String getType(Player who) {
        boolean playerIsFindus = Player.FINDUS == who;
        if(playerIsFindus) {
            return GameConstants.THAI_CHEF_HERO_TYPE;
        }
        return GameConstants.DANISH_CHEF_HERO_TYPE;
    }

    @Override
    public void useHeroPower(Game game, Player who) {
        StandardHotStoneHero opponentHero = (StandardHotStoneHero) game.getHero(Utility.computeOpponent(who));
        String playersHeroType = game.getHero(who).getType();
        switch(playersHeroType) {
            case GameConstants.THAI_CHEF_HERO_TYPE -> opponentHero.reduceHealth(2);
            case GameConstants.DANISH_CHEF_HERO_TYPE -> game.playCard(who,new StandardHotStoneCard(GameConstants.SOVS_CARD,who,0,1,1));
        }
    }
}
