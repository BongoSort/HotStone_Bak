package hotstone.utility;

import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;

public class FixedIndexStrategy implements IndexStrategy {
    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int calculateIndex(int bound) {
        return value;
    }
}
