import java.io.IOException;
import java.util.*;

public class Run {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner input = new Scanner(System.in);

        Deck deck = new Deck();

        System.out.print("\033[H\033[2J");
        System.out.println("Main Menu\n1)Single Player\n2)MultiPlayer\n3)Exit");
        int mainMenu = (int) input.nextLine().charAt(0) - 48;
        if (mainMenu != 2 && mainMenu != 1)
            return;
        System.out.print("\033[H\033[2J");
        System.out.println("1)3 players\n2)4 players\n3)5 players");
        int subMenu = (int) input.nextLine().charAt(0) - 48;
        if (subMenu != 2 && subMenu != 1 && subMenu!=3)
            return;
        System.out.print("\033[H\033[2J");
        Player [] players = new Player[subMenu+2];
        System.out.print("Enter your name :");
        players[0] = new Player(input.nextLine());
        if (mainMenu==2)
                for (int i=1;i<subMenu+2;i++) {
                    System.out.print("Enter your name :");
                    players[i] = new Player(input.nextLine());
                }
        else
            for (int i=1;i<subMenu+2;i++)
                players[i] = new Computer("COM"+i);

        Collections.shuffle(Arrays.asList(players));
        GameManagement man = new GameManagement(players,deck);
        man.playGame();

    }
}
