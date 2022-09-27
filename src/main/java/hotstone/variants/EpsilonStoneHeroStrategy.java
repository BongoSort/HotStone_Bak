package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Utility;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;
import hotstone.standard.StandardHotStoneHero;

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
        String playersHeroType = game.getHero(who).getType();
        switch(playersHeroType) {
            case GameConstants.FRENCH_CHEF_HERO_TYPE -> {}
            case GameConstants.ITALIAN_CHEF_HERO_TYPE -> {
                int randomNumber = (int) (Math.random() * game.getFieldSize(who));
                ((StandardHotStoneCard) game.getCardInField(who,randomNumber)).increaseAttack(2);
            }
        }

    }
}
