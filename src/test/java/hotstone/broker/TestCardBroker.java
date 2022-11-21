package hotstone.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.CardClientProxy;
import hotstone.broker.client.HeroClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.Card;
import hotstone.framework.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCardBroker {
    private Card card;

    @BeforeEach
    public void setup() {
        Game servant = new StubGameForBroker();
        Invoker invoker = new HotStoneGameInvoker(servant);
        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        card = new CardClientProxy(requestor);
    }

    @Test
    public void cardNameShouldBeIHaveAName() {
        assertThat(card.getName(), is("I have a name"));
    }

    @Test
    public void manaCostShouldBe6969() {
        assertThat(card.getManaCost(), is(6969));
    }

    @Test
    public void attackShouldBe80085() {
        assertThat(card.getAttack(), is(80085));
    }

    @Test
    public void healthShouldBe10101() {
        assertThat(card.getHealth(), is(10101));
    }

    @Test
    public void isActiveShouldBeTrue() {
        assertThat(card.isActive(), is(true));
    }

    @Test
    public void ownerShouldBeThePeddersen() {
        assertThat(card.getOwner(), is(Player.PEDDERSEN));
    }

    @Test
    public void getEffectDescriptionShouldBeIAmTheGOAT() {
        assertThat(card.getEffectDescription(), is("I am the GOAT"));
    }
}
