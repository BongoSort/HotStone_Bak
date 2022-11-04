package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.utility.FixedIndexStrategy;
import hotstone.utility.TestHelper;
import hotstone.utility.TestSemiStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSemiStone {
    private FixedIndexStrategy indexStrategy;

    @BeforeEach
    public void setUp() {
        indexStrategy = new FixedIndexStrategy();

    }

    @Test
    public void findusShouldBeAbleToGetThaiHero() {
        indexStrategy.setValue(0);
        Game game = new StandardHotStoneGame(new TestSemiStoneFactory(indexStrategy));
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }

    @Test
    public void findusShouldBeAbleToGetDanishHero() {
        indexStrategy.setValue(1);
        Game game = new StandardHotStoneGame(new TestSemiStoneFactory(indexStrategy));
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }

    @Test
    public void findusShouldBeAbleToGetFrenchHero() {
        indexStrategy.setValue(2);
        Game game = new StandardHotStoneGame(new TestSemiStoneFactory(indexStrategy));
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

    @Test
    public void findusShouldBeAbleToGetItalianHero() {
        indexStrategy.setValue(3);
        Game game = new StandardHotStoneGame(new TestSemiStoneFactory(indexStrategy));
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.ITALIAN_CHEF_HERO_TYPE));
    }

    @Test
    public void findusCanUseThaiChefPower(){
        indexStrategy.setValue(0); //ThaiChef is on index 0 in the HeroTypes array in the HeroStrategy used for semistone
        Game game = new StandardHotStoneGame(new TestSemiStoneFactory(indexStrategy));

        TestHelper.advanceGameNRounds(game,2);

        game.usePower(Player.FINDUS);

        assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(GameConstants.HERO_MAX_HEALTH - 2));
    }

    @Test
    public void findusCanUseDanishChefPower(){
        indexStrategy.setValue(1); //danish Hero is on index 1 in the HeroStrategy used
        Game game = new StandardHotStoneGame(new TestSemiStoneFactory(indexStrategy));

        TestHelper.advanceGameNRounds(game,2);
        game.usePower(Player.FINDUS);

        assertThat(game.getCardInField(Player.FINDUS,0).getName(), is(GameConstants.SOVS_CARD));
    }

    @Test
    public void pedddersenCanUseFrenchChefPower(){
        indexStrategy.setValue(2); //here we select the french hero
        Game game = new StandardHotStoneGame(new TestSemiStoneFactory(indexStrategy));

        TestHelper.advanceGameNRounds(game,2);
        Card card = game.getCardInHand(Player.FINDUS,2);
        game.playCard(Player.FINDUS, card);
        game.endTurn();

        indexStrategy.setValue(0); //Here we set the index that the french hero power should target.
        game.usePower(Player.PEDDERSEN);

        assertThat(game.getCardInField(Player.FINDUS,0).getHealth(), is(1));
    }

    @Test
    public void findusCanUseItalianChefPower() {
        indexStrategy.setValue(3); //Italian hero is on index 3 in the herotypes array from HeroStrategy
        Game game = new StandardHotStoneGame(new TestSemiStoneFactory(indexStrategy));

        Card card = game.getCardInHand(Player.FINDUS,2);
        game.playCard(Player.FINDUS,card);
        TestHelper.advanceGameNRounds(game,1);

        indexStrategy.setValue(0); //sets the index that the usepower method should use.
        game.usePower(Player.FINDUS);

        assertThat(game.getCardInField(Player.FINDUS,0).getAttack(), is(3));
    }
}
