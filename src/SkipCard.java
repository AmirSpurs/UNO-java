public class SkipCard extends Card {

  public SkipCard(int color, int number) {
    super(color, number);
  }

  @Override
  public boolean action(GameManagement manager, Card currentCard)
  {
      if (!canPutOver(currentCard))
          return false;
      manager.setTurn(manager.nextPlayer());
      return  true;
  }

  @Override
  public void print() {

  }

  @Override
  public boolean canPutOver(Card currentCard) {
    return  (this.getColor()==currentCard.getColor() || currentCard instanceof SkipCard ) ;

  }


}
