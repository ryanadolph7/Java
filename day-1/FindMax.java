// Example11-2
import java.util.Scanner;

class FindMax {
    public static void main(String args[]) {

        PositiveNumsList pnl = new PositiveNumsList(50);

        Scanner in = new Scanner(System.in);
        
        while (in.hasNextInt()) {
            
            pnl.Add(in.nextInt());
        }
    
        System.out.println("The maximum number is " + pnl.Max());
        pnl.Delete(pnl.Max());
        System.out.println("The maximum number is " + pnl.Max());

        System.out.println("Is 10 in the list? " + pnl.Search(10));
        in.close();
    }
}
