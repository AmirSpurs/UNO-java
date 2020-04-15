import java.util.*;

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
        for (int color=0;color<4;color++)
        {
            cards.add(new NormalCard(color,0));
            cards.add(new WildDrawFourCard());
            cards.add(new ColorWildCard());
            for (int i=1;i<=2;i++)
            {
                for (int number=1;number<10;number++)
                    cards.add(new NormalCard(color,number));
                cards.add(new SkipCard(color));
                cards.add(new ReverseCard(color));
                cards.add(new DrawTwoCard(color));
            }
        }
        Collections.shuffle(cards);
        Random topCard = new Random();
        int index = 0;

        do {
            index = topCard.nextInt(108);
        } while ( cards.get(index) instanceof WildCard); //recheck for sure
        currentCard = cards.get(index);
        cards.remove(index);
        
    }
    public void giveAwayCard(Player playerToGive)
    {
        playerToGive.addCard(cards.get(0));
        cards.remove(0);
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
            if (i<=7)
                itr.remove();
            i++;
        }
    }

}


