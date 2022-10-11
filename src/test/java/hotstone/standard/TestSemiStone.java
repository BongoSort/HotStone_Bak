package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.utility.FixedIndexStrategy;
import hotstone.utility.TestSemiStoneConcreteFactory;
import hotstone.utility.TestSemiStoneHeroes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSemiStone {
    private TestSemiStoneConcreteFactory testSemiStoneConcreteFactory;
    private FixedIndexStrategy indexStrategy;
    private Game game;

    @BeforeEach
    public void setUp() {
        indexStrategy = new FixedIndexStrategy();

    }

    @Test
    public void findusShouldBeAbleToGetDanishHero() {
        indexStrategy.setValue(1);
        game = new StandardHotStoneGame(new TestSemiStoneConcreteFactory(indexStrategy));
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }

    @Test
    public void findusShouldBeAbleToGetThaiHero() {
        indexStrategy.setValue(0);
        game = new StandardHotStoneGame(new TestSemiStoneConcreteFactory(indexStrategy));
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }

    @Test
    public void findusShouldBeAbleToGetFrenchHero() {
        indexStrategy.setValue(2);
        game = new StandardHotStoneGame(new TestSemiStoneConcreteFactory(indexStrategy));
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

    @Test
    public void findusShouldBeAbleToGetItalianHero() {
        indexStrategy.setValue(3);
        game = new StandardHotStoneGame(new TestSemiStoneConcreteFactory(indexStrategy));
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.ITALIAN_CHEF_HERO_TYPE));
    }
}
