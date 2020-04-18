/**
 * The ReverseCard class is a subclass of Card that implements a type of card in Uno game
 * when a user put this card the direction of the game will be reversed (E.g. clockwise to anti clockwise)
 * the score of this card is 20 points
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public class ReverseCard extends Card {

    /**
     * Instantiates a new Reverse card.
     * @param color  the color of card
     * @param number the number/score of card (20)
     */
    protected ReverseCard(int color, int number) {
        super(color, number);
    }


    @Override
    public boolean action(GameManagement manager) throws InterruptedException {
        if (!canPutOver(manager.getCurrentCard()))
            return false;
        manager.setDirection(-1*manager.getDirection());
        System.out.println("Direction Reversed!");
        return true;
    }
    @Override
    public void cardPuttedMessage() throws InterruptedException {
        System.out.println("Card Putted!");
        System.out.println("Direction Reversed!");
        Thread.sleep(600);

    }
    @Override
    public void specificPrint() {
        System.out.print("\u001B[4"+getColor()+"m"+"\u001B[97m" +"    Reverse    ");

    }


    @Override
    public boolean canPutOver(Card currentCard) {
        return  (this.getColor()==currentCard.getColor() || currentCard instanceof ReverseCard ) ;

    }
}
