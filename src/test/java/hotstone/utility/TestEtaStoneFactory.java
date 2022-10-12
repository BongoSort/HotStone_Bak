package hotstone.utility;

import hotstone.framework.strategies.*;
import hotstone.variants.EtaStone.EtaStoneCardEffectStrategy;
import hotstone.variants.EtaStone.EtaStoneConcreteFactory;

public class TestEtaStoneFactory extends EtaStoneConcreteFactory {

    private FixedIndexStrategy fixedIndexStrategy = new FixedIndexStrategy();

    @Override
    public CardEffectStrategy createCardEffectStrategy() {
        return new EtaStoneCardEffectStrategy(fixedIndexStrategy);
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new TestEtaStoneDeck();
    }

    public FixedIndexStrategy getFixedIndexStrategy() {
        return fixedIndexStrategy;
    }
}