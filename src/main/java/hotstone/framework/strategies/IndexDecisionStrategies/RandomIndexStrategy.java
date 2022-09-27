package hotstone.framework.strategies.IndexDecisionStrategies;

import java.util.Random;

public class RandomIndexStrategy implements IndexStrategy{
    @Override
    public int calculateRandomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }
}
