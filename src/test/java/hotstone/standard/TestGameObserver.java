package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.utility.GameObserverSpy;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.AlphaStoneConcreteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestGameObserver {
    private StandardHotStoneGame game;
    private GameObserverSpy gameObserverSpy;

    /** Fixture for AlphaStone testing. */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaStoneConcreteFactory());
        gameObserverSpy = new GameObserverSpy();
        game.addObserver(gameObserverSpy);
    }


    @Test
    public void lastMethodCalledWasPlayCard(){
        Card card = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,card);
        assertThat(gameObserverSpy.getLastMethodCalled().contains("onCardPlay"), is(true));
    }

    @Test
    public void lastMethodCalledWasOnTurnChangeTo() {
        game.endTurn();
        assertThat(gameObserverSpy.getLastMethodCalled().contains("onTurnChangeTo"), is(true));
    }

    @Test
    public void lastMethodCalledWasOnAttackCard() {
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        Card findusCard = game.getCardInField(Player.FINDUS,0);
        Card peddersensCard = game.getCardInField(Player.PEDDERSEN,0);
        game.attackCard(Player.FINDUS,findusCard,peddersensCard);
        ArrayList<String> list = gameObserverSpy.getLastMethodCalled();

        assertThat(list.contains("onAttackCard"), is(true));
    }

    @Test
    public void lastMethodCalledWasOnAttackHero() {
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        game.attackHero(Player.FINDUS,findusCard);
        ArrayList<String> list = gameObserverSpy.getLastMethodCalled();

        assertThat(list.contains("onAttackHero"), is(true));
    }

    @Test
    public void lastMethodCalledWasOnUsePower() {
        game.usePower(Player.FINDUS);
        assertThat(gameObserverSpy.getLastMethodCalled().contains("onUsePower"), is(true));
    }

    @Test
    public void cardWasDrawnWhenNewTurn() {
        game.endTurn();
        assertThat(gameObserverSpy.getLastMethodCalled().contains("onCardDraw"), is(true));
    }

    @Test
    public void lastMethodCalledWasOnCardUpdate() {
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        Card findusCard = game.getCardInField(Player.FINDUS,0);
        Card peddersenCard = game.getCardInField(Player.FINDUS,0);
        game.attackCard(Player.FINDUS,findusCard,peddersenCard);
        ArrayList<String> list = gameObserverSpy.getLastMethodCalled();

        assertThat(list.contains("onCardUpdate"), is(true));

    }

    @Test
    public void onCardRemoveWasNotified() {
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        Card findusCard = game.getCardInField(Player.FINDUS,0);
        Card peddersenCard = game.getCardInField(Player.PEDDERSEN,0);
        game.attackCard(Player.FINDUS,findusCard,peddersenCard);

        ArrayList<String> list = gameObserverSpy.getLastMethodCalled();
        boolean wasnotified = list.contains("onCardRemove");

        assertThat(wasnotified,is(true));
    }

    @Test
    public void onHeroUpdateWasNotified() {
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        Card findusCard = game.getCardInField(Player.FINDUS,0);
        game.attackHero(Player.FINDUS, findusCard);

        ArrayList<String> list = gameObserverSpy.getLastMethodCalled();
        boolean wasnotified = list.contains("onHeroUpdate");

        assertThat(wasnotified,is(true));
    }
}
