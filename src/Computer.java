/**
 * The Computer class is a subclass of Player class that implements an AI
 * to play the game with user in single player mode.
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public class Computer extends Player {
    /**
     * Instantiates a new Computer.
     *
     * @param name the name that it's basically COM with a number
     */
    public Computer(String name) {
        super(name);
    }

    /**
     * Chooses a card it basically iterate through player's card and chooses
     * the first card that it's putable.
     *
     * @param manager the class that manages the game
     * @return index of chosen card as a string
     */
    public String chooseCard(GameManagement manager) {
        for (int i = 0; i < getCardsNumber(); i++) {
            if (cardAt(i) instanceof WildDrawFourCard) {
                if (((WildDrawFourCard) cardAt(i)).canPutOverWild(manager))
                    return (Integer.toString(i + 1));
            } else if (cardAt(i).canPutOver(manager.getCurrentCard()))
                return (Integer.toString(i + 1));
        }
        return null;
    }

    /**
     * Chooses a wild or normal draw card when the previous player has put one.
     *
     * @param wildOrColor if it's true previous player has put wild draw and if it's false player has put normal draw
     * @return the string
     */
    public String chooseDrawCard(boolean wildOrColor) {
        for (int i = 0; i < getCardsNumber(); i++) {
            if ((wildOrColor && cardAt(i) instanceof WildDrawFourCard) || (!wildOrColor && cardAt(i) instanceof DrawTwoCard))
                return (Integer.toString(i + 1));

        }
        return null;
    }
}

