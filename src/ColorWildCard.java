import java.util.Scanner;

public class ColorWildCard extends Card{


    protected ColorWildCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager, Player nextPLayer, Card currentCard, Deck deck)
    {
        if (!replaceCard(currentCard,deck))
            return false;


    }

    public void askColor()
    {
        Scanner input = new Scanner(System.in);
        while (true)
        {
            System.out.println("choose one of color\n1Red\n2)Green\n3)Yellow\n4)Blue");
            String userInput = input.next();
            int colorNo;
            try {
                colorNo = Integer.parseInt(userInput);
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid Input!");
            }
        }
    }
    @Override
    public void print() {

    }

    @Override
    public boolean canPutOver(Card cardToPutOver) {
        return  (this.getColor()==cardToPutOver.getColor() || cardToPutOver instanceof ColorWildCard ) ;

    }


}
