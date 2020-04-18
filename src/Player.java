import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cards;
    private String name ;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<Card>();
    }

    public Card cardAt (int index)
    {
        return cards.get(index);
    }
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void removeCardAt (int index)
    {
        cards.remove(index);
    }
    public int getCardsNumber()
    {
        return cards.size();
    }
    public String getName() {
        return name;
    }

    public void printAllCards()
    {
        int cardI = 0 ;

        int limit;
        if (cards.size() % 10 ==0)
            limit = cards.size()/10;
        else
            limit = cards.size()/10 + 1;
        for (int j=1;j<= limit;j++)
        {
            for (int i = 1; i <= 9; i++)
            {
                 cardI= (j-1)*10;
                while (cardI< cards.size() && cardI< j*10 ) {

                    cards.get(cardI).print(i);
                    cardI++;
                }

                System.out.println("\u001B[0m     ");
            }
            cardI= (j-1)*10;
            while (cardI< cards.size() && cardI< j*10 )
            {
                if (j==1) {
                    System.out.print("      (" + (cardI + 1) + ")      ");
                    System.out.print("     ");
                }
                else
                {
                    System.out.print("      (" + (cardI + 1) + ")     ");
                    System.out.print("     ");
                }

                cardI++;
            }

            System.out.println("\u001B[0m     \n");

        }
    }
    public boolean hasWildDrewFour()
    {
        for (Card card:cards)
        {
            if ( card instanceof WildDrawFourCard)
                return true;
        }
        return false ;
    }
    public boolean hasDrawTwo()
    {
        for (Card card:cards)
        {
            if ( card instanceof DrawTwoCard)
                return true;
        }
        return false ;
    }
    public boolean canPlaceAnyCard(Card currentCard)
    {
        for (Card card:cards)
        {
            if ( card.canPutOver(currentCard))
                return true;
        }
        return false ;
    }

    public void addCard (Card cardToAdd)
    {
        if (cardToAdd instanceof ColorWildCard || cardToAdd instanceof WildDrawFourCard)
            cardToAdd.setColor(0);
        cards.add(cardToAdd);
    }
    public int totalPoints()
    {
        int sum = 0;
        for (Card card:cards)
                sum+=card.getNumber();
        return sum;

    }
}
