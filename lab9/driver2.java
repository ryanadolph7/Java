import java.io.*;
import java.util.*;

public class driver2 {

    public static void main(String[] args) throws FileNotFoundException{

        Scanner sc = new Scanner( new File("lab9in.txt"));

        graph graph = new graph(sc);
    
        System.out.println(graph);

        /*while(sc.hasNext()) {
            data = sc.next().toLowerCase();
            for(int i = 0; i < data.length(); i++) {
                hash = (hash * 31) + data.charAt(i);
            }

            int n = data.hashCode();
            //System.out.println(data + " " + Math.abs(n) % 48);
            if((Math.abs(n) % 48) == 1) {
                System.out.println(data + " " + Math.abs(n) % 48);
            }

        }
        */

    }
    
}
