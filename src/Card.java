public abstract class Card
{

     private int color;
    private int number;

    public void setColor(int color) {
        this.color = color;
    }

    protected Card(int color, int number) {
        this.color = color;
        this.number = number;
    }


    public int getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }

    public abstract boolean action (GameManagement manager) throws InterruptedException;
    public  void print(int index)
    {
        if (index!=5)
            System.out.print("\u001B[4"+color+"m" +"               ");
        else
            specificPrint();
        System.out.print("\u001B[0m     ");


    }
    public void printWholeCard()
    {

        for (int i = 1;i<=9;i++) {
            System.out.print("                                           ");

            print(i);
            System.out.println("\u001B[0m     ");
        }

        System.out.println("\u001B[0m     \n");

    }
    public void cardPuttedMessage() throws InterruptedException {
        System.out.println("Card Putted!");
        Thread.sleep(600);
    }
    public abstract void specificPrint();
    public abstract boolean canPutOver(Card currentCard);


}
