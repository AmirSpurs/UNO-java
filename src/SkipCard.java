public class SkipCard extends Card {

  public SkipCard(int color, int number) {
    super(color, number);
  }

  @Override
  public boolean action(GameManagement manager) throws InterruptedException {
      if (!canPutOver(manager.getCurrentCard()))
          return false;
      manager.nextPlayer();
      manager.showGameInfo();
      printWholeCard();
      Thread.sleep(800);
      System.out.println("Next player misses a turn!");
      Thread.sleep(1000);
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
