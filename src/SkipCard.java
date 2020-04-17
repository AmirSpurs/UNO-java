public class SkipCard extends Card {

  public SkipCard(int color, int number) {
    super(color, number);
  }

  @Override
  public boolean action(GameManagement manager)  {
      if (!canPutOver(manager.getCurrentCard()))
          return false;
      manager.nextPlayer();
      System.out.println("Next player misses a turn!");
      return  true;
  }

    @Override
    public void specificPrint() {
        System.out.print("\u001B[4"+getColor()+"m"+"\u001B[97m" +"      Skip     ");

    }


    @Override
  public boolean canPutOver(Card currentCard) {

    return  (this.getColor()==currentCard.getColor() || currentCard instanceof SkipCard ) ;

  }


}
