package hotstone.variants.SemiStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.HeroStrategy;
import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;
import hotstone.framework.strategies.IndexDecisionStrategies.RandomIndexStrategy;
import hotstone.standard.GameConstants;
import hotstone.variants.EpsilonStone.EpsilonStoneHeroStrategy;
import hotstone.variants.GammaStone.GammaStoneHeroStrategy;

import java.util.ArrayList;

public class SemiStoneHeroStrategy implements HeroStrategy {

    private GammaStoneHeroStrategy gammaStoneHeroStrategy;
    private EpsilonStoneHeroStrategy epsilonStoneHeroStrategy;

    private IndexStrategy indexStrategy;

    public SemiStoneHeroStrategy(IndexStrategy indexStrategy) {
        this.indexStrategy = indexStrategy;
        epsilonStoneHeroStrategy = new EpsilonStoneHeroStrategy(indexStrategy);
        gammaStoneHeroStrategy = new GammaStoneHeroStrategy();
    }

    @Override
    public String getType(Player who) {
        ArrayList<String> heroTypes = new ArrayList<>();
        heroTypes.add(GameConstants.THAI_CHEF_HERO_TYPE);
        heroTypes.add(GameConstants.DANISH_CHEF_HERO_TYPE);
        heroTypes.add(GameConstants.FRENCH_CHEF_HERO_TYPE);
        heroTypes.add(GameConstants.ITALIAN_CHEF_HERO_TYPE);

        return heroTypes.get(indexStrategy.calculateIndex(heroTypes.size()));
    }

    @Override
    public void useHeroPower(Game game, Player who) {
        gammaStoneHeroStrategy.useHeroPower(game, who);
        epsilonStoneHeroStrategy.useHeroPower(game,who);
    }
}
