import java.io.IOException;
import java.util.Scanner;

public class GameManagement {
    private Player [] players ;
    private int direction;
    private int turn  ;
    private Deck deck;
    private int drawPoint ;

    public GameManagement(Player[] players, Deck deck) {
        this.players = players;
        this.deck = deck;
        direction = 1 ;
        drawPoint = 0;
        turn = 1;
    }


    public void startGame()
    {
        for (Player player:players)
            deck.handOutCard(player);
        deck.getCurrentCard().action(this,players[nextPlayer()],deck.getCurrentCard());
    }
    public void playGame() throws IOException {
        Scanner input = new Scanner(System.in);
        while (!endGame())
        {
            deck.getCurrentCard().print();
            if (!players[turn].canPlaceAnyCard())
            {
                players[turn].printAllCards();
                System.out.println(players[turn].getName() + "'s Turn");
                System.out.println("Can't place any card!");
                System.out.println("press any key to draw card from deck");
                System.in.read();
                deck.giveAwayCard(players[turn]);
                players[turn].printAllCards();

            }
            if (players[turn].canPlaceAnyCard())
            {
                String userInput;
                do {
                    System.out.println(players[turn].getName() + "'s Turn");
                    System.out.println("choose one of your cards");
                    players[turn].printAllCards();
                    userInput = input.next();
                } while (!placeCard(players[turn],userInput));
            }

        }
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
        if (cardIndex>playerToPlace.getCardsNumber() ||playerToPlace.placeCard(cardIndex,players[nextPlayer()],deck.getCurrentCard(),this,deck) ) {
            System.out.println("Invalid Input!");
            return false;
        }
        return true;
    }
    public int nextPlayer()
    {
        return ((turn+direction)%players.length);
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

    public int getDrawPoint() {
        return drawPoint;
    }

    public void setDrawPoint(int drawPoint) {
        this.drawPoint = drawPoint;
    }

}
