import java.util.ArrayList;

/**
 * The Player class implements players of the game.
 */
public class Player {
    //The player cards.
    private ArrayList<Card> cards;
    //The player name.
    private String name;

    /**
     * Instantiates a new Player.
     *
     * @param name the name of player
     */
    public Player(String name) {
        this.name = name;
        cards = new ArrayList<Card>();
    }

    /**
     * Returns the card at given index
     *
     * @param index the index of card
     * @return the card
     */
    public Card cardAt(int index) {
        return cards.get(index);
    }

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Remove card at given index.
     *
     * @param index the index
     */
    public void removeCardAt(int index) {
        cards.remove(index);
    }

    /**
     * Gets number of cards.
     *
     * @return the cards number
     */
    public int getCardsNumber() {
        return cards.size();
    }

    /**
     * Gets name of player.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Print all cards of player.
     */
    public void printAllCards() {
        int cardI = 0;

        //the maximum number of card to put in one line is 10.
        int limit;
        if (cards.size() % 10 == 0)
            limit = cards.size() / 10;
        else
            limit = cards.size() / 10 + 1;
        for (int j = 1; j <= limit; j++) {
            for (int i = 1; i <= 9; i++) {
                cardI = (j - 1) * 10;
                while (cardI < cards.size() && cardI < j * 10) {

                    cards.get(cardI).print(i);
                    cardI++;
                }

                System.out.println("\u001B[0m     ");
            }
            cardI = (j - 1) * 10;
            while (cardI < cards.size() && cardI < j * 10) {
                if (j == 1) {
                    System.out.print("      (" + (cardI + 1) + ")      ");
                    System.out.print("     ");
                } else {
                    System.out.print("      (" + (cardI + 1) + ")     ");
                    System.out.print("     ");
                }

                cardI++;
            }
            System.out.println("\u001B[0m     \n");
        }
    }

    /**
     * Checks if player has any wildDrewFourCard.
     *
     * @return the boolean true if he/she has and false otherwise
     */
    public boolean hasWildDrewFour() {
        for (Card card : cards) {
            if (card instanceof WildDrawFourCard)
                return true;
        }
        return false;
    }

    /**
     * Checks if player has any DrewTwoCard.
     *
     * @return the boolean true if he/she has and false otherwise
     */
    public boolean hasDrawTwo() {
        for (Card card : cards) {
            if (card instanceof DrawTwoCard)
                return true;
        }
        return false;
    }

    /**
     * Checks if he can play with any of his/her cards
     *
     * @param currentCard the current card at table
     * @return the boolean true if he/she can false otherwise
     */
    public boolean canPlaceAnyCard(Card currentCard) {
        for (Card card : cards) {
            if (card.canPutOver(currentCard))
                return true;
        }
        return false;
    }

    /**
     * Add card to player cards. (when he/she should pick up card from deck)
     *
     * @param cardToAdd the card to add
     */
    public void addCard(Card cardToAdd) {
        //sets the color of wild cards to black again.
        if (cardToAdd instanceof ColorWildCard || cardToAdd instanceof WildDrawFourCard)
            cardToAdd.setColor(0);
        cards.add(cardToAdd);
    }

    /**
     * Total points int.
     *
     * @return the int
     */
    public int totalPoints() {
        int sum = 0;
        for (Card card : cards)
            sum += card.getNumber();
        return sum;

    }
}
