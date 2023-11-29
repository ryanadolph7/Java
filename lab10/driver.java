import java.io.*;
import java.util.*;

public class driver {

    /** Test driver for Lab 10 Graph Searching
     * Data files: lab9in.txt
     * Output file: out.txt
     * Dependencies: Graph.java, BFS.java, States.java
     * 
     * Compilation: javac driver.java
     * Execuction: java driver
     * 
     * Searchs for a path from Montana to a states that is 2 or 3 connecting borders away
     *
     * @author Ryan Adolph
     * @since 11/28/2023
     */

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner( new File("lab9in.txt"));

        int noVerts = sc.nextInt();
        int noEdges = sc.nextInt();
        Graph G = new Graph(noVerts);

        States states = new States(noVerts);

        for(int i = 0; i < noEdges; i++) {
            String state1 = sc.next();
            int ind1 = states.add(state1);
            String state2 = sc.next();
            int ind2 = states.add(state2);

            G.addEdgeOnce(ind1, ind2);
        }

        PrintStream out = new PrintStream("out.txt");
        System.setOut(out);

        // -----------------------------------
        // BFS stuff below
        
        BFS b = new BFS(G, 35);
    
        for(int v = 0; v < G.V(); v++) {
            if(b.hasPathTo(v)) {
                if(b.distTo(v) == 2 || b.distTo(v) == 3) {
                    System.out.println("Distance from Montana to " + states.get(v) + ": " + b.distTo(v));
                    Stack<String> temp = new Stack<String>();
                    int count = 0;
                    for(int i : b.pathTo(v)) {
                        temp.push(states.get(i));
                        count++;     
                    } 
                    // definetely a cleaner way to do this, but I'm lazy
                    for(int i = 0; i < count; i++) {
                        System.out.print(temp.pop() + " - ");
                    }
                System.out.println("\n");
                }
            } 
        }
    }

}