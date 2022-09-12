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
* Player not in turn is not allowed to play the other players cards


HAND tests
* **OK** When Findus plays a Card, then his hand size is 2 and his field size is 1.
* **OK** When Findus plays two cards, then his hand size is 1 and his field size is 2.
* **OK** When Findus plays a card, Then Peddersen still has 3 cards in his hand.
* **OK** When Peddersen in turn, and plays a card, then his handsize is 2 and his field size is 1.

DECK tests
* **OK** After a game is started (turn 1/turncount 0), each players deck size is 4
* **OK** In turn 2 Findus deck size should be 4 while Peddersens deck size is 4
* **OK** In turn 3 Findus deck size should be 3 while Peddersens deck size is 4
* **OK** In turn 8 Findus deck size should be 1 while Peddersens deck size is 1

CARD DRAW TESTS
* **OK** In turn 1 and 2, no cards should be drawn.
* **OK** In turn 3 findus should get Card "Cuatro" From deck.
* **OK** In turn 5 Findus should get Card Cinco from deck.
* **OK** In turn 4 peddersen should get Card "Cuatro" From deck
* **OK** When Findus starts his second turn, he should draw a new card
* **OK** When Peddersen starts his second turn, he should draw a new card

HERO Tests
* **OK** Only allow hero to use its power when hero is active
* **OK** Does not allow hero to use its power twice
* **OK** Findus Hero is Baby
* **OK** Peddersens Hero is Baby
* **OK** When it becomes a Players turn, and the player have not used its Hero Power yet, the players hero should be active.
* **OK** When player in turn uses power, then hero becomes inactive
* **OK** Peddersen in inactive when not in turn
* **OK** when Findus is in turn, then findus hero is active.
* **OK** when peddersen not in turn, then peddersens hero is inactive.
* **OK** Peddersen in turn is allowed to use hero power even if Findus used power in his turn.
* Player not in turn is not allowed to use the other players hero power


MANA Tests
* **OK** Each Player has three mana when game begins(is initialized)
* **OK** After Hero Baby uses power "cute", players mana is reduced by 2.
* **OK** When Findus plays Uno, Then the mana available is one less (2 left)
  **OK** Mana Production: At the start of a player’s turn, he/she gets three mana.
* **OK** When Peddersen plays Tres, Then the mana available is two less.
* **OK** When a player plays a card, Mana available is greater or equal to the cards cost.
* **OK** It is not possible to use more mana, than you have when playing cards.
* **OK** It is not possible to use more mana, than you have when using hero power.

Play Card Tests (Field tests???)
* **OK** The cards that Findus plays in his turn are inactive until his next turn.
* **OK** The cards that Peddersen plays in his turn are inactive until his next turn.

ATTACK Tests
* **OK**Allow attacking the opponent hero with a minion
* **OK** When a minion has attacked it becomes inactive
* **TODO**Allow attacking an opponents minion, validate if its possible
* Do not allow players minions to attack each other. 

* HEALTH Tests
* **OK** Check if health starts at 21
* **OK** When the hero is attacked by a minion, Health is decreased by the attack value of the minion.
* Minions should die when they have 0 health left

Status tests:
* **OK** When a card is played as a minion it is inactive until it is the players turn again.

ENDTURN
* **OK** Findus heros Mana is restored after Peddersen ends turn.
* **OK** Findus minions is set to active after Peddersen ends turn.
* **OK** update mana for hero,
* **OK** changing minions' state to active.

CARD PLAYS
* Execute any effect that a card may have when being played (Senere-stone)?

MÅSKE IKKE ALPHASTONE, MEN SENERE-STONE??
* When trying to draw a card from an empty deck, the hero should take 2 damage.
* When the health of the hero reaches 0 or below, the hero dies, and the other hero wins.

jeg er nu på GitLab, wuhuuu. Forhåbentlig!??