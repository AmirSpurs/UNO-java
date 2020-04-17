public class ReverseCard extends Card {

    protected ReverseCard(int color, int number) {
        super(color, number);
    }


    @Override
    public boolean action(GameManagement manager) {
        if (!canPutOver(manager.getCurrentCard()))
            return false;
        manager.setDirection(-1*manager.getDirection());
        return true;
    }

    @Override
    public void specificPrint() {
        System.out.print("\u001B[4"+getColor()+"m"+"\u001B[97m" +"    Reverse    ");

    }


    @Override
    public boolean canPutOver(Card currentCard) {
        return  (this.getColor()==currentCard.getColor() || currentCard instanceof ReverseCard ) ;

    }
}
