public class DrawTwoCard extends Card{

    protected DrawTwoCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager, Player nextPLayer, Card currentCard, Deck deck) {
        if (!replaceCard(currentCard,deck))
            return false;
        manager.setDrawPoint(manager.getDrawPoint()+2);
        manager.setTurn(manager.nextPlayer());

    }

    @Override
    public void print() {

    }

    @Override
    public boolean canPutOver(Card cardToPutOver) {

        return false;
    }
}

