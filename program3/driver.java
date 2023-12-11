import java.io.*;
import java.util.*;

public class driver {

    // gotten from geeks4geeks
    private static int max_ref;

    public static int _lis(ArrayList<Integer> arr, int n) {

        if(n == 1) {
            return 1;
        }
        int res, max_ending = 1;
        for(int i = 1; i < n; i++) {
            res = _lis(arr, i); 
            if((arr.get(i - 1) < arr.get(n - 1)) && (res + 1 > max_ending)){
                max_ending = res + 1;
            }
        }
        if(max_ref < max_ending) {
            max_ref = max_ending;
        }
        return max_ending;
    }


    public static int lis(ArrayList<Integer> arr, int n) {
        max_ref = 1;
        _lis(arr, n);
        return max_ref;
    }

    

    /**
     * Test Driver for program 3 in CSCI232
     * Find the longest increasing subsequence of a random array of size 20 
     * of ints ranging from 1 - 20 (no repeating) using dynamic programming and a graph 
     * 
     * Compilation - javac driver.java 
     * Execution - java driver 
     * Dependencies - Graph.java 
     * 
     * @param args
     */

    public static void main(String[] args) {

        // test array 
        //int[] array = {5, 1, 2, 14, 19, 7, 17, 13, 3, 11, 4, 15, 8, 9};
        //int[] array2 = {18, 4, 1, 5, 8, 20, 6, 13, 7, 19, 10, 3, 17, 9};



        // random array  

        boolean[] check = new boolean[20];
        for(int i = 0; i < check.length; i++) {
            check[i] = false;
        }
        Random rand = new Random();
        int[] randArray = new int[20];
        for(int i = 0; i < randArray.length; i++) {
            int rint = rand.nextInt(20);
            if(check[rint] != true) {
                randArray[i] = rint + 1;
                check[rint] = true;
            } else {
                randArray[i] = -1;
            }
        }
        ArrayList<Integer> test = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            if(randArray[i] == -1) {
            } else {
                test.add(randArray[i]);
            }
        }

        System.out.println(test);


        // dynamic programming solution 

        //int n = array.length;
        int arraySize = test.size();
        //int n2 = array2.length;


        System.out.println("Length of LIS is " + lis(test, arraySize));


        // graph solution
        // incorrect solution, logic of creating edges isn't correct 
        // supposed to create edges when a number is smaller than the number after it,
        // but for some reason im stupid and can't figure it out

        Graph g2 = new Graph(21);

        for(int i = 0; i < arraySize; i++) {
            int ref = test.get(i);
            for(int j = 1; j < arraySize; j++) {
                if((test.get(j) > ref) && (test.get(j) > test.get(j-1))) {
                    //g2.addEdgeOnce(ref, test.get(j));
                    g2.addEdgeOnce(test.get(j), ref);
                }
            }
        }

        System.out.println("Longest increasing subsequence: " + g2.findLongestPath(20));



        //System.out.println(g2.toString());

        //-----------------------
        // below are other (incorrect) iterations of the graph solution  
        
        /* 
        Graph g3 = new Graph(21);

        for(int i = array.length - 1; i > 0; i--) {
            int ref = array[i]; 
            for(int j = i - 1 ; j > 0; j--) {
                if(array[j] < array[i]) {
                    g3.addEdgeOnce(array[j], ref);
                }
            }
        }

        //System.out.println(g3.toString());


        //---------------------------

        int v = array.length;

        Graph g = new Graph(21);
        
        

        int lastChecked = 0;
        for(int i = 1; i < v; i++) {
            int ref = array[i];
            for(int j = 1; j < v; j++) {
                lastChecked = 0;
                if((array[j] > ref) && (array[j] > lastChecked)){
                    lastChecked = array[j];
                    g.addEdgeOnce(ref, array[j]);
                }
            }
        }

        //System.out.println(g.toString());

        for(int i = 0; i < 21; i++) {
            //System.out.print(i + " : " +g.indegree(i) + " - ");
            //System.out.println(g.outdegree(i));
        }

        // ------------------------------

        for(int i = 1; i < v; i++) {
            int ref = array2[i];
            for(int j = 1; j < v; j++) {
                if((array2[j] > ref) && (array2[j] > array2[j-1])) {
                    //g2.addEdgeOnce(ref, array2[j]);
                }
            }
        }
        */

    }
}






