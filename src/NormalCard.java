public class NormalCard extends Card {


    protected NormalCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager,  Card currentCard) {
        return canPutOver(currentCard);

    }

    @Override
    public void print() {

    }



    @Override
    public boolean canPutOver(Card currentCard) {
        return this.getColor() == currentCard.getColor() || this.getNumber() == currentCard.getNumber();
    }

}
