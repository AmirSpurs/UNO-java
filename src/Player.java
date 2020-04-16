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
//    public ArrayList<Card> getCards() {
//        return cards;
//    }

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
        for (int i = 1;i<=9;i++) {
            for (Card card : cards)
                card.print(i);
            System.out.println("\u001B[0m     ");
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
    public boolean canPlayWithoutWildDraw(Card currentCard)
    {

            for (Card card: cards) {

                if (  !(card instanceof WildDrawFourCard) && (card.canPutOver(currentCard)))
                    return true;
            }

        return false;
    }


    public void addCard (Card cardToAdd)
    {
        if (cardToAdd instanceof ColorWildCard || cardToAdd instanceof WildDrawFourCard)
            cardToAdd.setColor(0);
        cards.add(cardToAdd);
    }
}
