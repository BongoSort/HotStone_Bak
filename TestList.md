

* Test computeOpponent somethingsomething 
* vi skal få tests getHandSize()

* In turn 3 findus should get Card "Cuatro" From deck
* at start of turn 8 the game ends.

Game Start tests
* **OK** At start of game Findus should have three cards in hand, Tres at index 0, Dos at index 1, and Uno at index 2. (OK)
* At start of game Peddersen should have three cards in hand, Tres at index 0, Dos at index 1, and Uno at index 2.

Game state tests (mulig omnavngivning???) 
* **TODO** When Findus plays Uno, Then it is allowed (Status.OK) (OK HERTIL),
  and Then minion Uno appears at index 0 on the field. (TODO)

Turn tests
* **OK** Given an initialized game, Findus is player in turn (OK)
* **OK** When Findus ends its turn, it is Peddersen in turn (OK)
* **OK** When Peddersen ends his turn, it is Findus turn. (OK)
* at start of turn 8 the game ends.

DECK tests
* After a game is started (1 turn), each players deck size is 4
* In turn 2 Findus deck size should be 3 while Peddersens deck size is 4
* In turn 3 Findus deck size should be 3 while Peddersens deck size is 3
* In turn 4 Findus deck size should be 2 while Peddersens deck size is 3
* In turn 5 Findus deck size should be 2 while Peddersens deck size is 2
* In turn 6 Findus deck size should be 1 while Peddersens deck size is 2
* In turn 7 Findus deck size should be 1 while Peddersens deck size is 1
* etc.

CARD Tests
* **OK** Card Uno has attributes (1,1,1) (OK)
* **OK** Card Dos has attributes (2,2,2) (OK)
* **OK** Card Tres has attributes (3,3,3) (OK)
* Card Cuatro has attributes (2,3,1)
* Card Cinco has attributes (3,5,1)
* Card Siete has attributes (3,2,4)


HAND tests
* When Findus plays a Card, then his hand size is 2 and his field size is 1.
* When Findus plays a card, Then Peddersen still has 3 cards in his hand.
* In turn 3 findus should get Card "Cuatro" From deck
* When Findus starts his second turn, he should draw a new card 
* When Peddersen starts his second turn, he should draw a new card

MANA Tests
* When Findus plays Uno, Then the mana available is one less (2 left)
* at the Start of a players turn, their mana is restored to 3.
* When Peddersen plays Dos, Then the mana available is two less.

HERO Tests
* After Hero Baby uses power "cute", players mana is reduced by 2.