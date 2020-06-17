import java.io.IOException;
import java.util.Scanner;

/**
 * The GameManagement class manages the uno game
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public class GameManagement {
    //The players of the game.
    private Player[] players;
    //The current player.
    private Player currentPlayer;
    //The current card on table.
    private Card currentCard;
    //The direction of the game.
    private int direction;
    //The index of the player who should play.
    private int turn;
    //The deck of the game
    private Deck deck;
    //Shows how many cards should player draw as penalty of WildDraw cards.
    private int draw4Points;
    //Shows how many cards should player draw as penalty of DrawTwo cards
    private int draw2Points;

    /**
     * Instantiates a new Game management.
     *
     * @param players the array of players
     * @param deck    the deck
     */
    public GameManagement(Player[] players, Deck deck) {
        this.players = players;
        this.deck = deck;
        direction = 1;
        draw4Points = 0;
        draw2Points = 0;
        turn = -1;
        currentPlayer = null;
    }


    private void startGame() throws InterruptedException {
        currentCard = deck.putFirstCard();
        for (Player player : players)
            deck.handOutCard(player);
        System.out.print("\033[H\033[2J");
        currentCard.printWholeCard();
        Thread.sleep(500);
        currentCard.action(this);
    }

    /**
     * Plays the game.
     *
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public void playGame() throws IOException, InterruptedException {
        startGame();
        Scanner input = new Scanner(System.in);
        while (!endGame()) {
            nextPlayer();
            showGameInfo();
            if (draw2Points > 0)
                manageDrawTwoCards();
            else if (draw4Points > 0)
                manageWildDrawFourCard();
            else {
                if (!currentPlayer.canPlaceAnyCard(currentCard)) {
                    System.out.println("Can't place any card!");
                    if (currentPlayer instanceof Computer)
                        Thread.sleep(1000);
                    else {
                        System.out.println("press any key to draw card from deck");
                        System.in.read();
                    }
                    System.out.println("Card Picked up");
                    Thread.sleep(800);
                    deck.giveAwayCard(currentPlayer);
                    showGameInfo();
                    Thread.sleep(800);
                }
                if (currentPlayer.canPlaceAnyCard(currentCard)) {
                    String userInput;
                    do {
                        showGameInfo();
                        System.out.println(currentPlayer.getName() + " choose one of your cards");
                        if (currentPlayer instanceof Computer) {
                            userInput = ((Computer) currentPlayer).chooseCard(this);
                            Thread.sleep(1000);
                        } else
                            userInput = input.next();

                    } while ((!endGame()) && !placeCard(currentPlayer, userInput));

                }
            }
        }
    }

    //Manages when previous player has put DrawTwoCards
    private void manageDrawTwoCards() throws IOException, InterruptedException {
        if (currentPlayer.hasDrawTwo()) {
            Scanner input = new Scanner(System.in);
            String userInput;
            System.out.println(currentPlayer.getName() + " you Can choose one of your draw cards enter Any other Key to pick up " + draw2Points + " cards");
            if (currentPlayer instanceof Computer) {
                userInput = ((Computer) currentPlayer).chooseDrawCard(false);
                Thread.sleep(1600);
            } else
                userInput = input.next();
            if (!placeCard(currentPlayer, userInput)) {
                penalty(draw2Points);
                draw2Points = 0;
            }

        } else {
            penalty(draw2Points);
            draw2Points = 0;
        }
    }

    //Manages when previous player has put WildDrawFourCard
    private void manageWildDrawFourCard() throws IOException, InterruptedException {
        if (currentPlayer.hasWildDrewFour()) {
            Scanner input = new Scanner(System.in);
            String userInput;
            System.out.println(currentPlayer.getName() + " you Can choose one of your draw cards enter Any other Key to pick up " + draw4Points + " cards");
            if (currentPlayer instanceof Computer) {
                userInput = ((Computer) currentPlayer).chooseDrawCard(true);
                Thread.sleep(1700);
            } else
                userInput = input.next();
            if (!placeCard(currentPlayer, userInput)) {
                penalty(draw4Points);
                draw4Points = 0;
            }
        } else {
            penalty(draw4Points);
            draw4Points = 0;
        }
    }

    //Punishes the player and they should draw cards
    private void penalty(int drawPoints) throws IOException, InterruptedException {
        System.out.println(currentPlayer.getName() + " should pick up " + drawPoints + " cards!");
        if (currentPlayer instanceof Computer)
            Thread.sleep(1400);
        else {
            System.out.println("Press Any Key To Continue");
            System.in.read();

        }
        for (int i = 1; i <= drawPoints; i++)
            deck.giveAwayCard(currentPlayer);
        showGameInfo();
        Thread.sleep(800);
    }

    //Places card in table if the input is valid
    private boolean placeCard(Player playerToPlace, String userInput) throws InterruptedException {
        int cardIndex;
        try {
            cardIndex = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input!");
            Thread.sleep(500);

            return false;
        }
        cardIndex--;
        if (draw2Points > 0 && !(playerToPlace.cardAt(cardIndex) instanceof DrawTwoCard))
            return false;
        if (draw4Points > 0 && !(playerToPlace.cardAt(cardIndex) instanceof WildDrawFourCard))
            return false;
        if (cardIndex >= playerToPlace.getCardsNumber() || !(playerToPlace.cardAt(cardIndex).action(this))) {
            System.out.println("Invalid Input!");
            Thread.sleep(500);
            return false;
        }
        deck.addCard(currentCard);
        currentCard = (playerToPlace.cardAt(cardIndex));
        playerToPlace.removeCardAt(cardIndex);
        showGameInfo();
        currentCard.cardPuttedMessage();
        if (playerToPlace instanceof Computer)
            Thread.sleep(1000);
        return true;
    }

    /**
     * Gives the turn to Next player.
     */
    public void nextPlayer() {
        if (turn + direction < 0)
            turn = players.length;
        turn = (turn + direction) % players.length;
        currentPlayer = players[turn];
    }

    /**
     * Shows game info.
     */
    public void showGameInfo() {
        System.out.print("\033[H\033[2J");
        for (Player player : players)
            System.out.println(player.getName() + " : " + player.getCardsNumber() + " cards");
        if (direction == -1)
            System.out.println("Anti clockWise");
        else
            System.out.println("ClockWise");
        currentCard.printWholeCard();
        int i =0 ;
        Player playerToPrint = null;
        for (Player player:players) {
            if (!(player instanceof Computer))
            {
                playerToPrint = player;
                i++;
            }
        }
        if (i==1 && playerToPrint!=currentPlayer)
            playerToPrint.printAllCards();
        System.out.println(currentPlayer.getName() + "'s Turn\n");

        if (!(currentPlayer instanceof Computer))
            currentPlayer.printAllCards();

    }

    //return true if the game is finished and we have a winner!
    private boolean endGame() throws InterruptedException {
        for (Player player : players) {
            if (player.getCardsNumber() == 0) {
                System.out.println(player.getName() + " Is The Winner !");
                Thread.sleep(1000);
                ranking();
                return true;
            }
        }
        return false;

    }

    /**
     * Gets current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets current card.
     *
     * @return the current card
     */
    public Card getCurrentCard() {
        return currentCard;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Gets draw 4 points.
     *
     * @return the draw 4 points
     */
    public int getDraw4Points() {
        return draw4Points;
    }

    /**
     * Sets draw4points.
     *
     * @param draw4Points the draw4points
     */
    public void setDraw4Points(int draw4Points) {
        this.draw4Points = draw4Points;
    }

    /**
     * Gets draw2points.
     *
     * @return the draw2points
     */
    public int getDraw2Points() {
        return draw2Points;
    }

    /**
     * Sets draw2points.
     *
     * @param draw2Points the draw2points
     */

    public void setDraw2Points(int draw2Points) {
        this.draw2Points = draw2Points;
    }

    //Ranks the players and prints the ranking.
    private void ranking() {
        System.out.print("\033[H\033[2J");
        sort();
        int i = 0;
        for (Player player : players) {
            i++;
            System.out.println("                   " + i + ") " + player.getName() + " : " + player.totalPoints() + " points");
        }

    }

    //Sorts and rank the players according to their points.
    private void sort() {
        for (int i = 0; i < players.length; i++)
            for (int j = 0; j < players.length - 1; j++)
                if (players[j].totalPoints() > players[j + 1].totalPoints() ||
                        (players[j].totalPoints() == players[j + 1].totalPoints() && players[j].getCardsNumber() > players[j + 1].getCardsNumber())) {
                    Player temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }
    }
}
