package hotstone.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.client.HeroClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.doubles.StubHeroForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.broker.service.CardNameServiceImpl;
import hotstone.broker.service.HeroNameServiceImpl;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.AlphaStoneConcreteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestHeroBroker {
    private Hero hero;

    @BeforeEach
    public void setup() {
        Game servant = new StandardHotStoneGame(new AlphaStoneConcreteFactory());
        Invoker invoker = new HotStoneGameInvoker(servant, new CardNameServiceImpl(), new HeroNameServiceImpl());
        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        Game proxy = new GameClientProxy(requestor);
        hero = proxy.getHero(Player.FINDUS);
    }

    @Test
    public void heroManaShouldReturn1234() {
        assertThat(hero.getMana(), is(3));
    }

    @Test
    public void heroHealthShouldBe2345() {
        assertThat(hero.getHealth(), is(21));
    }

    @Test
    public void heroShouldReturnActive() {
        assertThat(hero.isActive(), is(true));
    }

    @Test
    public void heroTypeShouldBeStubHero123() {
        assertThat(hero.getType(), is("Baby"));
    }

    @Test
    public void HeroOwnerShouldBePeddersen() {
        assertThat(hero.getOwner(), is(Player.FINDUS));
    }

    @Test
    public void heroEffectDescriptionIsCrushingSkullsOfTheirEnemies() {
        assertThat(hero.getEffectDescription(), is("Cute"));
    }
}
