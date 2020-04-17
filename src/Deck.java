import java.util.*;

public class Deck {
    private ArrayList<Card> cards;

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




    }
    public Card putFirstCard()
    {
        Random topCard = new Random();
        int index = 0;
        do {
            index = topCard.nextInt(108);
        } while ( cards.get(index) instanceof WildDrawFourCard || cards.get(index) instanceof ColorWildCard); //recheck for sure
        Card currentCard = cards.get(index);
        cards.remove(index);
        return currentCard ;

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


}


