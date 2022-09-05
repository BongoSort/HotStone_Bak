THIS IS OUR TEST LIST.

Game Start tests
* **OK** At start of game Findus should have three cards in hand, Tres at index 0, Dos at index 1, and Uno at index 2. (OK)
* **OK** At start of game Peddersen should have three cards in hand, Tres at index 0, Dos at index 1, and Uno at index 2.

Game state tests (mulig omnavngivning???) 
* **OK** When Findus plays Uno, Then it is allowed (Status.OK),
  and Then minion Uno appears at index 0 on the field.
* **OK** When Peddersen plays Dos, Then it is allowed (Status.OK),
    and Then minion Uno appears at index 0 on the field.
* **OK** When it is Findus Turn, Peddersen is not allowed to play a Card, Then it is not allowed.

Turn tests
* **OK** Given an initialized game, Findus is player in turn (OK)
* **OK** When Findus ends its turn, it is Peddersen in turn (OK)
* **OK** When Peddersen ends his turn, it is Findus turn. (OK)
* **OK** first turn is turncount 0
* **OK** second turn is turncount 1
* **OK** at start of turn 8 the game ends.


CARD Tests
* **OK** Card Uno has attributes (1,1,1)
* **OK** Card Dos has attributes (2,2,2)
* **OK** Card Tres has attributes (3,3,3)
* **OK** Card Cuatro has attributes (2,3,1)
* **OK** Card Cinco has attributes (3,5,1)
* **OK** Card Seis has attributes (2,1,3)
* **OK** Card Siete has attributes (3,2,4)

HAND tests
* **OK** When Findus plays a Card, then his hand size is 2 and his field size is 1.
* **OK** When Findus plays two cards, then his hand size is 1 and his field size is 2.
* **OK** When Findus plays a card, Then Peddersen still has 3 cards in his hand.
* **OK** When Peddersen in turn, and plays a card, then his handsize is 2 and his field size is 1.

DECK tests
* After a game is started (turn 1/turncount 0), each players deck size is 4
* In turn 2 Findus deck size should be 4 while Peddersens deck size is 4
* In turn 3 Findus deck size should be 3 while Peddersens deck size is 4
* In turn 4 Findus deck size should be 3 while Peddersens deck size is 3
* In turn 5 Findus deck size should be 2 while Peddersens deck size is 3
* In turn 6 Findus deck size should be 2 while Peddersens deck size is 2
* In turn 7 Findus deck size should be 1 while Peddersens deck size is 2
* In turn 8 Findus deck size should be 1 while Peddersens deck size is 1

CARD DRAW TESTS
* In turn 1 and 2, no cards should be drawn.
* In turn 3 findus should get Card "Cuatro" From deck //Aflevering
* In turn 5 Findus should get Card Cinco from deck. //aflevering 
* In turn 4 peddersen should get Card "Cuatro" From deck
* When Findus starts his second turn, he should draw a new card
* When Peddersen starts his second turn, he should draw a new card

MANA Tests
* Each Player has three mana when game begins(is initialized)
* When Findus plays Uno, Then the mana available is one less (2 left)
* at the Start of a players turn, their mana is restored to 3.
* When Peddersen plays Dos, Then the mana available is two less.
* When a player plays a card, Mana available is greater or equal to the cards cost.

HERO Tests
* After Hero Baby uses power "cute", players mana is reduced by 2.

Hero Health for later iterations of HotStone (NOT AlphaStone)
* Health starts at 21
* When the hero is attacked by a minion, Health is decreased by the attack value of the minion.
* When the health of the hero reaches 0 or below, the hero dies, and the other hero wins.