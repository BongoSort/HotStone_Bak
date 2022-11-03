package hotstone.framework;

public interface MutableGame extends Game {
    /**
     *  Draws a card from the deck and puts it in the players hand
     *  @param who the player that draws the card
     */
    void drawCard(Player who);

    /**
     * Removes a card(minion) from the field if the card has 0 or less health
     * @param card The minion on the field
     */
    void removeCardFromFieldIfHealthIsZeroOrBelow(Card card);
}
