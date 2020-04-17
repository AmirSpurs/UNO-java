import java.io.IOException;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args) throws IOException, InterruptedException {

        Deck deck = new Deck();
        Player player1 = new Player("ali");
        Player player2 = new Computer("amir");
        Player player3 = new Computer("mammad");
        Player [] players = new Player[3];
        players[0] = player1;
        players[1] = player2;
        players[2] = player3;

        GameManagement man = new GameManagement(players,deck);
        man.playGame();
//        NormalCard norm = new NormalCard(1,0);
//        for (int i=1;i<=20;i++)
//        player1.addCard(norm);
//        player1.printAllCards();






    }
}
