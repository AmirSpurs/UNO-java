import java.util.Random;
import java.util.Scanner;

/**
 * The ColorWildCard class is a subclass of Card that implements a type of card in Uno game
 * when a user put this card he/she can choose a color of card that next player should play with.
 * the score of this card is 50 points
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public class ColorWildCard extends Card {


    /**
     * Instantiates a new Color wild card.
     *
     * @param color  the color of card (black at beginning)
     * @param number the number/score of card (50)
     */
    public ColorWildCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager) {
        if (!canPutOver(manager.getCurrentCard()))
            return false;
        askAndSetColor(manager);
        return true;
    }

    @Override
    public void specificPrint() {
        System.out.print("\u001B[4" + getColor() + "m" + "\u001B[97m" + "   ColorWild   ");


    }

    /**
     * Ask user a color and set it the color of card.
     * the default color of the card is black and when the user choose a color it will be colored
     *
     * @param manager the class that manages the game
     */
    public void askAndSetColor(GameManagement manager) {
        int colorNo;
        if (manager.getCurrentPlayer() instanceof Computer) {
            Random randColor = new Random();
            colorNo = randColor.nextInt(4) + 1;
        } else {
            Scanner input = new Scanner(System.in);

            while (true) {
                System.out.println("choose one of color\n1)Red\n2)Green\n3)Yellow\n4)Blue");
                String userInput = input.next();
                try {
                    colorNo = Integer.parseInt(userInput);
                    if (colorNo > 0 && colorNo < 5)
                        break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input!");
                    manager.showGameInfo();
                }
            }
        }
        this.setColor(colorNo);

    }

    @Override
    public boolean canPutOver(Card currentCard) {
        return true;

    }
}
