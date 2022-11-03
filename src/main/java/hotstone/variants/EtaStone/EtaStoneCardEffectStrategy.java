package hotstone.variants.EtaStone;

import hotstone.framework.*;
import hotstone.framework.strategies.CardEffectStrategy;
import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

public class EtaStoneCardEffectStrategy implements CardEffectStrategy {

    private IndexStrategy indexStrategy;

    public EtaStoneCardEffectStrategy(IndexStrategy indexStrategy) {
        this.indexStrategy = indexStrategy;
    }

    @Override
    public void useCardEffect(Game game, Player who, Card card) {
        Player opponent = Utility.computeOpponent(who);
        int randomIndex = indexStrategy.calculateIndex(game.getFieldSize(who));

        switch (card.getName()) {
            case GameConstants.BROWN_RICE_CARD -> {
                ((MutableHero) game.getHero(opponent)).reduceHealth(1);
            }
            case GameConstants.TOMATO_SALAD_CARD -> {
                if(game.getFieldSize(who) <= 0) {
                    return;
                }
                    ((MutableCard) game.getCardInField(who,randomIndex)).increaseAttack(1);
            }
            case GameConstants.POKE_BOWL_CARD -> {
                ((MutableHero) game.getHero(who)).increaseHealth(2);
            }

            case GameConstants.NOODLE_SOUP_CARD -> {
                ((MutableGame) game).drawCard(who);
            }
            case GameConstants.CHICKEN_CURRY_CARD -> {
                if(game.getFieldSize(opponent) <= 0) {
                    return;
                }
                MutableCard opponentCard = ((MutableCard) game.getCardInField(opponent,randomIndex));
                opponentCard.setHealth(0);
                ((MutableGame) game).removeCardFromFieldIfHealthIsZeroOrBelow(opponentCard);
            }
            case GameConstants.BEEF_BURGER_CARD -> {
                if(game.getFieldSize(opponent) <= 0) {
                    return;
                }
                ((MutableCard) game.getCardInField(opponent,randomIndex)).increaseAttack(2);
            }
        }
    }
}
