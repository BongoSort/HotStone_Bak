/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
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

package hotstone.standard;

/**
 * Skeleton class for AlphaStone test cases
 *
 *    This source code is from the book
 *      "Flexible, Reliable Software:
 *        Using Patterns and Agile Development"
 *      2nd Edition
 *    Author:
 *      Henrik Bærbak Christensen
 *      Department of Computer Science
 *      Aarhus University
 */

/**
 *
 *    Further developed by:
 *      Mai Ricaplaza
 *      Timmi Andersen
 *      Rune Schuster
 *      DAT3
 *      Aarhus University
 *
 */

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.utility.TestHelper;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import hotstone.framework.Game;

/** Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestAlphaStone {
  private Game game;

  /** Fixture for AlphaStone testing. */
  @BeforeEach
  public void setUp() {
    game = new StandardHotStoneGame();
  }

  @Test
  public void firstPlayerShouldBeFindus() {
    // Given a new HotStone game
    // Then check if the player in turn is Findus.
    assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
  }

  @Test
  public void findusEndsTurnPeddersenInTurn() {
    // Given a HotStone game
    // When second turn starts
    game.endTurn();
    // Then check if the player in turn is Peddersen.
    assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
  }

  @Test
  public void peddersenEndsHisTurnNowItsFindusTurn() {
    // Given a HotStone game
    // When the third turn starts
    game.endTurn(); game.endTurn();
    // Then check if the player in turn is Findus
    assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
  }

  // The HotStone specs are quite insisting on how
  // the cards, drawn from the deck, are organized
  // in the hand. So when drawing the top three cards
  // from the deck (uno, dos, tres) they have to
  // be organized in the hand as
  // index 0 = tres; index 1 = dos; index 2 = uno
  // That is, a newly drawn card is 'at the top'
  // of the hand - always entered at position 0
  // and pushing the rest of the cards 1 position
  // 'down'
  @Test
  public void findusShouldHaveUnoDosTresCardsInitially() {
    // Given a new game, Findus has 3 cards in hand
    int count = game.getHandSize(Player.FINDUS);
    assertThat(count, is(3));

    // And these are ordered Tres, Dos, Uno in slot 0,1,2

    // Given card 0 in the hand
    Card card0 = game.getCardInHand(Player.FINDUS, 0);
    // Then is it Tres
    assertThat(card0.getName(), is(GameConstants.TRES_CARD));

    // Given card 1 in the hand
    Card card1 = game.getCardInHand(Player.FINDUS, 1);
    // Then it is Dos
    assertThat(card1.getName(), is(GameConstants.DOS_CARD));

    // Given card 2 in the hand
    Card card2 = game.getCardInHand(Player.FINDUS, 2);
    // Then it is Uno
    assertThat(card2.getName(), is(GameConstants.UNO_CARD));
  }

  @Test
  public void peddersenShouldHaveUnoDosTresCardsInitially() {
    // Given a new game, Peddersen has 3 cards in hand
    int count = game.getHandSize(Player.PEDDERSEN);
    assertThat(count, is(3));

    // And these are ordered Tres, Dos, Uno in slot 0,1,2

    // Given card 0 in the hand
    Card card0 = game.getCardInHand(Player.PEDDERSEN, 0);
    // Then is it Tres
    assertThat(card0.getName(), is(GameConstants.TRES_CARD));

    // Given card 1 in the hand
    Card card1 = game.getCardInHand(Player.PEDDERSEN, 1);
    // Then it is Dos
    assertThat(card1.getName(), is(GameConstants.DOS_CARD));

    // Given card 2 in the hand
    Card card2 = game.getCardInHand(Player.PEDDERSEN, 2);
    // Then it is Uno
    assertThat(card2.getName(), is(GameConstants.UNO_CARD));
  }


  @Test
  public void cardUnoShouldHaveManaCostOneAttackOneHealthOne() {
    //given a game, and a Card Uno
    //Then card Uno has the attributes (1,1,1)
    Card card0 = new StandardHotStoneCard(GameConstants.UNO_CARD);
    assertThat(card0.getManaCost(), is(1));
    assertThat(card0.getAttack(),is(1));
    assertThat(card0.getHealth(), is(1));
  }
  @Test
  public void cardDosShouldHaveManaCostTwoAttackTwoHealthTwo() {
    //given a game, and a Card Dos
    //Then card Dos has the attributes (2,2,2) (Mana cost, Attack, Health)
    Card card0 = new StandardHotStoneCard(GameConstants.DOS_CARD);
    assertThat(card0.getManaCost(), is(2));
    assertThat(card0.getAttack(),is(2));
    assertThat(card0.getHealth(), is(2));
  }

  @Test
  public void cardTresShouldHaveManaCostThreeAttackThreeHealthThree() {
    //given a game, and a Card Tres
    //Then card Tres has the attributes (3,3,3)
    Card card0 = new StandardHotStoneCard(GameConstants.TRES_CARD);
    assertThat(card0.getManaCost(),is(3));
    assertThat(card0.getAttack(), is(3));
    assertThat(card0.getHealth(),is(3));
  }

  @Test
  public void cardCuatroShouldHaveManaCost2Attack3Health1() {
    //given a game, and a Card Cuatro
    //Then card Cuatro has the attributes (2,3,1)
    Card card0 = new StandardHotStoneCard(GameConstants.CUATRO_CARD);
    assertThat(card0.getManaCost(),is(2));
    assertThat(card0.getAttack(), is(3));
    assertThat(card0.getHealth(),is(1));
  }
  @Test
  public void cardCincoShouldHaveManaCost3Attack5Health1() {
    //given a game, and a Card Cinco
    //Then card Cinco has the attributes (3,5,1)
    Card card0 = new StandardHotStoneCard(GameConstants.CINCO_CARD);
    assertThat(card0.getManaCost(),is(3));
    assertThat(card0.getAttack(), is(5));
    assertThat(card0.getHealth(),is(1));
  }
  @Test
  public void cardSeisShouldHaveManaCost2Attack1Health3() {
    //given a game, and a Card Seis
    //Then card Seis has the attributes (2,1,3)
    Card card0 = new StandardHotStoneCard(GameConstants.SEIS_CARD);
    assertThat(card0.getManaCost(),is(2));
    assertThat(card0.getAttack(), is(1));
    assertThat(card0.getHealth(),is(3));
  }

  @Test
  public void cardSieteShouldHaveManaCost3Attack2Health4() {
    //given a game, and a Card Siete
    //Then card Siete has the attributes (3,2,4)
    Card card0 = new StandardHotStoneCard(GameConstants.SIETE_CARD);
    assertThat(card0.getManaCost(),is(3));
    assertThat(card0.getAttack(), is(2));
    assertThat(card0.getHealth(),is(4));
  }

  @Test
  public void firstTurnIsTurnCounter0() {
    // Given a new game
    // Then the first turn is turn 0
    assertThat(game.getTurnNumber(),is(0));
  }

  @Test
  public void secondTurnIsTurnCounter1() {
    // Given a new game
    // When a turn has passed
    game.endTurn();
    // Then the second turn is turn 1
    assertThat(game.getTurnNumber(),is(1));
  }

  @Test
  public void findusWinsGameWhenTurnCounterIs8() {
    //given a game
    //when turn eight starts, Findus wins game;
    TestHelper.advanceGameNRounds(game, 4);
    assertThat(game.getWinner(), is(Player.FINDUS));
  }

  @Test
  public void findusIsAllowedToPlayUnoCardANDMinionAppearOnField() {
    // Given a game, and card UNO
    Card card0 = game.getCardInHand(Player.FINDUS,2); //Uno card is on index 2 in turn 0.
    // When it is allowed (Status.ok)
    assertThat(game.playCard(Player.FINDUS, card0), is(Status.OK));
  }

  @Test
  public void findusInTurnPeddersenNotAllowedToPlayCard() {
    // Given a game, and some card from peddersens Hand
    // When it is Findus turn, and Peddersen tries to play a card
    Card card = game.getCardInHand(Player.PEDDERSEN, 0);
    // Then Status has to report that it is not peddersens turn
    assertThat(game.playCard(Player.PEDDERSEN, card), is(Status.NOT_PLAYER_IN_TURN));
  }

  @Disabled
  @Test
  public void findusPlaysCardHandSize2FieldSize1() {
    //Given a new Game and it is Findus' turn
    //when findus chooses and plays card Tres
    Card chosenCard = game.getCardInHand(Player.FINDUS, 0);
    game.playCard(Player.FINDUS, chosenCard);
    //then his handsize is reduced by 1 and fieldsize is increased by one
    assertThat(game.getHandSize(Player.FINDUS), is(2));
    //assertThat(game.getFieldSize(Player.FINDUS),is(1));
  }

  /** REMOVE ME. Not a test of HotStone, just an example of the
   matchers that the hamcrest library has... */
  @Test
  public void shouldDefinetelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    // Hamcrest uses the 'equals()' method
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // You can use is on any type
    int answerToLifeUniverseAndEverything = 42;
    assertThat(answerToLifeUniverseAndEverything, is(42));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }

}
