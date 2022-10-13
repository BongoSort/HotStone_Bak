package hotstone.framework.strategies.IndexDecisionStrategies;

import java.util.Random;

public class RandomIndexStrategy implements IndexStrategy{
    @Override
    public int calculateIndex(int bound) {
        if(bound == 0) {
            return 0;
        }
        Random random = new Random();
        return random.nextInt(bound);
    }
}
