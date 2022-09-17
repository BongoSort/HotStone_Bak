package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.variants.GammaStoneHeroStrategy;
import hotstone.variants.AlphaStoneManaProduction;
import hotstone.variants.AlphaStoneWinnerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGammaStone {
    private Game game;

    /** Fixture for AlphaStone testing. */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaStoneManaProduction(), new AlphaStoneWinnerStrategy(), new GammaStoneHeroStrategy());
    }

    @Test
    public void findusHeroTypeShouldBeThaiChef() {
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }

    @Test
    public void peddersenHeroTypeShouldBeDanishChef() {
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }

    @Test
    public void thaiChefsHeroPowerShouldDecreaseOpponentsHerosHealthBy2() {
        game.usePower(Player.FINDUS);
        assertThat(game.getHero(Player.PEDDERSEN).getHealth(),is(21 - 2));
    }

    @Test
    public void danishChefHeroPowerShouldFieldMinionSovsWithAttackPower1AndHealth1() {
        game.endTurn();
        game.usePower(Player.PEDDERSEN);
        Card minionFielded = game.getCardInField(Player.PEDDERSEN,0);
        assertThat(minionFielded.getName(), is(GameConstants.SOVS_CARD));
        assertThat(minionFielded.getHealth(), is(1));
        assertThat(minionFielded.getAttack(), is(1));
    }

}