public class WildDrawFourCard extends ColorWildCard {
    protected WildDrawFourCard(int color, int number) {
        super(color, number);
    }


    @Override
    public boolean action(GameManagement manager, Card currentCard)
    {
        if (!canPutOver(currentCard))
            return false;
        askAndSetColor();
        manager.setDraw4Points(manager.getDraw4Points() + 4);

        return true;
    }
}

