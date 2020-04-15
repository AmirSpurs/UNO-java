public class DrawTwoCard extends Card{

    protected DrawTwoCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager, Card currentCard) {
        if (!canPutOver(currentCard))
            return false;
        manager.setDraw2Points(manager.getDraw2Points() + 2);
        return true;
    }

    @Override
    public void print() {

    }

    @Override
    public boolean canPutOver(Card currentCard) {

        return  (this.getColor()==currentCard.getColor() || currentCard instanceof DrawTwoCard ) ;
    }
}

