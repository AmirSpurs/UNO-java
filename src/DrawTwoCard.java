public class DrawTwoCard extends Card{

    protected DrawTwoCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager) {
        if (!canPutOver(manager.getCurrentCard()))
            return false;
        manager.setDraw2Points(manager.getDraw2Points() + 2);
        return true;
    }

    @Override
    public void specificPrint() {
        System.out.print("\u001B[4"+getColor()+"m"+"\u001B[97m" +"     Drew2     ");

    }


    @Override
    public boolean canPutOver(Card currentCard) {

        return  (this.getColor()==currentCard.getColor() || currentCard instanceof DrawTwoCard ) ;
    }
}

