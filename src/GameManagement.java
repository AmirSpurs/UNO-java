import java.io.IOException;
import java.util.Scanner;

public class GameManagement {
    private Player [] players ;
    private int direction;
    private int turn  ;
    private Deck deck;
    private int draw4Points ;
    private int draw2Points ;

    public GameManagement(Player[] players, Deck deck) {
        this.players = players;
        this.deck = deck;
        direction = 1 ;
        draw4Points = 0;
        draw2Points = 0;
        turn = -1;
    }


    public void startGame() throws InterruptedException {
        for (Player player:players)
            deck.handOutCard(player);
        deck.getCurrentCard().action(this,deck.getCurrentCard());
    }
    public void playGame() throws IOException, InterruptedException {
        startGame();
        Scanner input = new Scanner(System.in);
        while (!endGame()) {
            turn = nextPlayer();
            showGameInfo();

            if (draw2Points > 0) {

                manageDrawTwoCards();

            }
            else if (draw4Points > 0)
            {
                manageWildDrawFourCard();

            }
//            if (endGame())
//                break;

            else {
                if (!players[turn].canPlaceAnyCard(deck.getCurrentCard())) {
                    System.out.println("Can't place any card!");
                    System.out.println("press any key to draw card from deck");
                    System.in.read();
                    Thread.sleep(700);
                    deck.giveAwayCard(players[turn]);
                }
//                if (endGame())
//                    break;
                if (players[turn].canPlaceAnyCard(deck.getCurrentCard())) {
                    String userInput;

                    do {
                        showGameInfo();
                        System.out.println("choose one of your cards");
                        userInput = input.next();
                    } while (( !endGame()) && !placeCard(players[turn], userInput));
//                    if (endGame())
//                        break;
                }
            }

        }

        }


    public void manageDrawTwoCards() throws IOException, InterruptedException {
        if (players[turn].hasDrawTwo())
        {
            Scanner input = new Scanner(System.in);
            String userInput;
            System.out.println("You Can choose one of your draw cards enter Any other Key to pick up "+draw2Points+" Cards");
            userInput = input.next();
            if (!placeCard(players[turn],userInput))
            {
                penalty(draw2Points);
                draw2Points = 0;
            }

        }
        else {
            penalty(draw2Points);
            draw2Points = 0;
        }
    }
    public void manageWildDrawFourCard() throws IOException, InterruptedException {

        if (players[turn].hasWildDrewFour())
        {
            Scanner input = new Scanner(System.in);
            String userInput;
            System.out.println("You Can choose one of your draw cards enter Any other Key to pick up "+draw4Points+" Cards");
            userInput = input.next();
            if (!placeCard(players[turn],userInput))
            {
                penalty(draw4Points);
                draw4Points = 0;
            }

        }
        else {
            penalty(draw4Points);
            draw4Points = 0;
        }

    }
    public void penalty (int drawPoints) throws IOException, InterruptedException {
        System.out.println("You should pick up "+drawPoints+" cards\nPress Any Key To Continue");
        System.in.read();
        for (int i=1;i<=drawPoints;i++)
            deck.giveAwayCard(players[turn]);
        showGameInfo();
        Thread.sleep(600);
    }
    public boolean placeCard(Player playerToPlace,String userInput) throws InterruptedException {
        int cardIndex ;
        try {
            cardIndex = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input!");
            Thread.sleep(500);

            return false;
        }
        cardIndex--;
        if ( draw2Points>0 && !( playerToPlace.cardAt(cardIndex) instanceof DrawTwoCard))
            return false;
        if (draw4Points>0 && !( playerToPlace.cardAt(cardIndex) instanceof WildDrawFourCard))
            return false;

        if (cardIndex>playerToPlace.getCardsNumber() || ( draw4Points==0 && playerToPlace.CanplayWithoutWildDraw(cardIndex,deck.getCurrentCard()))  ||
                !(playerToPlace.cardAt(cardIndex).action(this,deck.getCurrentCard()))  )
        {
            System.out.println("Invalid Input!");
            Thread.sleep(500);

            return false;
        }
        deck.addCard(deck.getCurrentCard());
        deck.setCurrentCard(playerToPlace.cardAt(cardIndex));
        playerToPlace.removeCardAt(cardIndex);
        Thread.sleep(300);
        return true;
    }
    public int nextPlayer()
    {
        if (turn+direction<0)
            turn = players.length;
        return ((turn+direction) % players.length);
    }

    public void showGameInfo()
    {
        System.out.print("\033[H\033[2J");
        for (Player player: players)
            System.out.println(player.getName()+" : "+player.getCardsNumber()+" cards");
        if (direction==-1)
            System.out.println("Anti clockWise");
        else
            System.out.println("ClockWise");
        deck.printCurrentCard();
        System.out.println(players[turn].getName() + "'s Turn\n");
        players[turn].printAllCards();

    }
    public boolean endGame() {
        for (Player player : players) {
            if (player.getCardsNumber() == 0)
                return true;
        }
        return false;

    }
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getDraw4Points() {
        return draw4Points;
    }

    public void setDraw4Points(int draw4Points) {
        this.draw4Points = draw4Points;
    }

    public int getDraw2Points() {
        return draw2Points;
    }

    public void setDraw2Points(int draw2Points) {
        this.draw2Points = draw2Points;
    }
}
