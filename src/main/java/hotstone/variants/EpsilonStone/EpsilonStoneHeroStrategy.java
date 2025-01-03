package hotstone.variants.EpsilonStone;

import hotstone.framework.*;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;
import hotstone.standard.GameConstants;

import java.util.ArrayList;

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

        String playersHeroType = game.getHero(who).getType();
        switch(playersHeroType) {
            case GameConstants.FRENCH_CHEF_HERO_TYPE -> {
                Player opponent = Utility.computeOpponent(who);

                if(game.getFieldSize(opponent) <= 0) { return; }

                int randomNumber = indexStrategy.calculateIndex(game.getFieldSize(opponent));


                MutableCard card = (MutableCard) game.getCardInField(opponent,randomNumber);
                card.reduceHealth(2);
                ((MutableGame) game).removeCardFromFieldIfHealthIsZeroOrBelow(card);
            }
            case GameConstants.ITALIAN_CHEF_HERO_TYPE -> {
                if(game.getFieldSize(who) <= 0) { return; }

                int randomNumber = indexStrategy.calculateIndex(game.getFieldSize(who));
                ((MutableCard) game.getCardInField(who,randomNumber)).increaseAttack(2);
            }
        }
    }
}
