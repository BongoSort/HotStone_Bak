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

    @Test
    public void frenchChefPowerDecreasesRandomEnemyMinionHealthBy2() {

    }

    @Test
    public void italianChefPowerIncreasesRandomFriendlyMinionAttackBy2() {
        TestHelper.fieldUnoDosForFindusAndUnoDosForPeddersen(game);
        game.endTurn();
        game.usePower(Player.PEDDERSEN);
        Card standardDosCard = new StandardHotStoneCard(GameConstants.DOS_CARD, Player.PEDDERSEN);
        boolean minionDosAttackWasIncreased = game.getCardInField(Player.PEDDERSEN,0).
                getAttack() != standardDosCard.getAttack();

        Card standardUnoCard = new StandardHotStoneCard(GameConstants.UNO_CARD, Player.PEDDERSEN);
        boolean minionUnoAttackWasIncreased = game.getCardInField(Player.PEDDERSEN,1).
                getAttack() != standardUnoCard.getAttack();

        assertThat(minionDosAttackWasIncreased || minionUnoAttackWasIncreased, is(true));
    }

    @Test
    public void frenchChefPowerDecreasesEnemyMinionHealthByTwo() {
        TestHelper.fieldTresForPeddersen(game);


    }





}
