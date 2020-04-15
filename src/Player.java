import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cards;
    private String name ;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<Card>();
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

    }
    public boolean placeCard(int cardIndex,Player nextPlayer,Card deckCard,GameManagement manager,Deck deck)
    {
        //here we'll write the code to handle super fucking card blah blah
        cards.get(cardIndex).action(manager,nextPlayer,deckCard,deck);
        return true;
    }


    public void addCard (Card cardToAdd)
    {
        cards.add(cardToAdd);
    }
}
