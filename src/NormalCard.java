/**
 * The NormalCard class is a subclass of Card that implements a type of card in Uno game
 * normal cards have a number that equals to their scores.
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public class NormalCard extends Card {


    /**
     * Instantiates a new Normal card.
     *
     * @param color  the color of card
     * @param number the number/score of card
     */
    protected NormalCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager) {
        return canPutOver(manager.getCurrentCard());

    }
    @Override
    public void specificPrint() {
        System.out.print("\u001B[4" + getColor() + "m" + "\u001B[97m" + "       " + getNumber() + "       ");


    }

    @Override
    public boolean canPutOver(Card currentCard) {
        return this.getColor() == currentCard.getColor() || this.getNumber() == currentCard.getNumber();
    }

}
