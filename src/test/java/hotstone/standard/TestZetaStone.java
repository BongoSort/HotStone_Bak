package hotstone.standard;


import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.AlphaStoneHeroStrategy;
import hotstone.variants.AlphaStone.AlphaStoneManaProductionStrategy;
import hotstone.variants.EtaStone.NoCardEffectStrategy;
import hotstone.variants.ZetaStone.ZetaStoneAlternatingWinnerStrategy;
import hotstone.variants.ZetaStone.ZetaStoneDeckStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestZetaStone {
    private DeckStrategy zetaStoneDeckStrategy;
    private Game game;

    /** Fixture for AlphaStone testing. */
    @BeforeEach
    public void setUp() {
        zetaStoneDeckStrategy = new ZetaStoneDeckStrategy();
        game = new StandardHotStoneGame(new AlphaStoneManaProductionStrategy(),
                new ZetaStoneAlternatingWinnerStrategy(), new AlphaStoneHeroStrategy(), new ZetaStoneDeckStrategy(), new NoCardEffectStrategy());
    }

    @Test
    public void findusDeckShouldConsistOf7Cards() {
        ArrayList<Card> deck = zetaStoneDeckStrategy.deckInitialization(Player.FINDUS);
        assertThat(deck.size(), is(7));
    }

    @Test
    public void peddersensDeckShouldConsistOf7Cards() {
        ArrayList<Card> deck = zetaStoneDeckStrategy.deckInitialization(Player.PEDDERSEN);
        assertThat(deck.size(), is(7));
    }

    @Test
    public void allCardsInFindusDeckShouldBeCinco() {
        ArrayList<Card> deck = zetaStoneDeckStrategy.deckInitialization(Player.FINDUS);
        deck.forEach(card -> assertThat(card.getName(), is(GameConstants.CINCO_CARD)));
    }


    @Test
    public void allCardsInPeddersensDeckShouldBeCinco() {
        ArrayList<Card> deck = zetaStoneDeckStrategy.deckInitialization(Player.PEDDERSEN);
        deck.forEach(card -> assertThat(card.getName(), is(GameConstants.CINCO_CARD)));
    }

    @Test
    public void inRound5WinnerStrategyShouldBeByHealthDepletion(){
        for(int i = 0 ; i < 5 ; i++) {
           Card card = game.getCardInHand(Player.FINDUS,0);
           game.playCard(Player.FINDUS,card);
           TestHelper.advanceGameNRounds(game,1);
        }

        for(int i = 0 ; i < 5 ; i++) {
            Card card = game.getCardInField(Player.FINDUS, i);
            game.attackHero(Player.FINDUS, card);
        }
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

    @Test
    public void inRound7WinnerStrategyShouldBeMinionToMinionOutputAttack() {
        TestHelper.advanceGameNRounds(game, 4);
        for(int i = 0 ; i < 2; i++) {
            Card card = game.getCardInHand(Player.FINDUS,0);
            game.playCard(Player.FINDUS,card);

            game.endTurn();

            Card card1 = game.getCardInHand(Player.PEDDERSEN,0);
            game.playCard(Player.PEDDERSEN,card);

            game.endTurn();

            game.attackCard(Player.FINDUS,card,card1);
        }

        assertThat(game.getWinner(),is(Player.FINDUS));
    }
}

