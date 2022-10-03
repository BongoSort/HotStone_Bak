package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.utility.FixedIndexStrategy;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.AlphaStoneDeckStrategy;
import hotstone.variants.AlphaStone.AlphaStoneManaProductionStrategy;
import hotstone.variants.EpsilonStone.EpisilonStoneWinnerStrategy;
import hotstone.variants.EpsilonStone.EpsilonStoneHeroStrategy;
import hotstone.variants.NoCardEffectStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonStone {
    private Game game;
    private FixedIndexStrategy fixedIndexStrategy;

    /** Fixture for EpsilonStone testing. */
    @BeforeEach
    public void setUp() {
        fixedIndexStrategy = new FixedIndexStrategy();
        game = new StandardHotStoneGame(new AlphaStoneManaProductionStrategy(), new EpisilonStoneWinnerStrategy(),
                 new EpsilonStoneHeroStrategy(fixedIndexStrategy), new AlphaStoneDeckStrategy(), new NoCardEffectStrategy());
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
    public void italianChefPowerIncreasesFriendlyMinionAttackBy2() {
        fixedIndexStrategy.setValue(0);
        TestHelper.fieldUnoDosForFindusAndUnoDosForPeddersen(game);
        game.endTurn();
        game.usePower(Player.PEDDERSEN);
        Card dosCard = game.getCardInField(Player.PEDDERSEN,0);
        assertThat(dosCard.getAttack() , is(2 + 2));
    }

    @Test
    public void frenchChefPowerDecreasesEnemyMinionHealthByTwo() {
        fixedIndexStrategy.setValue(0);
        TestHelper.fieldTresForPeddersen(game);
        game.usePower(Player.FINDUS);
        assertThat(game.getCardInField(Player.PEDDERSEN,0).getHealth(), is(1));
    }


    @Test
    public void whenFrenchChefPowerDecreasesEnemyMinionHealthToZeroMinionIsRemovedFromField() {
        fixedIndexStrategy.setValue(1);
        TestHelper.fieldUnoDosForFindusAndUnoDosForPeddersen(game);
        game.usePower(Player.FINDUS);
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(1));
    }

    @Test
    public void whenFrenchChefPowerDecreasesEnemyMinionHealthToZeroOrLessMinionIsRemovedFromField() {
        fixedIndexStrategy.setValue(1);
        TestHelper.fieldUnoDosForFindusAndUnoDosForPeddersen(game);
        game.usePower(Player.FINDUS);
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(1));
    }

    @Test
    public void whenFindusHasAttackedPeddersensMinionsForGreaterThan7AttackFindusWins() {
        TestHelper.fieldUnoDosTresCuatroForFindusAndPeddersen(game);
        Card unoCardFindus = game.getCardInField(Player.FINDUS,3);
        Card dosCardFindus = game.getCardInField(Player.FINDUS,2);
        Card tresCardFindus = game.getCardInField(Player.FINDUS,1);
        Card cuatroCardFindus = game.getCardInField(Player.FINDUS,0);

        Card unoCardPeddersen = game.getCardInField(Player.FINDUS,3);
        Card dosCardPeddersen = game.getCardInField(Player.PEDDERSEN,2);
        Card tresCardPeddersen = game.getCardInField(Player.PEDDERSEN,1);
        Card cuatroCardPeddersen = game.getCardInField(Player.PEDDERSEN,0);

        game.attackCard(Player.FINDUS,unoCardFindus,unoCardPeddersen);
        game.attackCard(Player.FINDUS,dosCardFindus,dosCardPeddersen);
        game.attackCard(Player.FINDUS,tresCardFindus,tresCardPeddersen);
        game.attackCard(Player.FINDUS,cuatroCardFindus,cuatroCardPeddersen);

        assertThat(game.getWinner(), is(Player.FINDUS));
    }

    @Test
    public void italianPowerEffectDescriptionIsCorrectDescription() {
        //Description Should be:
        //"Pick a random minion of his own (on the field), and increase its attack by 2. In case no minions are on the field, the power has no effect"
        //Peddersen owns the ThaiHero
        assertThat(game.getHero(Player.PEDDERSEN).getEffectDescription(), is(GameConstants.ITALIAN_CHEF_EFFECT_DESCRIPTION));
    }

    @Test
    public void frenchPowerEffectDescriptionIsCorrectDescription() {
        //Description Should be:
        //"Pick a random opponent minion (on the field) and decrease its health by 2. In case no minions are on the field, the power has no effect"
        //Findus owns the ThaiHero
        assertThat(game.getHero(Player.FINDUS).getEffectDescription(), is(GameConstants.FRENCH_CHEF_EFFECT_DESCRIPTION));
    }
}
