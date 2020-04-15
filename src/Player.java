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

    }
    public boolean canPlaceAnyCard()
    {
        return true;
    }
    public boolean CanplayWithoutWildDraw(int cardIndex)
    {
        return true;

    }


    public void addCard (Card cardToAdd)
    {
        cards.add(cardToAdd);
    }
}
