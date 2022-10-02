package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.AlphaStoneHeroStrategy;
import hotstone.variants.AlphaStone.AlphaStoneManaProductionStrategy;
import hotstone.variants.AlphaStone.AlphaStoneWinnerStrategy;
import hotstone.variants.DeltaStone.DeltaStoneDeckStrategy;
import hotstone.variants.DeltaStone.DeltaStoneManaProductionStrategy;
import hotstone.variants.ZetaStone.ZetaStoneAlternatingWinnerStrategy;
import hotstone.variants.ZetaStone.ZetaStoneDeckStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaStone {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new DeltaStoneManaProductionStrategy(),
               new AlphaStoneWinnerStrategy(), new AlphaStoneHeroStrategy(), new DeltaStoneDeckStrategy());
    }
}
