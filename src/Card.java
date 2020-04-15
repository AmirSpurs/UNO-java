public abstract class Card
{

     private int color;
    private int number;

    public void setColor(int color) {
        this.color = color;
    }

    protected Card(int color, int number) {
        this.color = color;
        this.number = number;
    }

    public boolean replaceCard (Card currentCard,Deck deck)
    {
        if (!currentCard.canPutOver(this))
            return false;
        deck.addCard(currentCard);
        deck.setCurrentCard(this);
        return true;
    }
    public int getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public abstract boolean action (GameManagement manager, Player nextPLayer, Card currentCard, Deck deck);
    public abstract void print();

    public abstract boolean canPutOver(Card cardToPutOver);
}
