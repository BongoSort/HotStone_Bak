package hotstone.broker.service;

import hotstone.framework.Card;

public interface CardNameService {
    Card getCard(String objectId);

    Card putCard(String objectId, Card card);
}
