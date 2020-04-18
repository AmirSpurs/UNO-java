import java.util.*;

/**
 * The Deck class implements Deck of the game and holds cards of the game.
 *
 * @author Amirreza Hashemi
 * @version 1.0
 * @since 4/14/2020
 */
public class Deck {
    //The cards of deck.
    private ArrayList<Card> cards;

    /**
     * Add a card to Deck.(when a card is put the previous card brought back to deck)
     *
     * @param cardToAdd the card to add
     */
    public void addCard(Card cardToAdd) {
        cards.add(cardToAdd);
    }

    /**
     * Instantiates a new Deck with 108 cards.
     * one 0 and two 1-9 normal card for each color
     * one DrawTwo,Skip and reverse card for each color
     * four WildDrawFour ana WildColorCard with black color
     * colors could be green,yellow,red or blue
     */
    public Deck() {

        // black=0 red =1 green=2 yellow=3 blue=4

        cards = new ArrayList<Card>();
        for (int color = 1; color <= 4; color++) {

            cards.add(new NormalCard(color, 0));
            cards.add(new WildDrawFourCard(0, 50));
            cards.add(new ColorWildCard(0, 50));
            for (int i = 1; i <= 2; i++) {
                for (int number = 1; number < 10; number++)
                    cards.add(new NormalCard(color, number));
                cards.add(new SkipCard(color, 20));
                cards.add(new ReverseCard(color, 20));
                cards.add(new DrawTwoCard(color, 20));
            }
        }
        //shuffles the cards
        Collections.shuffle(cards);

    }

    /**
     * Puts a random first card in game's table.
     *
     * @return the card
     */
    public Card putFirstCard() {
        Random topCard = new Random();
        int index = 0;
        do {
            index = topCard.nextInt(108);
        } while (cards.get(index) instanceof WildDrawFourCard || cards.get(index) instanceof ColorWildCard); //recheck for sure
        Card currentCard = cards.get(index);
        cards.remove(index);
        return currentCard;
    }
    /**
     * Gives away card to players when they're surpassed to pick up card.
     *
     * @param playerToGive the player to give card to
     */
    public void giveAwayCard(Player playerToGive) {
        Random randCard = new Random();
        int index = randCard.nextInt(cards.size());
        playerToGive.addCard(cards.get(index));
        cards.remove(index);
    }
    /**
     * Hands out 7 card to each player at start of the game.
     *
     * @param playerToGive the player to hand out to
     */
    public void handOutCard(Player playerToGive) {
        Collections.shuffle(cards);
        for (int i = 0; i < 7; i++)
            playerToGive.addCard(cards.get(i));
        Iterator itr = cards.iterator();
        int i = 1;
        while (itr.hasNext()) {
            itr.next();
            if (i <= 7)
                itr.remove();

            i++;
        }
    }
}


