public class WildDrawFourCard extends ColorWildCard {
    public WildDrawFourCard(int color, int number) {
        super(color, number);
    }


    @Override
    public boolean action(GameManagement manager, Card currentCard)
    {
        if (!canPutOver(currentCard))
            return false;
        askAndSetColor(manager);
        manager.setDraw4Points(manager.getDraw4Points() + 4);

        return true;
    }
    @Override
    public void specificPrint() {
        System.out.print("\u001B[4"+getColor()+"m"+"\u001B[97m" +"   WildDrew4   ");

    }
}

