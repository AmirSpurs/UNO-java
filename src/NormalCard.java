public class NormalCard implements Card {
    final private int color;
    private int number;


    public NormalCard(int color,int number) {
        this.color = color;
        this.number = number;
    }


    @Override
    public boolean action(GameManagement manager, Player nextPLayer, Card currentCard, Deck deck) {
        if (!currentCard.canPutOver(this))
            return false;
        deck.addCard(currentCard);
        deck.setCurrentCard(this);
        manager.setTurn(manager.nextPlayer());
            return true;

    }

    @Override
    public void print() {

    }

    public int getNumber() {
        return number;
    }

    @Override
    public int getColor() {
        return color;


    }


    @Override
    public boolean canPutOver(Card cardToPutOver) {
        if (color==)
        return false;
    }

}
