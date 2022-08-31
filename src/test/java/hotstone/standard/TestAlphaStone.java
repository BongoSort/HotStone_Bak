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

import hotstone.framework.Card;
import hotstone.framework.Player;
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

  /**
   *
   */
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
    game.endTurn();
    game.endTurn();
    // Then check of the player in turn is Findus
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
  public void shouldHaveUnoDosTresCardsInitially() {
    // Given a game, Findus has 3 cards in hand
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
