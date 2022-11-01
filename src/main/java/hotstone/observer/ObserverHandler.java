/*
 * Copyright (C) 2022. Henrik BÃ¦rbak Christensen, Aarhus University.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hotstone.observer;

import hotstone.framework.Card;
import hotstone.framework.Player;

import java.util.ArrayList;
import java.util.List;

/** A default implementation of the Subject role of a GameObserver. It
 * supports notification of all events coming from a Game instance.
 *
 * So instead of implementing your own 'addObserver' and notifyAllAboutX
 * methods, you simply use the 2nd principle 'Favor object composition'
 * and declare an instance of this role and let it handle all the
 * observer notifications.
 */

public class ObserverHandler {
    private List<GameObserver> observerList = new ArrayList<>();

    public void addObserver(GameObserver observer) {
        observerList.add(observer);
    }

    public void notifyPlayCard(Player who, Card card) {
        observerList
                .forEach( gameObserver -> gameObserver.onCardPlay(who, card) );
    }

    public void notifyTurnChangeTo(Player playerInTurn) {
        observerList
                .forEach( gameObserver -> gameObserver.onTurnChangeTo(playerInTurn) );

    }

    public void notifyAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        observerList
                .forEach( gameObserver -> gameObserver.onAttackCard(playerAttacking,
                        attackingCard, defendingCard) );
    }

    public void notifyCardUpdate(Card card) {
        observerList
                .forEach( gameObserver -> gameObserver.onCardUpdate(card));
    }

    public void notifyCardRemove(Player who, Card card) {
        observerList
                .forEach( gameObserver -> gameObserver.onCardRemove(who, card));
    }

    public void notifyAttackHero(Player playerAttacking, Card attackingCard) {
        observerList
                .forEach( gameObserver
                        -> gameObserver.onAttackHero(playerAttacking,attackingCard ));
    }

    public void notifyHeroUpdate(Player who) {
        observerList
                .forEach( gameObserver -> gameObserver.onHeroUpdate(who));
    }

    public void notifyUsePower(Player who) {
        observerList
                .forEach( gameObserver -> gameObserver.onUsePower(who));
    }

    public void notifyGameWon(Player playerWinning) {
        observerList
                .forEach( gameObserver -> gameObserver.onGameWon(playerWinning));
    }

    public void notifyCardDraw(Player who, Card drawnCard) {
        observerList
                .forEach( gameObserver -> gameObserver.onCardDraw(who, drawnCard));
    }
}