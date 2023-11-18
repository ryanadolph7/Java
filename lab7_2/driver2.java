import java.util.*;
import java.io.*;

public class driver2 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner( new File("owid-covid-data.csv"));
        Scanner tsc = new Scanner( new File("in.txt"));
        MinPQ<String> pq = new MinPQ<String>();
        MinPQ<Integer> test = new MinPQ<Integer>();

        String temp = "";
        String[] tSplit = new String[6];


        sc.nextLine();

        while(sc.hasNextLine()) {
            temp = sc.nextLine();
            tSplit = temp.split(",");
            int lt = Integer.valueOf(tSplit[5]);
            //test.insert(lt);
            pq.insert(tSplit[5]);
        }

        while(!pq.isEmpty()) {
            System.out.println(pq.delMin());
        }

    }

}
