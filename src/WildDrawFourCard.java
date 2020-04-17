public class WildDrawFourCard extends ColorWildCard {
    public WildDrawFourCard(int color, int number) {
        super(color, number);
    }


    @Override
    public boolean action(GameManagement manager)
    {
        if (!canPutOverWild(manager))
            return false;
        askAndSetColor(manager);
        manager.setDraw4Points(manager.getDraw4Points() + 4);

        return true;
    }
    public boolean canPutOverWild(GameManagement management)
    {
            if (management.getDraw4Points()>0)
                return true ;
            for (Card card: management.getCurrentPlayer().getCards()) {

                if (!(card instanceof WildDrawFourCard) && card.canPutOver(management.getCurrentCard()))
                    return false;
            }
        return true;
    }
    @Override
    public void specificPrint() {
        System.out.print("\u001B[4"+getColor()+"m"+"\u001B[97m" +"   WildDrew4   ");

    }
}

