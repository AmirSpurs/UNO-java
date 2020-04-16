import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Deck {
    private ArrayList<Card> cards;
    private Card currentCard;

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void addCard (Card cardToAdd)
    {
        cards.add(cardToAdd);
    }

    public Deck() {
        cards = new ArrayList<Card>();
        for (int color=1;color<=4;color++)
        {

            cards.add(new NormalCard(color,0));
            cards.add(new WildDrawFourCard(0,50));
            cards.add(new ColorWildCard(0,50));
            for (int i=1;i<=2;i++)
            {
                for (int number=1;number<10;number++)
                    cards.add(new NormalCard(color,number));
                cards.add(new SkipCard(color,20));
                cards.add(new ReverseCard(color,20));
                cards.add(new DrawTwoCard(color,20));
            }
        }
        Collections.shuffle(cards);
        Random topCard = new Random();
        int index = 0;

        do {
            index = topCard.nextInt(108);
        } while ( cards.get(index) instanceof WildDrawFourCard || cards.get(index) instanceof ColorWildCard); //recheck for sure
        currentCard = cards.get(index);
        cards.remove(index);

    }
    public void giveAwayCard(Player playerToGive)
    {
        Random randCard = new Random();
        int index = randCard.nextInt(cards.size());
        playerToGive.addCard(cards.get(index));
        cards.remove(index);
    }
    public void handOutCard (Player playerToGive)
    {
        Collections.shuffle(cards);
        for (int i=0;i<7;i++)
            playerToGive.addCard(cards.get(i));
        Iterator itr = cards.iterator();
        int i = 1;
        while (itr.hasNext())
        {
            itr.next();
            if (i<=7)
                itr.remove();

            i++;
        }
    }
    public void printCurrentCard()
    {


        for (int i = 1;i<=9;i++) {
            System.out.print("                                           ");

            currentCard.print(i);
            System.out.println("\u001B[0m     ");
        }

            System.out.println("\u001B[0m     \n");

    }

}


