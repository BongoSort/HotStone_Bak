package hotstone.broker;
import hotstone.framework.*;

import hotstone.standard.StandardHotStoneCard;
import hotstone.standard.StandardHotStoneGame;
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
        Invoker invoker = new HotStoneGameInvoker(servant);

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
    /*
    Card getCardInField(Player who, int indexInField);

    Iterable<? extends Card> getField(Player who);

    Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard);
    */
}
