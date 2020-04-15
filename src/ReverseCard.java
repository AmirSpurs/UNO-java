public class ReverseCard extends Card {

    protected ReverseCard(int color, int number) {
        super(color, number);
    }


    @Override
    public boolean action(GameManagement manager, Card currentCard) {
        if (!canPutOver(currentCard))
            return false;
        manager.setDirection(-1*manager.getDirection());
        return true;
    }

    @Override
    public void print() {

    }

    @Override
    public boolean canPutOver(Card currentCard) {
        return  (this.getColor()==currentCard.getColor() || currentCard instanceof ReverseCard ) ;

    }
}
