public class Computer extends Player {
    public Computer(String name) {
        super(name);
    }

    public String chooseCard(Card currentCard)
    {
        for (int i=0;i<getCardsNumber();i++) {
            if (cardAt(i).canPutOver(currentCard))
                return (Integer.toString(i+1));
        }
            return null;
     }
    public String chooseDrawCard(boolean wildOrColor)
    {
        for (int i=0;i<getCardsNumber();i++) {
            if ( (wildOrColor && cardAt(i) instanceof WildDrawFourCard) || (!wildOrColor && cardAt(i) instanceof DrawTwoCard))
                return (Integer.toString(i+1));

        }
        return null;
    }

    }

