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
  public void peddersenEndsTurnNowItIsFindusTurn() {
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
    Card card0 = new StandardHotStoneCard(GameConstants.UNO_CARD, Player.FINDUS);
    assertThat(card0.getManaCost(), is(1));
    assertThat(card0.getAttack(),is(1));
    assertThat(card0.getHealth(), is(1));
  }
  @Test
  public void cardDosShouldHaveManaCostTwoAttackTwoHealthTwo() {
    //given a game, and a Card Dos
    //Then card Dos has the attributes (2,2,2) (Mana cost, Attack, Health)
    Card card0 = new StandardHotStoneCard(GameConstants.DOS_CARD, Player.FINDUS);
    assertThat(card0.getManaCost(), is(2));
    assertThat(card0.getAttack(),is(2));
    assertThat(card0.getHealth(), is(2));
  }

  @Test
  public void cardTresShouldHaveManaCostThreeAttackThreeHealthThree() {
    //given a game, and a Card Tres
    //Then card Tres has the attributes (3,3,3)
    Card card0 = new StandardHotStoneCard(GameConstants.TRES_CARD, Player.FINDUS);
    assertThat(card0.getManaCost(),is(3));
    assertThat(card0.getAttack(), is(3));
    assertThat(card0.getHealth(),is(3));
  }

  @Test
  public void cardCuatroShouldHaveManaCost2Attack3Health1() {
    //given a game, and a Card Cuatro
    //Then card Cuatro has the attributes (2,3,1)
    Card card0 = new StandardHotStoneCard(GameConstants.CUATRO_CARD,Player.PEDDERSEN);
    assertThat(card0.getManaCost(),is(2));
    assertThat(card0.getAttack(), is(3));
    assertThat(card0.getHealth(),is(1));
  }
  @Test
  public void cardCincoShouldHaveManaCost3Attack5Health1() {
    //given a game, and a Card Cinco
    //Then card Cinco has the attributes (3,5,1)
    Card card0 = new StandardHotStoneCard(GameConstants.CINCO_CARD,Player.PEDDERSEN);
    assertThat(card0.getManaCost(),is(3));
    assertThat(card0.getAttack(), is(5));
    assertThat(card0.getHealth(),is(1));
  }
  @Test
  public void cardSeisShouldHaveManaCost2Attack1Health3() {
    //given a game, and a Card Seis
    //Then card Seis has the attributes (2,1,3)
    Card card0 = new StandardHotStoneCard(GameConstants.SEIS_CARD, Player.FINDUS);
    assertThat(card0.getManaCost(),is(2));
    assertThat(card0.getAttack(), is(1));
    assertThat(card0.getHealth(),is(3));
  }

  @Test
  public void cardSieteShouldHaveManaCost3Attack2Health4() {
    //given a game, and a Card Siete
    //Then card Siete has the attributes (3,2,4)
    Card card0 = new StandardHotStoneCard(GameConstants.SIETE_CARD, Player.FINDUS);
    assertThat(card0.getManaCost(),is(3));
    assertThat(card0.getAttack(), is(2));
    assertThat(card0.getHealth(),is(4));
  }

  @Test
  public void firstTurnIsTurnCount0() {
    // Given a new game
    // Then the first turn is turn 0
    assertThat(game.getTurnNumber(),is(0));
  }

  @Test
  public void secondTurnIsTurnCount1() {
    // Given a new game
    // When a turn has passed
    game.endTurn();
    // Then the second turn is turn 1
    assertThat(game.getTurnNumber(),is(1));
  }

  @Test
  public void findusWinsGameWhenTurnCount8() {
    //given a game
    //when turn eight starts, Findus wins game;
    TestHelper.advanceGameNRounds(game, 4);
    assertThat(game.getWinner(), is(Player.FINDUS));
  }

  @Test
  public void findusIsAllowedToPlayUnoCardANDMinionAppearOnField() {
    // Given a game, and card UNO
    Card card0 = game.getCardInHand(Player.FINDUS,2); //Uno card is on index 2 in turn 0.
    // When UNO card is played
    Status findusPlaysCard = game.playCard(Player.FINDUS, card0);
    // Then it is allowed (Status.ok)
    assertThat(findusPlaysCard, is(Status.OK));
    // Then the card in Findus' Field at index 0 should be Uno.
    assertThat(game.getCardInField(Player.FINDUS, 0), is(card0));
  }

  @Test
  public void peddersenIsAllowedToPlayDosCardAndMinionAppearOnField() {
    // Given a game
    // When it becomes Peddersens turn.
    game.endTurn();
    Card card0 = game.getCardInHand(Player.PEDDERSEN,1); //Dos card is on index 1 in turn 1.
    // When Dos card is played
    Status peddersenPlaysCard = game.playCard(Player.PEDDERSEN, card0);
    // Then it is allowed (Status.ok)
    assertThat(peddersenPlaysCard, is(Status.OK));
    // Then the card in Peddersen's Field at index 0 should be Dos.
    assertThat(game.getCardInField(Player.PEDDERSEN, 0), is(card0));
  }
  @Test
  public void peddersenPlaysACardHandSize2FieldSize1() {
    //Given a new Game
    // When it is peddersens turn
    game.endTurn();
    // Then peddersen chooses and plays card Tres (at index 0)
    Card chosenCard = game.getCardInHand(Player.PEDDERSEN, 0);
    game.playCard(Player.PEDDERSEN, chosenCard);
    //then his handsize is reduced by 1 and fieldsize is increased by one
    assertThat(game.getHandSize(Player.PEDDERSEN), is(2));
    assertThat(game.getFieldSize(Player.PEDDERSEN),is(1));
  }

  @Test
  public void eachDeckShouldHaveSize4AtStartOfGame() {
    // Given a new game
    // When the decks have been initialized
    // Then size of both decks is 4
    assertThat(game.getDeckSize(Player.FINDUS), is(4));
    assertThat(game.getDeckSize(Player.PEDDERSEN), is(4));
  }

  @Test
  public void inTurnThreeFindusShouldDrawCardCuatro() {
    // Given a game, findus turn first
    // when it is turn three
    game.endTurn();
    game.endTurn();
    //Then Findus draws card Cuatro from deck.
    Card drawnCard = game.getCardInHand(Player.FINDUS, 0);
    assertThat(drawnCard.getName(), is(GameConstants.CUATRO_CARD));
  }

  @Test
  public void inTurnFiveFindusShouldDrawCardCinco() {
    // Given a game, findus turn first
    // When it is Turn 5
    game.endTurn();
    game.endTurn();
    game.endTurn();
    game.endTurn();
    //Then Findus draws Card Cinco from deck.
    Card drawnCard = game.getCardInHand(Player.FINDUS, 0);
    assertThat(drawnCard.getName(), is(GameConstants.CINCO_CARD));
  }

  @Test
  public void inTurnFourPeddersenShouldDrawCardCuatro() {
    // Given a game
    // When it is turn four
    game.endTurn();
    game.endTurn();
    game.endTurn();
    //Then peddersen draws Card Cinco from deck.
    Card drawnCard = game.getCardInHand(Player.PEDDERSEN, 0);
    assertThat(drawnCard.getName(), is(GameConstants.CUATRO_CARD));
  }

  @Test
  public void findusInTurnPeddersenNotAllowedToPlayCard() {
    // Given a game, and some card from peddersens Hand
    // When it is Findus turn, and Peddersen tries to play a card
    Card card = game.getCardInHand(Player.PEDDERSEN, 0);
    // Then Status has to report that it is not peddersens turn
    assertThat(game.playCard(Player.PEDDERSEN, card), is(Status.NOT_PLAYER_IN_TURN));
  }

  @Test
  public void startGameFindusPlaysCardHandSize2FieldSize1() {
    //Given a new Game, it is Findus' turn
    //when Findus chooses and plays card Tres (at index 0)
    Card chosenCard = game.getCardInHand(Player.FINDUS, 0);
    game.playCard(Player.FINDUS, chosenCard);
    //then his handsize is reduced by 1 and fieldsize is increased by one
    assertThat(game.getHandSize(Player.FINDUS), is(2));
    assertThat(game.getFieldSize(Player.FINDUS),is(1));
  }
  @Test
  public void startGameFindusPlays2CardsHandSize1FieldSize2() {
    //Given a new Game, it is Findus' turn
    //when Findus chooses and plays 2 cards
    Card chosenCard = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, chosenCard);

    Card chosenCard1 = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS, chosenCard1);
    //then his handsize is reduced by 2 and fieldsize is increased by 2
    assertThat(game.getHandSize(Player.FINDUS), is(1));
    assertThat(game.getFieldSize(Player.FINDUS),is(2));
  }

  @Test
  public void findusPlaysCardPeddersenStillHas3CardsInHand() {
    //Given a new Game, it is Findus' turn
    //when Findus chooses and plays card Tres (at index 0)
    Card chosenCard = game.getCardInHand(Player.FINDUS, 0);
    game.playCard(Player.FINDUS, chosenCard);
    // Then Peddersens handsize is still 3
    assertThat(game.getHandSize(Player.PEDDERSEN), is(3));
  }

  @Test
  public void inTurn3FindusDeckSizeIs3PeddersenDeckSizeIs4() {
    //Given a Game
    //when it is turn 3
    game.endTurn();
    game.endTurn();
    //then Findus Deck size is 3 and Peddersens deck size is 4
    assertThat(game.getDeckSize(Player.FINDUS), is(3));
    assertThat(game.getDeckSize(Player.PEDDERSEN), is(4));
  }

  @Test
  public void inTurn8FindusDeckSizeIs0PeddersenDeckSizeIs1() {
    //Given a Game
    //when it is turn 8
    TestHelper.advanceGameNRounds(game, 4);
    //then Findus Deck size is 0 and Peddersens deck size is 1
    assertThat(game.getDeckSize(Player.FINDUS), is(0));
    assertThat(game.getDeckSize(Player.PEDDERSEN), is(1));
  }

  //tests getHand method, by testing cases already tested by other methods.
  @Test
  public void findusHandShouldBeUnoDosTresInTurnOne() {
    // Given a new game
    // When findus have filled his hand
    // Then Findus should have Cards Uno, Dos, Tres in his hand
    for (Card c : game.getHand(Player.FINDUS)) {
      switch (c.getName()) {
        case GameConstants.UNO_CARD -> assertThat(game.getCardInHand(Player.FINDUS, 2), is(c));
        case GameConstants.DOS_CARD -> assertThat(game.getCardInHand(Player.FINDUS, 1), is(c));
        case GameConstants.TRES_CARD -> assertThat(game.getCardInHand(Player.FINDUS, 0), is(c));
      }
    }
  }
    @Test
    public void peddersensHandShouldBeUnoDosTresInTurnOne() {
      // Given a new game
      // When findus have filled his hand
      // Then Findus should have Cards Uno, Dos, Tres in his hand
      for (Card c :  game.getHand(Player.PEDDERSEN)) {
        switch (c.getName()) {
          case GameConstants.UNO_CARD -> assertThat(game.getCardInHand(Player.PEDDERSEN, 2), is(c));
          case GameConstants.DOS_CARD -> assertThat(game.getCardInHand(Player.PEDDERSEN, 1), is(c));
          case GameConstants.TRES_CARD -> assertThat(game.getCardInHand(Player.PEDDERSEN, 0), is(c));
        }
      }
  }

  @Test
  public void findusHeroIsBaby() {
    //Given a game
    //When it is AlphaStone
    //Then Findus' Hero is Baby
    assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.BABY_HERO_TYPE));
  }


  @Test
  public void peddersenHeroIsBaby() {
    //Given a game
    //When it is AlphaStone
    //Then Peddersen's Hero is Baby
    assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.BABY_HERO_TYPE));
  }

  @Test
  public void peddersenInTurnFindusHeroIsInactive() {
    //Given a game and it is peddersens turn
    game.endTurn();
    //Then Findus Hero is inactive
    assertThat(game.getHero(Player.FINDUS).isActive(), is(false));
  }

  @Test
  public void findusInTurnHeroIsActive() {
    //Given a game and it is findus' turn
    //Then Findus' hero is active.
    assertThat(game.getHero(Player.FINDUS).isActive(), is(true));
  }

  @Test
  public void allowFindusHeroToUsePowerOnce() {
    //Given a game
    //Then Findus' hero is allowed to use its power
    assertThat(game.usePower(Player.FINDUS), is(Status.OK));
  }

  @Test
  public void doesNotAllowFindusHeroToUsePowerTwice() {
    //Given a game
    //When Findus uses his power
    game.usePower(Player.FINDUS);
    //Then he is not allowed to use his power twice.
    assertThat(game.usePower(Player.FINDUS), is(Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND));
  }

  @Test
  public void peddersenInTurnIsAllowedToUseHeroPowerEvenIfFindusUsedPowerInHisTurn() {
    //Given a game and Findus has used his power
    game.usePower(Player.FINDUS);
    //When Findus ends his turn
    game.endTurn();
    //Then Peddersen is allowed to use his heros power.
    assertThat(game.usePower(Player.PEDDERSEN),is(Status.OK));
  }

  @Test
  public void shouldOnlyAllowPeddersenToUseHeroPowerWhenHeroIsActive() {
    //Given a game and it is Peddersens turn
    game.endTurn();
    //then Peddersens hero is active and his hero is allowed to use its power
    assertThat(game.getHero(Player.PEDDERSEN).isActive(), is(true));
    assertThat(game.usePower(Player.PEDDERSEN),is(Status.OK));
    //but Findus should not be allowed to use his heros power.
  }

  @Test
  //When player in turn uses power, then hero becomes inactive
  public void afterPeddersenUsesHeroPowerHeroShouldBeInactive() {
    //Given a game and it is Peddersens turn
    game.endTurn();
    //when Peddersens uses his heros power
    game.usePower(Player.PEDDERSEN);
    //Then Peddersens hero is inactive (isactive() returns false)
    assertThat(game.getHero(Player.PEDDERSEN).isActive(), is(false));
  }

  @Test
  public void shouldNotAllowPeddersenToUseHeroPowerWhenNotInTurn() {
    //Given a game, and it is Findus turn
    assertThat(game.usePower(Player.PEDDERSEN),is(Status.NOT_PLAYER_IN_TURN));
  }

  @Test
  public void shouldNotAllowFindusToUseHeroPowerWhenNotInTurn() {
    //Given a game, and it is Peddersens turn
    game.endTurn();
    //Then Findus is not allowed to use his heros power.
    assertThat(game.usePower(Player.FINDUS),is(Status.NOT_PLAYER_IN_TURN));
  }

// MANA TESTS:
  @Test
  public void findusAndPeddersenShouldHave3ManaAtStartOfGame() {
    //Given a new game
    //Both Findus and Peddersen starts with 3 mana each.
    assertThat(game.getHero(Player.FINDUS).getMana(), is(3));
    assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(3));
  }

  @Test
  public void heroBabyUsesCutePlayerManaReducedBy2() {
    //Given a game
    // Findus' hero, Baby, uses power "cute" that costs 2 mana.
    game.usePower(Player.FINDUS);
    //Then Findus' mana is reduced by 2
    assertThat(game.getHero(Player.FINDUS).getMana(), is(1));
  }

  @Test
  public void findusPlaysCardUnoThenHeroManaIsReducedByCardManaCost() {
    //Given a game
    //When Findus plays Card Uno
    Card unoCard = game.getCardInHand(Player.FINDUS,2);
    game.playCard(Player.FINDUS,unoCard);
    //Then his heros mana is reduced by Uno manacost which is 1
    assertThat(game.getHero(Player.FINDUS).getMana(), is(3 - 1));
  }

  @Test
  public void peddersenPlaysCardTresThenHeroManaIsReducedByCardManaCost() {
    //Given a game and it is peddersens turn
    game.endTurn();
    //when he plays Card Tres from his Hand
    Card tresCard = game.getCardInHand(Player.PEDDERSEN,0);
    game.playCard(Player.PEDDERSEN,tresCard);
    //then his heros mana is reduced by Tres Manacost which is 3.
    assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(3-3));
  }

  @Test
  public void startOfNewTurnPlayerInTurnRefillsTheirHerosMana() {
    //Given a new game
    //Findus uses hero power
    game.usePower(Player.FINDUS);
    //Then his heros mana is redued by 2 and his turn is ended.
    assertThat(game.getHero(Player.FINDUS).getMana(), is(1));
    game.endTurn();

    //Now it is Peddersens turn
    //He uses his heros power and its mana is also reduced by two.
    //His turn is then ended.
    game.usePower(Player.PEDDERSEN);
    assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(1));
    game.endTurn();
    //A new turn has started for Findus
    //His mana is now refilled again.
    assertThat(game.getHero(Player.FINDUS).getMana(),is(3));

    //A new turn has started for Peddersen
    //His mana is now refilled again.
    game.endTurn();
    assertThat(game.getHero(Player.PEDDERSEN).getMana(),is(3));
  }

  @Test
  //FindusShouldNotBeAbleToPlayMinionWithToHighManaCost()
  public void FindusShouldNotBeAbleToPlayMinionWithToHighManaCost() {
    //Given a game and Findus has used his power
    game.usePower(Player.FINDUS);
    //Then He Should not be allowed to play Card Tres in his hand, since its mana Cost is to high
    Card tresCard = game.getCardInHand(Player.FINDUS,0);
    assertThat(game.playCard(Player.FINDUS,tresCard),is(Status.NOT_ENOUGH_MANA));
  }

  @Test
  public void FindusShouldNotBeAbleToUseHeroPowerWhenManaIsTooLow() {
    //Given a game and Findus have played Tres which means his Heroes mana is 0
    Card tresCard = game.getCardInHand(Player.FINDUS,0);
    game.playCard(Player.FINDUS,tresCard);
    //Then he should not be allowed to use his Heroes power
    assertThat(game.usePower(Player.FINDUS),is(Status.NOT_ENOUGH_MANA));
  }

  @Test
  public void peddersenShouldNotBeAbleToPlayMinionWithToHighManaCost() {
    //Given a game and Peddersen has used his power
    game.endTurn();
    game.usePower(Player.PEDDERSEN);
    //Then He Should not be allowed to play Card Tres in his hand, since its mana Cost is too high
    Card tresCard = game.getCardInHand(Player.PEDDERSEN,0);
    assertThat(game.playCard(Player.PEDDERSEN,tresCard),is(Status.NOT_ENOUGH_MANA));
  }

  @Test
  public void peddersenShouldNotBeAbleToUseHeroPowerWhenManaIsTooLow() {
    //Given a game and Peddersen have played Tres which means his Hero's mana is 0
    game.endTurn();
    Card tresCard = game.getCardInHand(Player.PEDDERSEN,0);
    game.playCard(Player.PEDDERSEN,tresCard);
    //Then he should not be allowed to use his Heroes power
    assertThat(game.usePower(Player.PEDDERSEN),is(Status.NOT_ENOUGH_MANA));
  }

  //ATTACK + HEALTH Tests
  @Test
  public void findusAndPeddersensHeroShouldStartAGameWith21Health() {
    //Given a game
    //Then both players hero should have 21 health.
    assertThat(game.getHero(Player.FINDUS).getHealth(),is(GameConstants.HERO_MAX_HEALTH));
    assertThat(game.getHero(Player.PEDDERSEN).getHealth(),is(GameConstants.HERO_MAX_HEALTH));
  }


  @Test
  public void findusInTurnCardsPlayedShouldBeInactiveUntilHisNextTurn() {
    //Given a game
    //When Findus plays unoCard and dosCard to the field
    Card unoCard = game.getCardInHand(Player.FINDUS,2);
    game.playCard(Player.FINDUS,unoCard);
    Card dosCard = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS,dosCard);
    //Then the two cards should be inactive
    assertThat(game.getCardInField(Player.FINDUS,0).isActive(),is(false));
    assertThat(game.getCardInField(Player.FINDUS,1).isActive(),is(false));
    //When Findus turn ends
    game.endTurn();
    //Then the cards should stay inactive
    assertThat(game.getCardInField(Player.FINDUS,0).isActive(),is(false));
    assertThat(game.getCardInField(Player.FINDUS,1).isActive(),is(false));
    //When Peddersens turn ends
    game.endTurn();
    //Then the cards in Findus field should be active.
    assertThat(game.getCardInField(Player.FINDUS,0).isActive(),is(true));
    assertThat(game.getCardInField(Player.FINDUS,1).isActive(),is(true));
  }

  @Test
  public void peddersenInTurnCardsPlayedShouldBeInactiveUntilHisNextTurn() {
    //Given a game and it is peddersens turn
    game.endTurn();
    //When peddersen plays uno and dos
    Card unoCard = game.getCardInHand(Player.PEDDERSEN,2);
    game.playCard(Player.PEDDERSEN,unoCard);
    Card dosCard = game.getCardInHand(Player.PEDDERSEN, 1);
    game.playCard(Player.PEDDERSEN,dosCard);
    //Then the two cards should be inactive
    assertThat(game.getCardInField(Player.PEDDERSEN,0).isActive(),is(false));
    assertThat(game.getCardInField(Player.PEDDERSEN,1).isActive(),is(false));
    //When Peddersens turn ends
    game.endTurn();
    //Then the cards should stay inactive
    assertThat(game.getCardInField(Player.PEDDERSEN,0).isActive(),is(false));
    assertThat(game.getCardInField(Player.PEDDERSEN,1).isActive(),is(false));
    //When Findus turn ends
    game.endTurn();
    //Then the cards in Peddersens field should be active.
    assertThat(game.getCardInField(Player.PEDDERSEN,0).isActive(),is(true));
    assertThat(game.getCardInField(Player.PEDDERSEN,1).isActive(),is(true));

  }

  @Test
  public void findusEndsTurnActiveMinionsShouldBeInActive() {
    TestHelper.fieldUnoDosForFindusAndUnoDosForPeddersen(game);
    game.endTurn();
    for(Card c : game.getField(Player.FINDUS)) {
      assertThat(c.isActive(), is(false));
    }
  }

  @Test
  public void findusMinionShouldNotBeAllowedToAttackPeddersensHeroInTurn1() {
    //Given a game
    //When it is turn 1, Findus plays a card to the field
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    //Then it should not be ok to attack Peddersens hero, since the minion is inActive.
    assertThat(game.attackHero(Player.FINDUS, game.getCardInField(Player.FINDUS,0)),
              is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
  }

  @Test
  public void findusMinionShouldBeAllowedToAttackPeddersensHeroInTurn3() {
    //Given a game
    //When it is turn 1, Findus plays a card to the field
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    game.endTurn();
    //When it is turn 3, Findus has a minion on the field
    game.endTurn();
    //Then it should be ok to attack Peddersens hero
    assertThat(game.attackHero(Player.FINDUS, game.getCardInField(Player.FINDUS,0)), is(Status.OK));
  }

  @Test
  public void findusMinionAttacksPeddersensHeroWhichDecreasesHeroHealth_AndMinionBecomesInactive() {
    //Given a game
    //When it is turn 1, Findus plays a card to the field
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2));
    game.endTurn();
    //When it is turn 3, Findus has a minion on the field
    game.endTurn();
    Card fieldCard = game.getCardInField(Player.FINDUS,0);
    game.attackHero(Player.FINDUS, fieldCard);
    assertThat(game.getHero(Player.PEDDERSEN).getHealth(),
            is(GameConstants.HERO_MAX_HEALTH - fieldCard.getAttack()));
    assertThat(fieldCard.isActive(), is(false));
  }

  @Test
  public void peddersensMinionAttacksFindusHeroWhichDecreasesHeroHealth_AndMinionBecomesInactive() {
    //Given a game and it is peddersens turn
    game.endTurn();
    //when peddersen plays a Card, and waits a turn.
    game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, 2));
    game.endTurn();
    game.endTurn();
    //then peddersen attacks Findus hero, which reduces Findus Hero hp
    Card fieldCard = game.getCardInField(Player.PEDDERSEN,0);
    game.attackHero(Player.PEDDERSEN, fieldCard);
    assertThat(game.getHero(Player.FINDUS).getHealth(),
            is(GameConstants.HERO_MAX_HEALTH - fieldCard.getAttack()));
    assertThat(fieldCard.isActive(), is(false));
  }

  @Test
  public void AllowAttackingAnOpponentsMinionAndValidatesIt() {
    TestHelper.fieldTresForFindusAndDosForPeddersen(game);
    assertThat(game.attackCard(Player.FINDUS, game.getCardInField(Player.FINDUS,0),
            game.getCardInField(Player.PEDDERSEN,0)), is(Status.OK));
  }

  @Test
  public void doesNotAllowOwnMinionsToAttackEachOther() {
    TestHelper.fieldTresForFindusAndDosForPeddersen(game);
    assertThat(game.attackCard(Player.FINDUS, game.getCardInField(Player.FINDUS,0),
            game.getCardInField(Player.FINDUS,0)), is(Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION));
  }
  @Test
  public void findusMinionIsNotAllowedToAttackPeddersensMinionWhenInactive() {
    //Given a game where findus and peddersen have both played a card to the field
    TestHelper.fieldTresForFindusAndDosForPeddersen(game);
    //when Findus minion has attacked Peddersens hero.
    Card findusCard = game.getCardInField(Player.FINDUS,0);
    game.attackHero(Player.FINDUS,findusCard);
    //then Findus minion is inactive and cannot attack Peddersens minion
    assertThat(findusCard.isActive(),is(false));
    assertThat(game.attackCard(Player.FINDUS,findusCard,game.getCardInField(Player.PEDDERSEN,0)),
            is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
  }

  @Test
  public void findusMinionIsAllowedToAttackPeddersensMinionWhenActive() {
    //Given a game, Findus and peddersen have played a card to the field and it is findus turn.
    TestHelper.fieldTresForFindusAndDosForPeddersen(game);
    //when
    Card findusCard = game.getCardInField(Player.FINDUS,0);
    assertThat(game.getCardInField(Player.FINDUS,0).isActive(), is(true));
    assertThat(game.attackCard(Player.FINDUS,findusCard,game.getCardInField(Player.PEDDERSEN,0)),
            is(Status.OK));
  }

  @Test
  public void afterFindusMinionHasAttackedPeddersensMinionFindusMinionIsInactive() {
    //Given a game, Findus and peddersen have played a card to the field and it is findus turn.
    TestHelper.fieldTresForFindusAndDosForPeddersen(game);
    //When Findus minion Attacks Peddersens minion
    game.attackCard(Player.FINDUS,game.getCardInField(Player.FINDUS,0),game.getCardInField(Player.PEDDERSEN,0));
    assertThat(game.getCardInField(Player.FINDUS,0).isActive(),is(false));
  }

  @Test
  public void findusInTurnNotAllowedToPlayPeddersensCards() {
    //given a game
    Card card = game.getCardInHand(Player.PEDDERSEN,0);
    //Then Findus is not allowed to play Cards from Peddersens Hand
    assertThat(game.playCard(Player.FINDUS,card),is(Status.NOT_OWNER));
  }

  @Test
  public void peddersenInTurnNotAllowedToPlayFindusCards() {
    //Given a game and it is Peddersens turn
    game.endTurn();
    //Then Peddersen is not allowed to play cards from Findus Hand
    Card card = game.getCardInHand(Player.FINDUS,0);
    assertThat(game.playCard(Player.PEDDERSEN,card),is(Status.NOT_OWNER));
  }

  @Test
  public void findusInTurnNotAllowedToAttackPeddersensMinionWithPeddersensOtherMinion() {
    //Given a game and Findus and Peddersen have two minions on their field.
    TestHelper.fieldUnoDosForFindusAndUnoDosForPeddersen(game);
    //Then Findus is not allowed to attack other minions with Peddersens minions
    Card peddersensMinion0 = game.getCardInField(Player.PEDDERSEN,0);
    Card peddersensMinion1 = game.getCardInField(Player.PEDDERSEN,1);
    assertThat(game.attackCard(Player.FINDUS,peddersensMinion0,peddersensMinion1),is(Status.NOT_OWNER));
  }

  @Test
  public void peddersenInTurnNotAllowedToAttackFindusMinionWithFindusOtherMinion() {
    TestHelper.fieldUnoDosForFindusAndUnoDosForPeddersen(game);
    game.endTurn();
    Card findusMinion0 = game.getCardInField(Player.FINDUS,0);
    Card findusMinion1 = game.getCardInField(Player.FINDUS,1);
    assertThat(game.attackCard(Player.PEDDERSEN,findusMinion0,findusMinion1),is(Status.NOT_OWNER));
  }

  @Test
  public void findusInTurnNotAllowedToAttackPeddersensHeroWithPeddersensMinion() {
    TestHelper.fieldTresForFindusAndDosForPeddersen(game);
    assertThat(game.attackHero(Player.FINDUS,game.getCardInField(Player.PEDDERSEN,0)),is(Status.NOT_OWNER));
  }

  @Disabled
  @Test //TODO: Skal finde ud af om man kan teste dette.
  public void PeddersenInTurnFindusNotAllowedToUsePeddersensHeroPower() {
    //Given a game and it is Peddersens Turn
    game.endTurn();
    //Then Findus is not allowed to use Peddersens Hero power
    assertThat(game.usePower(Player.FINDUS),is(Status.NOT_OWNER));
  }

  @Disabled
  @Test
  public void attackingMinionLosesHealthEqualToDefendingMinionsAttack() {
    TestHelper.fieldTresForFindusAndDosForPeddersen(game);
    game.attackCard(Player.FINDUS, game.getCardInField(Player.FINDUS,0),
            game.getCardInField(Player.PEDDERSEN,0));
    assertThat(game.getCardInField(Player.FINDUS,0).getHealth(), is(1));
  }

  @Disabled
  @Test
  public void defendingMinionLosesHealthEqualToAttackingMinionsAttack() {
    TestHelper.fieldTresForFindusAndDosForPeddersen(game);
    game.attackCard(Player.FINDUS, game.getCardInField(Player.FINDUS,0),
            game.getCardInField(Player.PEDDERSEN,0));
    assertThat(game.getCardInField(Player.PEDDERSEN,0).getHealth(), is(-1));
  }

  @Disabled
  @Test
  public void minionDieWhenZeroOrBelowHealthLeft() {
    TestHelper.fieldTresForFindusAndDosForPeddersen(game);
    game.attackCard(Player.FINDUS, game.getCardInField(Player.FINDUS,0),
            game.getCardInField(Player.PEDDERSEN,0));
    assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));
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
