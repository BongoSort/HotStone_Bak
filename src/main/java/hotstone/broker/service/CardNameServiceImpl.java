package hotstone.broker.service;


import hotstone.framework.Card;

import java.util.HashMap;
import java.util.Map;

public class CardNameServiceImpl implements CardNameService {
    private Map<String, Card> cardMap;

    public CardNameServiceImpl() {
        this.cardMap = new HashMap<>();
    }

    @Override
    public Card getCard(String objectId) {
        return cardMap.get(objectId);
    }

    @Override
    public Card putCard(String objectId, Card card) {
        return cardMap.put(objectId,card);
    }
}
