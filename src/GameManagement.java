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
        turn = 1;
    }


    public void startGame()
    {
        for (Player player:players)
            deck.handOutCard(player);
        deck.getCurrentCard().action(this,deck.getCurrentCard());
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
        cardIndex--;
        if (cardIndex>playerToPlace.getCardsNumber() || playerToPlace.CanplayWithoutWildDraw(cardIndex)  ||
                playerToPlace.cardAt(cardIndex).action(this,deck.getCurrentCard())  )
        {
            System.out.println("Invalid Input!");
            return false;
        }
        deck.addCard(deck.getCurrentCard());
        deck.setCurrentCard(playerToPlace.cardAt(cardIndex));
        playerToPlace.removeCardAt(cardIndex);
        turn = nextPlayer();

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
