package hotstone.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.CardClientProxy;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.client.HeroClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.broker.service.CardNameServiceImpl;
import hotstone.broker.service.HeroNameServiceImpl;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.AlphaStoneConcreteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCardBrokerII {
    private Card card;

    @BeforeEach
    public void setup() {
        Game servant = new StandardHotStoneGame(new AlphaStoneConcreteFactory());
        Invoker invoker = new HotStoneGameInvoker(servant, new CardNameServiceImpl(), new HeroNameServiceImpl());
        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        Game proxy = new GameClientProxy(requestor);
        card = proxy.getCardInHand(Player.FINDUS,0);
    }

    @Test
    public void cardNameShouldBeIHaveAName() {
        assertThat(card.getName(), is("Tres"));
    }

    @Test
    public void manaCostShouldBe6969() {
        assertThat(card.getManaCost(), is(3));
    }

    @Test
    public void attackShouldBe80085() {
        assertThat(card.getAttack(), is(3));
    }

    @Test
    public void healthShouldBe10101() {
        assertThat(card.getHealth(), is(3));
    }

    @Test
    public void isActiveShouldBeTrue() {
        assertThat(card.isActive(), is(false));
    }

    @Test
    public void ownerShouldBeThePeddersen() {
        assertThat(card.getOwner(), is(Player.FINDUS));
    }
}
