package hotstone.broker;
import hotstone.broker.service.CardNameServiceImpl;
import hotstone.broker.service.HeroNameServiceImpl;
import hotstone.framework.*;

import hotstone.standard.StandardHotStoneCard;
import hotstone.standard.StandardHotStoneGame;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.AlphaStoneConcreteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;

import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;

import java.util.ArrayList;
import java.util.List;

public class TestGamePassByReferenceMethods {
    private Game game;

    @BeforeEach
    public void setup() {
        // === We start at the server side of the Broker pattern:
        // define the servant, next the invoker

        // Given a Servant game, here a test stub with canned output
        Game servant = new StandardHotStoneGame(new AlphaStoneConcreteFactory());
        // Which is injected into the dedicated Invoker which you must
        // develop
        Invoker invoker = new HotStoneGameInvoker(servant, new CardNameServiceImpl(), new HeroNameServiceImpl());

        // === Next define the client side of the pattern:
        // the client request handler, the requestor, and the client proxy

        // Instead of a network-based client- and server request handler
        // we make a fake object CRH that talks directly with the injected
        // invoker
        ClientRequestHandler crh =
                new LocalMethodClientRequestHandler(invoker);

        // Which is injected into the standard JSON requestor of the
        // FRDS.Broker library
        Requestor requestor = new StandardJSONRequestor(crh);

        // Which is finally injected into the GameClientProxy that
        // you must develop...
        game = new GameClientProxy(requestor);
    }

    @Test
    public void shouldGetCardInHand() {
        //Given a game client proxy for a AlphaStone game servant
        //When I ask for cord 0 in findus hand
        Card card = game.getCardInHand(Player.FINDUS,0);
        //Then i get a valid card
        assertThat(card, is(notNullValue()));
        //Then that card's name is Tres
        assertThat(card.getName(), is("Tres"));
        assertThat(card.getAttack(), is(3));
    }

    @Test
    public void shouldGetHand() {
        Iterable<? extends Card> cards = game.getHand(Player.FINDUS);
        cards.forEach(c -> assertThat(c, is(notNullValue())));
    }

    @Test
    public void shouldPlayCard() {
        Status status = game.playCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,0));
        assertThat(status, is(Status.OK));
    }

    @Test
    public void shouldGetCardInField() {
        game.playCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,0));

        Card c = game.getCardInField(Player.FINDUS,0);
        assertThat(c, is(notNullValue()));
        assertThat(c.getName(), is("Tres"));
        assertThat(c.getAttack(),is(3));
    }

    @Test
    public void shouldGetField() {
        game.playCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,1));
        game.playCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,1));

        Iterable<? extends Card> cards = game.getField(Player.FINDUS);
        cards.forEach(c -> assertThat(c, is(notNullValue())));
    }

    @Test
    public void shouldAttackCard() {
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        Card findusCard = game.getCardInField(Player.FINDUS,0);
        Card peddersenCard = game.getCardInField(Player.PEDDERSEN,0);
        Status status = game.attackCard(Player.FINDUS,findusCard,peddersenCard);
        assertThat(status, is(Status.OK));
    }

    @Test
    public void shouldGetHero() {
        Hero hero = game.getHero(Player.FINDUS);

        assertThat(hero.getOwner(), is(Player.FINDUS));
    }
    @Test
    public void shouldAttackHero() {
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        Card findusCard = game.getCardInField(Player.FINDUS,0);
        Status status = game.attackHero(Player.FINDUS,findusCard);
        assertThat(status, is(Status.OK));
    }
}
