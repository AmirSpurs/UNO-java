public interface Card {


    public boolean action (GameManagement manager,Player nextPLayer,Card currentCard,Deck deck);
    public void print();
    public int getColor();
    public boolean canPutOver(Card cardToPutOver);
}
