public class SkipCard extends Card {

  public SkipCard(int color, int number) {
    super(color, number);
  }

  @Override
  public boolean action(GameManagement manager, Player nextPLayer, Card currentCard, Deck deck)
  {
      if (!replaceCard(currentCard,deck))
        return false;
      manager.setTurn(manager.nextPlayer());
      manager.setTurn(manager.nextPlayer());
      return  true;
  }

  @Override
  public void print() {

  }

  @Override
  public boolean canPutOver(Card cardToPutOver) {
    return  (this.getColor()==cardToPutOver.getColor() || cardToPutOver instanceof SkipCard ) ;

  }


}
