package hotstone.standard;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.utility.FixedIndexStrategy;
import hotstone.variants.AlphaStone.AlphaStoneConcreteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestTranscriber {
    private Game game;
    private Game origin;
    private Game gameTranscriber;

    @BeforeEach
    public void setUp() {
        origin = new StandardHotStoneGame(new AlphaStoneConcreteFactory());
        gameTranscriber = new StandardHotStoneGameTranscriber(origin);
        game = origin;
    }

    @Test
    public void testThatEndTurnPrints() {
        gameTranscriber.endTurn();
        assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
    }

    @Test
    public void testThatPlayCardPrints() {
        Card card = game.getCardInHand(Player.FINDUS,0);
        gameTranscriber.playCard(Player.FINDUS,card);
        assertThat(game.getFieldSize(Player.FINDUS), is(1));
    }

    @Test
    public void testThatTranscribingIsInDirectCorrelationWithGameObject() {
        game.endTurn();
        assertThat(gameTranscriber.getPlayerInTurn(), is(Player.PEDDERSEN));
    }

    @Test
    public void testThatTranscribingCanBeTurnedOnAndOff() {
        //at first the game isn't being transcribed
        game.endTurn();
        assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
        //then we start transcribing
        game = gameTranscriber;
        //now when we end turn it transcribes (prints to terminal) that Peddersen ended his turn
        game.endTurn();
        //then is should be findus turn
        assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
        //now when we take the decorator of it should stop transcribing
        // and nothing will be printed(transcribed) when we use endturn
        // and it should beomce peddersens turn
        game = origin;
        game.endTurn();
        assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
    }
}
