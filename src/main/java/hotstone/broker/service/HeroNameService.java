package hotstone.broker.service;

import hotstone.framework.Hero;

public interface HeroNameService {
    Hero getHero(String objectId);

    Hero putHero(String objectId, Hero hero);
}
