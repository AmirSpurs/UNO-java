import java.util.Scanner;

public class ColorWildCard extends Card{


    protected ColorWildCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager, Card currentCard)
    {
        if (!canPutOver(currentCard))
            return false;
        askAndSetColor();
        return true;
    }

    public void askAndSetColor()
    {
        Scanner input = new Scanner(System.in);
        int colorNo ;

        while (true)
        {
            System.out.println("choose one of color\n1Red\n2)Green\n3)Yellow\n4)Blue");
            String userInput = input.next();
            try {
                colorNo = Integer.parseInt(userInput);
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid Input!");
            }
        }
        this.setColor(colorNo);
    }
    @Override
    public void print() {

    }

    @Override
    public boolean canPutOver(Card currentCard) {
        return  true ;

    }


}
