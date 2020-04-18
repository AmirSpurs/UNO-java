/**
 * The SkipCard class is a subclass of Card that implements a type of card in Uno game
 * when a user put this card next player will miss a turn.
 * the score of this card is 20 points
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public class SkipCard extends Card {

    /**
     * Instantiates a new Skip card.
     *
     * @param color  the color of card
     * @param number the number/score of card (20)
     */
    public SkipCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager) throws InterruptedException {
        if (!canPutOver(manager.getCurrentCard()))
            return false;
        manager.nextPlayer();
        return true;
    }

    @Override
    public void cardPuttedMessage() throws InterruptedException {
        System.out.println("Card Putted!");
        System.out.println("Next player misses a turn!");
        Thread.sleep(600);
    }

    @Override
    public void specificPrint() {
        System.out.print("\u001B[4" + getColor() + "m" + "\u001B[97m" + "      Skip     ");

    }


    @Override
    public boolean canPutOver(Card currentCard) {

        return (this.getColor() == currentCard.getColor() || currentCard instanceof SkipCard);

    }


}
