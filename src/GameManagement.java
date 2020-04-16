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
        turn = 0;
    }


    public void startGame()
    {
        for (Player player:players)
            deck.handOutCard(player);
        deck.getCurrentCard().action(this,deck.getCurrentCard());
    }
    public void playGame() throws IOException {
        startGame();
        Scanner input = new Scanner(System.in);
        while (!endGame()) {

            deck.printCurrentCard();

            System.out.println(players[turn].getName() + "'s Turn");

            if (draw2Points > 0)
                manageDrawTwoCards();
            else if (draw4Points > 0)
            {
                System.out.println(12);
                manageWildDrawFourCard();

            }

            else {
                if (!players[turn].canPlaceAnyCard(deck.getCurrentCard())) {
                    players[turn].printAllCards();
                    System.out.println("Can't place any card!");
                    System.out.println("press any key to draw card from deck");
                    System.in.read();
                    deck.giveAwayCard(players[turn]);
                    players[turn].printAllCards();

                }

                if (players[turn].canPlaceAnyCard(deck.getCurrentCard())) {
                    String userInput;

                    do {
                        System.out.println("choose one of your cards");
                        players[turn].printAllCards();
                        userInput = input.next();
                    } while (!placeCard(players[turn], userInput));
                }
            }
            turn = nextPlayer();
        }

        }


    public void manageDrawTwoCards() throws IOException {
        players[turn] .printAllCards();
        if (players[turn].hasDrawTwo())
        {
            Scanner input = new Scanner(System.in);
            String userInput;
            System.out.println("You Can choose one of your draw cards enter Any other Key to pick up "+draw2Points+" Cards");
            players[turn].printAllCards();
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
    public void manageWildDrawFourCard() throws IOException {
        players[turn] .printAllCards();

        if (players[turn].hasWildDrewFour())
        {
            Scanner input = new Scanner(System.in);
            String userInput;
            System.out.println("You Can choose one of your draw cards enter Any other Key to pick up "+draw4Points+" Cards");
            players[turn].printAllCards();
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
    public void penalty (int drawPoints) throws IOException {
        System.out.println("You should pick up "+drawPoints+" cards\nPress Any Key To Continue");
        System.in.read();
        for (int i=1;i<=drawPoints;i++)
            deck.giveAwayCard(players[turn]);
    }
    public boolean placeCard(Player playerToPlace,String userInput)
    {
        int cardIndex ;
        try {
            cardIndex = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input!");
            return false;
        }
        cardIndex--;
        if ( draw2Points>0 && !( playerToPlace.cardAt(cardIndex) instanceof DrawTwoCard))
            return false;
        if (draw4Points>0 && !( playerToPlace.cardAt(cardIndex) instanceof WildDrawFourCard))
            return false;

        if (cardIndex>playerToPlace.getCardsNumber() || (playerToPlace.CanplayWithoutWildDraw(cardIndex,deck.getCurrentCard()))  ||
                !(playerToPlace.cardAt(cardIndex).action(this,deck.getCurrentCard()))  )
        {
            System.out.println("Invalid Input!");
            return false;
        }
        deck.addCard(deck.getCurrentCard());
        deck.setCurrentCard(playerToPlace.cardAt(cardIndex));
        playerToPlace.removeCardAt(cardIndex);
        return true;
    }
    public int nextPlayer()
    {
        if (turn+direction<0)
            turn = players.length;
        return ((turn+direction) % players.length);
    }


    public boolean endGame(){return false;}
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
