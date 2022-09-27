package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.utility.TestHelper;
import hotstone.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonStone {
    private Game game;

    /** Fixture for AlphaStone testing. */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaStoneManaProductionStrategy(), new AlphaStoneWinnerStrategy(), new EpsilonStoneHeroStrategy(), new AlphaStoneDeckStrategy());
    }

    @Test
    public void findusHeroIsFrenchChef() {
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

    @Test
    public void peddersenHeroIsItalianChef() {
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.ITALIAN_CHEF_HERO_TYPE));
    }



}
