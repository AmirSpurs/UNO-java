import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameManagement {
    private Player [] players ;
    private Player currentPlayer ;
    private Card currentCard ;
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
        currentPlayer = null;
    }


    public void startGame() throws InterruptedException {
        currentCard = deck.putFirstCard();
        for (Player player:players)
            deck.handOutCard(player);
        System.out.print("\033[H\033[2J");
        currentCard.printWholeCard();
        Thread.sleep(500);
       currentCard.action(this);
    }
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
                    else
                    {
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
                        //here we should create method
                        System.out.println(currentPlayer.getName()+" choose one of your cards");
                        if (currentPlayer instanceof Computer) {
                            userInput = ((Computer) currentPlayer).chooseCard(this);
                            Thread.sleep(1000);
                        }
                        else
                            userInput = input.next();

                        } while (( !endGame()) && !placeCard(currentPlayer, userInput));

                }
            }
        }

        }


    public void manageDrawTwoCards() throws IOException, InterruptedException {
        if (currentPlayer.hasDrawTwo())
        {
            Scanner input = new Scanner(System.in);
            String userInput;
            System.out.println(currentPlayer.getName()+" you Can choose one of your draw cards enter Any other Key to pick up "+draw2Points+" cards");
            if (currentPlayer instanceof Computer) {
                userInput = ((Computer) currentPlayer).chooseDrawCard(false);
                Thread.sleep(1600);
            }
            else
                userInput = input.next();
            if (!placeCard(currentPlayer,userInput))
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

        if (currentPlayer.hasWildDrewFour())
        {
            Scanner input = new Scanner(System.in);
            String userInput;
            System.out.println(currentPlayer.getName()+" you Can choose one of your draw cards enter Any other Key to pick up "+draw4Points+" cards");
            if (currentPlayer instanceof Computer) {
                userInput = ((Computer) currentPlayer).chooseDrawCard(true);
                Thread.sleep(1700);
            }
            else
             userInput = input.next();
            if (!placeCard(currentPlayer,userInput))
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
        System.out.println(currentPlayer.getName()+" should pick up "+drawPoints+" cards!");
        if (currentPlayer instanceof Computer)
            Thread.sleep(1400);
        else
        {
            System.out.println("Press Any Key To Continue");
            System.in.read();

        }
        for (int i=1;i<=drawPoints;i++)
            deck.giveAwayCard(currentPlayer);
        showGameInfo();
        Thread.sleep(800);
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


        if (cardIndex>=playerToPlace.getCardsNumber()  || !(playerToPlace.cardAt(cardIndex).action(this))  )
        {
            System.out.println("Invalid Input!");
            Thread.sleep(500);
            return false;
        }
        deck.addCard(currentCard);
        currentCard = ( playerToPlace.cardAt(cardIndex));
        playerToPlace.removeCardAt(cardIndex);
        showGameInfo();
        //System.out.print("\033[H\033[2J");
        //currentCard.printWholeCard();
        currentCard.cardPuttedMessage();
        if (playerToPlace instanceof Computer)
            Thread.sleep(1000);
        return true;
    }
    public void nextPlayer()
    {
        if (turn+direction<0)
            turn = players.length;
        turn = (turn+direction) % players.length ;
        currentPlayer = players[turn];
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
        currentCard.printWholeCard();
        System.out.println(currentPlayer.getName() + "'s Turn\n");
        if (!(currentPlayer instanceof Computer))
        currentPlayer.printAllCards();

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public boolean endGame() throws InterruptedException {
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

    public void ranking()
    {
        System.out.print("\033[H\033[2J");
        sort();
        int i =0;
        for (Player player : players)
        {
            i++;
            System.out.println("                   "+i+") "+player.getName()+" : "+player.totalPoints()+" points");
        }

    }
    private void sort()
    {
        for (int i=0;i<players.length;i++)
            for (int j=0;j<players.length-1;j++)
                if (players[j].totalPoints() > players[j+1].totalPoints() ||
                        ( players[j].totalPoints() == players[j+1].totalPoints() && players[j].getCardsNumber() > players[j+1].getCardsNumber() ))
                {
                    Player temp = players[j];
                    players[j]=players[j+1];
                    players[j+1] = temp;
                }
    }
}
