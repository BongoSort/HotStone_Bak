package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.utility.FixedIndexStrategy;
import hotstone.variants.SemiStone.SemiStoneConcreteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSemiStone {
    private Game game;

    private FixedIndexStrategy fixedIndexStrategy;
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new SemiStoneConcreteFactory());
    }

    @Test
    public void lort() {

        String a = game.getHero(Player.FINDUS).getType();
        boolean stuff = false;

        switch (a) {
            case GameConstants.DANISH_CHEF_HERO_TYPE -> stuff = true;
            case GameConstants.THAI_CHEF_HERO_TYPE -> stuff = true;
            case GameConstants.FRENCH_CHEF_HERO_TYPE -> stuff = true;
            case GameConstants.ITALIAN_CHEF_HERO_TYPE -> stuff = true;
        }

        assertThat(stuff, is(true));
    }

    /*
    @Test
    public void FindusCanBeThaiHero() {

        testSemiStoneFactory.getFixedIndexStrategy().setValue(0); //Thai Chef index in HeroTypes Array in strategy
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));

    }

    @Test
    public void FindusCanBeDanishHero() {
        fixedIndexStrategy.setValue(1);
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));

    }

    @Test
    public void FindusCanBeGetFrenchHero() {
        testSemiStoneFactory.getFixedIndexStrategy().setValue(2); //Thai Chef index in HeroTypes Array in strategy
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

    @Test
    public void FindusCanBeGetItalianHero() {
        testSemiStoneFactory.getFixedIndexStrategy().setValue(3); //Thai Chef index in HeroTypes Array in strategy
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.ITALIAN_CHEF_HERO_TYPE));

    }*/
}
