public class NormalCard extends Card {


    protected NormalCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager,  Card currentCard) {
        return canPutOver(currentCard);

    }

    @Override
    public void specificPrint() {
        System.out.print("\u001B[4"+getColor()+"m" +"\u001B[97m"+"       "+getNumber()+"       ");


    }


    @Override
    public boolean canPutOver(Card currentCard) {
        return this.getColor() == currentCard.getColor() || this.getNumber() == currentCard.getNumber();
    }

}
