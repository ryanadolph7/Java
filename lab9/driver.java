import java.io.*;
import java.util.*;

public class driver {


    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner( new File("lab9in.txt"));
        Scanner sc2 = new Scanner( new File("lab9in.txt"));
        Scanner sc3 = new Scanner( new File("lab9in.txt"));
        Scanner tsc = new Scanner( new File("lab9in.txt"));
        
        graph G = new graph(sc);

        int v = sc3.nextInt();
        int e = sc3.nextInt();
        while(sc3.hasNext()) {
            int h = 31;
            String data = sc3.next();
            for(int i = 0; i < data.length(); i++) {
            }
        }
        

        for(int i = 0; i < 2; i++) {
            tsc.next();
        }
        String da = tsc.next();
        System.out.println(da.hashCode() % 48);
    
        
        sc2.nextLine();
        sc2.nextLine();

        String t = sc2.nextLine();
        int text = (t.hashCode() * 31) % 48;
        String next = sc2.nextLine();
        int nhash = 7;
        int hash = 7;
        for(int i = 0; i < t.length(); i++) {
            hash = (hash * 53) + t.charAt(i);
        }
        for(int i = 0; i < next.length(); i++) {
            nhash = (nhash * 53) + next.charAt(i);
        }
        sc2.nextLine();
        String test = sc2.nextLine();
        int n2hash = 7;
        for(int i = 0; i < test.length(); i++) {
            n2hash = (n2hash * 53) + test.charAt(i);
        }
        //System.out.println(hash % 48);
        //System.out.println(nhash % 48);
        //System.out.println(n2hash % 48);


    }

}