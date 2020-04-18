/**
 * The Card is a abstract class that implements card in Uno game
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public abstract class Card {

    //The color of the card.
    private int color;
    //The score of the card.
    private int number;

    /**
     * Sets color.
     *
     * @param color the color of card
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Instantiates a new Card.
     *
     * @param color  the color
     * @param number the number/score
     */
    protected Card(int color, int number) {
        this.color = color;
        this.number = number;
    }


    /**
     * Gets color.
     *
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * Gets number/score of card.
     *
     * @return the number/score
     */
    public int getNumber() {
        return number;
    }


    /**
     * Sets number.
     *
     * @param number the number/score
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * This method is an abstract method and implements each card action when it's chosen.
     *
     * @param manager the class that manages the game
     * @return the boolean if action is doable true and false otherwise
     * @throws InterruptedException the interrupted exception
     */
    public abstract boolean action(GameManagement manager) throws InterruptedException;

    /**
     * Prints one line of card.
     *
     * @param index integer that gives the line that supposed be printed
     */
    public void print(int index) {
        if (index != 5)
            System.out.print("\u001B[4" + color + "m" + "               ");
        else
            specificPrint();
        System.out.print("\u001B[0m     ");


    }

    /**
     * Prints the whole card.
     */
    public void printWholeCard() {

        for (int i = 1; i <= 9; i++) {
            System.out.print("                                           ");

            print(i);
            System.out.println("\u001B[0m     ");
        }

        System.out.println("\u001B[0m     \n");

    }

    /**
     * Shows that card has been put in the match.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void cardPuttedMessage() throws InterruptedException {
        System.out.println("Card Putted!");
        Thread.sleep(600);
    }

    /**
     * It's an abstract method that Prints the line of card that it's specif .
     */
    public abstract void specificPrint();

    /**
     * It's an abstract method that shows if it's possible to put over card.
     *
     * @param currentCard the current card of game
     * @return a boolean true if it's possible to put over card and false otherwise
     */
    public abstract boolean canPutOver(Card currentCard);


}
