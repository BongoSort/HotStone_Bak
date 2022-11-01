package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.utility.GameObserverSpy;
import hotstone.variants.AlphaStone.AlphaStoneConcreteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;

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
        assertThat(gameObserverSpy.getLastMethodCalled(), is("onCardPlay"));
    }

    @Test
    public void lastMethodCalledWasOnTurnChangeTo() {
        game.endTurn();
        assertThat(gameObserverSpy.getLastMethodCalled(), is("onTurnChangeTo"));
    }

}
