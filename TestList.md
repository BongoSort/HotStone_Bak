# THIS IS OUR TEST LIST.

## ALPHASTONE
### Game Start tests
* **OK** At start of game Findus should have three cards in hand, Tres at index 0, Dos at index 1, and Uno at index 2. (OK)
* **OK** At start of game Peddersen should have three cards in hand, Tres at index 0, Dos at index 1, and Uno at index 2.

### Game state tests (mulig omnavngivning???)
* **OK** When Findus plays Uno, Then it is allowed (Status.OK),
  and Then minion Uno appears at index 0 on the field.
* **OK** When Peddersen plays Dos, Then it is allowed (Status.OK),
  and Then minion Uno appears at index 0 on the field.
* **OK** When it is Findus Turn, Peddersen is not allowed to play a Card, Then it is not allowed.

### Turn tests
* **OK** Given an initialized game, Findus is player in turn (OK)
* **OK** When Findus ends its turn, it is Peddersen in turn (OK)
* **OK** When Peddersen ends his turn, it is Findus turn. (OK)
* **OK** first turn is turncount 0
* **OK** second turn is turncount 1
* **OK** at start of turn 8 the game ends.


### CARD Tests
* **OK** Card Uno has attributes (1,1,1)
* **OK** Card Dos has attributes (2,2,2)
* **OK** Card Tres has attributes (3,3,3)
* **OK** Card Cuatro has attributes (2,3,1)
* **OK** Card Cinco has attributes (3,5,1)
* **OK** Card Seis has attributes (2,1,3)
* **OK** Card Siete has attributes (3,2,4)
* **OK** Player not in turn is not allowed to play the other players cards


### HAND tests
* **OK** When Findus plays a Card, then his hand size is 2 and his field size is 1.
* **OK** When Findus plays two cards, then his hand size is 1 and his field size is 2.
* **OK** When Findus plays a card, Then Peddersen still has 3 cards in his hand.
* **OK** When Peddersen in turn, and plays a card, then his handsize is 2 and his field size is 1.

### DECK tests
* **OK** After a game is started (turn 1/turncount 0), each players deck size is 4
* **OK** In turn 2 Findus deck size should be 4 while Peddersens deck size is 4
* **OK** In turn 3 Findus deck size should be 3 while Peddersens deck size is 4
* **OK** In turn 8 Findus deck size should be 1 while Peddersens deck size is 1

### CARD DRAW TESTS
* **OK** In turn 1 and 2, no cards should be drawn.
* **OK** In turn 3 findus should get Card "Cuatro" From deck.
* **OK** In turn 5 Findus should get Card Cinco from deck.
* **OK** In turn 4 peddersen should get Card "Cuatro" From deck
* **OK** When Findus starts his second turn, he should draw a new card
* **OK** When Peddersen starts his second turn, he should draw a new card

### HERO Tests
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
* **KAN DENNE TESTES??** Player not in turn is not allowed to use the other players hero power


### MANA Tests
* **OK** Each Player has three mana when game begins(is initialized)
* **OK** After Hero Baby uses power "cute", players mana is reduced by 2.
* **OK** When Findus plays Uno, Then the mana available is one less (2 left)
  **OK** Mana Production: At the start of a player’s turn, he/she gets three mana.
* **OK** When Peddersen plays Tres, Then the mana available is two less.
* **OK** When a player plays a card, Mana available is greater or equal to the cards cost.
* **OK** It is not possible to use more mana, than you have when playing cards.
* **OK** It is not possible to use more mana, than you have when using hero power.

### Play Card Tests (Field tests???)
* **OK** The cards that Findus plays in his turn are inactive until his next turn.
* **OK** The cards that Peddersen plays in his turn are inactive until his next turn.

### ATTACK Tests
* **OK**Allow attacking the opponent hero with a minion
* **OK** When a minion has attacked a Hero it becomes inactive
* **OK** Allow attacking an opponents minion, validate if its possible
* **OK** Do not allow players minions to attack each other. 
* **OK** When a minion has attacked a minion it becomes inactive
* **OK** A minion should be active before it is allowed to attack a minion
* **OK** A minion should be active before it is allowed to attack a hero
* **OK** Player in Turn is not allowed to attack a minion with other players minions.
* **OK** Player in Turn is not allowed to attack a Hero with other players minions. (Mangler at teste for Peddersen)
  (har kun testet for at en spiller når de er i tur kan angribe med den anden persons minions, ikke at de kan gøre det i løbet af modstanderens tur)

### HEALTH Tests
* **OK** Check if health starts at 21
* **OK** When the hero is attacked by a minion, Health is decreased by the attack value of the minion.
* **OK** minions should loose health when being attacked
* **OK** defending minions attack their attacker.
* **OK** Minions should die when they have 0 health left

### Status tests:
* **OK** When a card is played as a minion it is inactive until it is the players turn again.

### ENDTURN
* **OK** Findus heros Mana is restored after Peddersen ends turn.
* **OK** Findus minions is set to active after Peddersen ends turn.
* **OK** update mana for hero,
* **OK** changing minions' state to active.
* **OK** Player not in turns Minions should be inactive 

### CARD PLAYS
* Execute any effect that a card may have when being played (Senere-stone)?


## BETASTONE
### Mana Tests
* **OK** each player starts with one mana
* **OK** for each turn the players mana increasing by one
* **OK** after reaching 7 mana the player can't get more mana.
* **OK** Unused mana from a round does not transfer to next turn

### EmptyDeck Tests
* **OK** Findus should take 2 damage when drawing from an empty deck

### Winner Tests
* **OK** When Findus Hero looses all health, Peddersen is the winner
* **OK** When Peddersens Hero looses all health, Findus is the winner

## GammaStone
* **OK** Findus hero should be ThaiChef
* **OK** Peddersen Hero Type Should Be Danish Chef
* **OK**  Thai Chefs owner should be Findus
* **OK**  Danish Chefs owner should be Peddersen
* **OK** Thai Chefs Hero Power Should Decrease Opponents Hero Health By 2
* **OK** Danish Chef Hero Power Should Field Minion Sovs With Attack Power 1 And Health 1


## DeltaStone
### CARD Tests
* **OK** Card Brown Rice Should Have Attributes (1,1,2)
* **OK** Card French Fries Should Have Attributes (1,2,1)
* **OK** Card Green Salad Should Have Attributes (2,2,3)
* **OK** Card Tomato Salad Should Have Attributes (2,3,2)
* **OK** Card Poke Bowl Should Have Attributes (3,2,4)
* **OK** Card Pumpkin Soup Should Have Attributes (4,2,7)
* **OK** Card Noodle Soup Should Have Attributes (4,5,3)
* **OK** Card Spring Rolls Should Have Attributes (5,3,7)
* **OK** Card Baked Salmon Should Have Attributes (5,8,2)
* **OK** Card Chicken Curry Should Have Attributes (6,8,4)
* **OK** Card Beef Burger Should Have Attributes (6,5,6)
* **OK** Card Filet Mignon Should Have Attributes (7,9,5)

### DECK TESTS
* **OK** Deck size should be 24
* **OK** Deck should contain 2 of each card from the Dish Deck
* **OK** First card in deck should cost one mana
* **OK** Second card in deck should cost two mana or less
* **OK** third card in deck should cost three mana or less

### Mana Tests
* **OK** each player should start with 7 mana
* **OK** each player have 7 mana in total each round

### Non-unit tests
* **OK** First card in hand should cost one mana for both players
* **OK** Second card in hand should cost two mana or less for both players
* **OK** third card in hand should cost three mana or less for both players
* **OK** After either player plays first card in hand, mana should be 6
* **OK** each player's deck should contain 21 cards at start of game.

## EpsilonStone
### Hero Tests
* **OK** Findus is French Chef
* **OK** Peddersen is Italian Chef

### Hero Power Tests
* **OK** French chef power "Red wine" picks a random minion on opponents field and decreases its health by 2
* **OK** when French chef power kills a minion the minion is removed from its field.
* **OK??** Italian chef Power "Pasta" picks a random minion of his field and increases its attack by 2
* **OK** Italian chef power has no effect when his field is empty. **Kan dette testes?**
* **OK** French chef power has no effect when his field is empty **Kan dette testes?**

### Winner Tests
* The winner is the player that first exceeds a total “attack output” from minion-to-minion attacks of 7
(that is, the sum of attack values of all minion-to-minion attacks performed by that player is greater than 7.)
* 

