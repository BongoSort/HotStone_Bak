package hotstone.broker.service;

import hotstone.framework.Hero;

import java.util.HashMap;
import java.util.Map;

public class HeroNameServiceImpl implements HeroNameService{
    private Map<String,Hero> heroMap = new HashMap<>();
    @Override
    public Hero getHero(String objectId) {
        return heroMap.get(objectId);
    }

    @Override
    public Hero putHero(String objectId, Hero hero) {
        return heroMap.put(objectId,hero);
    }
}
