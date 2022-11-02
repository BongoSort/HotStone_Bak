package hotstone.standard;
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
    private Game gameTranscriber;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaStoneConcreteFactory());
        gameTranscriber = new StandardHotStoneGameTranscriber(game);
    }

    @Test
    public void testThatPlayCardPrints() {
        gameTranscriber.endTurn();
        assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
    }

}
