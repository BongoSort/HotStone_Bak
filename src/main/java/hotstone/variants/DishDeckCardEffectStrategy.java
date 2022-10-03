package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Utility;
import hotstone.framework.strategies.CardEffectStrategy;
import hotstone.framework.strategies.IndexDecisionStrategies.IndexStrategy;
import hotstone.framework.strategies.IndexDecisionStrategies.RandomIndexStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneCard;
import hotstone.standard.StandardHotStoneGame;
import hotstone.standard.StandardHotStoneHero;

public class DishDeckCardEffectStrategy implements CardEffectStrategy {

    private IndexStrategy indexStrategy;

    public DishDeckCardEffectStrategy(IndexStrategy indexStrategy) {
        this.indexStrategy = indexStrategy;
    }

    @Override
    public void useCardEffect(Game game, Player who, Card card) {
        Player opponent = Utility.computeOpponent(who);

        int randomIndex = indexStrategy.calculateRandomNumber(game.getFieldSize(who));
        switch (card.getName()) {
            case GameConstants.BROWN_RICE_CARD -> {
                ((StandardHotStoneHero) game.getHero(opponent)).reduceHealth(1);
            }
            case GameConstants.TOMATO_SALAD_CARD -> {
                if(game.getFieldSize(who) > 0) {
                    ((StandardHotStoneCard) game.getCardInField(who,randomIndex)).increaseAttack(1);
                }
            }
            case GameConstants.POKE_BOWL_CARD -> {
                ((StandardHotStoneHero) game.getHero(who)).increaseHealth(2);
            }

            case GameConstants.NOODLE_SOUP_CARD -> {
                ((StandardHotStoneGame) game).drawCard(who);
            }
            case GameConstants.CHICKEN_CURRY_CARD -> {
                if(game.getFieldSize(opponent) > 0) {
                    StandardHotStoneCard opponentCard = ((StandardHotStoneCard) game.getCardInField(opponent,randomIndex));
                    opponentCard.setHealth(0);
                    ((StandardHotStoneGame) game).removeCardFromFieldIfHealthIsZeroOrBelow(opponentCard);
                }
            }
            case GameConstants.BEEF_BURGER_CARD -> {
                if(game.getFieldSize(opponent) > 0) {
                    ((StandardHotStoneCard) game.getCardInField(opponent,randomIndex)).increaseAttack(2);
                }
            }
        }
    }
}
