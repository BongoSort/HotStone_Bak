package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Utility;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;
import hotstone.standard.StandardHotStoneGame;

public class EpsilonStoneHeroStrategy implements HeroStrategy {
    private IndexStrategy indexStrategy;

    public EpsilonStoneHeroStrategy(IndexStrategy indexStrategy) {
        this.indexStrategy = indexStrategy;
    }

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
        int randomNumber = indexStrategy.calculateRandomNumber(game.getFieldSize(who));

        String playersHeroType = game.getHero(who).getType();
        switch(playersHeroType) {
            case GameConstants.FRENCH_CHEF_HERO_TYPE -> {
                Player opponent = Utility.computeOpponent(who);
                if(game.getFieldSize(opponent) == 0) {
                    return;
                }

                StandardHotStoneCard card = (StandardHotStoneCard) game.getCardInField(opponent,randomNumber);
                card.reduceHealth(2);

                ((StandardHotStoneGame) game).removeCardFromFieldIfHealthIsZeroOrBelow(card);
            }
            case GameConstants.ITALIAN_CHEF_HERO_TYPE -> {
                if(game.getFieldSize(who) == 0) {
                    return;
                }

                ((StandardHotStoneCard) game.getCardInField(who,randomNumber)).increaseAttack(2);
            }
        }
    }
}
