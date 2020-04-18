/**
 * The DrawTwoCard class is a subclass of Card that implements a type of card in Uno game
 * when a user put this card next player should pick up two cards or put DrawTwo if he/she has one
 * the score of this card is 20 points
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public class DrawTwoCard extends Card {

    /**
     * Instantiates a new Draw two card.
     *
     * @param color  the color of card
     * @param number the number/score of card (20)
     */
    protected DrawTwoCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager) {
        if (!canPutOver(manager.getCurrentCard()))
            return false;
        manager.setDraw2Points(manager.getDraw2Points() + 2);
        return true;
    }

    @Override
    public void specificPrint() {
        System.out.print("\u001B[4" + getColor() + "m" + "\u001B[97m" + "     Drew2     ");

    }

    @Override
    public boolean canPutOver(Card currentCard) {

        return (this.getColor() == currentCard.getColor() || currentCard instanceof DrawTwoCard);
    }
}

