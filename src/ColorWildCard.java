import java.util.Scanner;

public class ColorWildCard extends Card{


    public ColorWildCard(int color, int number) {
        super(color, number);
    }

    @Override
    public boolean action(GameManagement manager, Card currentCard)
    {
        if (!canPutOver(currentCard))
            return false;
        askAndSetColor(manager);
        return true;
    }

    @Override
    public void specificPrint() {
        System.out.print("\u001B[4"+getColor()+"m"+"\u001B[97m" +"   ColorWild   ");


    }

    public void askAndSetColor(GameManagement manager)
    {
        Scanner input = new Scanner(System.in);
        int colorNo ;

        while (true)
        {
            System.out.println("choose one of color\n1)Red\n2)Green\n3)Yellow\n4)Blue");
            String userInput = input.next();
            try {
                colorNo = Integer.parseInt(userInput);
               if (colorNo>0 && colorNo<5)
                   break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid Input!");
                manager.showGameInfo();
            }
        }
        this.setColor(colorNo);
    }


    @Override
    public boolean canPutOver(Card currentCard) {
        return  true ;

    }


}
