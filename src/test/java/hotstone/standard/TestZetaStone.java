package hotstone.standard;


import hotstone.framework.*;
import hotstone.framework.strategies.DeckStrategy;
import hotstone.framework.strategies.WinnerStrategy;
import hotstone.variants.ZetaStone.ZetaStoneWinnerStrategy;
import hotstone.variants.ZetaStone.ZetaStoneDeckStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestZetaStone {
    private DeckStrategy zetaStoneDeckStrategy;
    private WinnerStrategy winnerStrategy;
    private TestDoubleGame gameDouble;
    private StandardHotStoneHero findusHero = new StandardHotStoneHero(Player.FINDUS, 3, GameConstants.BABY_HERO_TYPE);
    private StandardHotStoneHero peddersenHero = new StandardHotStoneHero(Player.PEDDERSEN,3,GameConstants.BABY_HERO_TYPE);


    @BeforeEach
    public void setUp() {
        zetaStoneDeckStrategy = new ZetaStoneDeckStrategy();
        gameDouble = new TestDoubleGame();
        winnerStrategy = new ZetaStoneWinnerStrategy();
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
    public void inRound6WinnerStrategyShouldBeByHealthDepletion() {
        gameDouble.turnNumber = 11;
        peddersenHero.reduceHealth(peddersenHero.getHealth());
        assertThat(gameDouble.getWinner(),is(Player.FINDUS));
    }

    @Test
    public void inRound6WinnerStrategyShouldBeMinionToMinionOutputAttack() {
        gameDouble.turnNumber = 12;
        winnerStrategy.attackingMinionsAttackValue(Player.FINDUS, gameDouble,8);
        assertThat(gameDouble.getWinner(), is(Player.FINDUS));
    }

    private class TestDoubleGame implements Game {
        public int turnNumber;

        @Override
        public Player getPlayerInTurn() {
            return null;
        }

        @Override
        public Hero getHero(Player who) {
            return (who == Player.FINDUS) ? findusHero : peddersenHero;
        }

        @Override
        public Player getWinner() {
            return winnerStrategy.calculateWinner(this);
        }

        @Override
        public int getTurnNumber() {
            return turnNumber;
        }

        @Override
        public int getDeckSize(Player who) {
            return 0;
        }

        @Override
        public Card getCardInHand(Player who, int indexInHand) {
            return null;
        }

        @Override
        public Iterable<? extends Card> getHand(Player who) {
            return null;
        }

        @Override
        public int getHandSize(Player who) {
            return 0;
        }

        @Override
        public Card getCardInField(Player who, int indexInField) {
            return null;
        }

        @Override
        public Iterable<? extends Card> getField(Player who) {
            return null;
        }

        @Override
        public int getFieldSize(Player who) {
            return 0;
        }

        @Override
        public void endTurn() {

        }

        @Override
        public Status playCard(Player who, Card card) {
            return null;
        }

        @Override
        public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
            return null;
        }

        @Override
        public Status attackHero(Player playerAttacking, Card attackingCard) {
            return null;
        }

        @Override
        public Status usePower(Player who) {
            return null;
        }
    }
}


