import java.util.*;
import java.io.*;

public class driver {
    /**
     * 
     * Nothing works in this driver
     * For some reason, the krusals array.sort call is throwing an error with the test input from the lab
     * but using the test input provided for the kruskals test cases it works
     * idk whats going on
     * 
     */
    
    private static int m;

    private static int hash(String s) {
        int hash = 0;
        for(int i = 0; i < s.length(); i++) {
            hash = (hash * 31) + s.charAt(i);
        }
        return Math.abs(hash % 6);
    }


    public static void main(String[] args) throws FileNotFoundException {



        // -----------------------
        // testing

        //Scanner in = new Scanner(System.in);
        //edgeWeightedGraph g1 = new edgeWeightedGraph(in);

        //System.out.println(g1.toString());
        //KrusalsMST2 mst = new KrusalsMST2(g1);
        
        //for(Edge e : mst.edges()) {
            //System.out.println(e);
        //}

        //System.out.println(mst.weight());


        // -------------------
        
        String[] arr = {"Seattle", "LosAngeles", "Denver", "Atlanta", "NewYork", "Miami"};

        Scanner sc = new Scanner( new File("lab11in.txt"));

        int v = sc.nextInt();
        m = v;
        int e = sc.nextInt();

        edgeWeightedGraph g = new edgeWeightedGraph(v, e);


        while(sc.hasNext()) {
           String in1 = sc.next();
           String in2 = sc.next();
           int in3 = sc.nextInt();
           
           int hash1 = hash(in1);
           int hash2 = hash(in2);
           double in_3 = (double)in3;
           //System.out.println(hash1 + " " + hash2);

            for(int i = 0; i < 6; i++) {
                if(in1.equals(arr[i])) {
                    hash1 = i;
                }
                if(in2.equals(arr[i])) {
                    hash2 = i;
                }
            }



           Edge edge = new Edge(hash1, hash2, in_3);

           g.addEdge(edge);

           //System.out.println(hash1 + " " + hash2 + " " + in3);
        }

        System.out.println(g.toString());

        KrusalsMST2 mst = new KrusalsMST2(g);






    }


}