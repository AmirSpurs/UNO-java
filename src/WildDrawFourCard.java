/**
 * The WildDrawFourCard class is a subclass of ColorWildCard that implements a type of card in Uno game
 * when a user put this card he/she can choose a color of card that next players should play with
 * and next player should pick up four cards or put WildDrawFour if he/she has one
 * the score of this card is 50 points
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public class WildDrawFourCard extends ColorWildCard {
    /**
     * Instantiates a new Wild draw four card.
     *
     * @param color  the color of card (black at beginning)
     * @param number the number/score of card (50)
     */
    public WildDrawFourCard(int color, int number) {
        super(color, number);
    }


    @Override
    public boolean action(GameManagement manager)
    {
        if (!canPutOverWild(manager))
            return false;
        askAndSetColor(manager);
        manager.setDraw4Points(manager.getDraw4Points() + 4);

        return true;
    }

    /**
     * Checks if it's possible to put over WildDrawFourCard.
     *
     * @param management the class that manages the game
     * @return the boolean if action is doable true and false otherwise
     */
    public boolean canPutOverWild(GameManagement management)
    {
            if (management.getDraw4Points()>0)
                return true ;
            for (Card card: management.getCurrentPlayer().getCards()) {

                if (!(card instanceof WildDrawFourCard) && card.canPutOver(management.getCurrentCard()))
                    return false;
            }
        return true;
    }
    @Override
    public void specificPrint() {
        System.out.print("\u001B[4"+getColor()+"m"+"\u001B[97m" +"   WildDrew4   ");

    }
}

