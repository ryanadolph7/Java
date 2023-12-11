import java.io.*;
import java.util.*;

public class KruskalMST {

    private static final double FLOATING_POINT_EPSILON = 1.0E-12;

    private double weight;
    private Queue<Edge> mst = new LinkedList<Edge>();

    public KruskalMST(edgeWeightedGraph G) {

        // create array of edges, sorted by weight
        Edge[] edges = new Edge[G.E()];
        int t = 0;
        for (Edge e: G.edges()) {
            edges[t++] = e;
        }
        Arrays.sort(edges);

        // run greedy algorithm
        UF uf = new UF(G.V());
        for (int i = 0; i < G.E() && mst.size() < G.V() - 1; i++) {
            Edge e = edges[i];
            int v = e.either();
            int w = e.other(v);

            // v-w does not create a cycle
            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);     // merge v and w components
                mst.add(e);     // add edge e to mst
                weight += e.weight();
            }
        }

        assert check(G);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    private boolean check(edgeWeightedGraph G) {

        // check total weight
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", total, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new UF(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new UF(G.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (uf.find(x) != uf.find(y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }

    
    public static void main(String[] args) throws FileNotFoundException {

        Scanner test = new Scanner( new File("tiny.txt"));

        int v1 = test.nextInt();
        int e1 = test.nextInt();
        edgeWeightedGraph g1 = new edgeWeightedGraph(v1, e1);
        
        System.out.println(g1.toString());

        while(test.hasNextInt()) {
            int t1 = test.nextInt();
            int t2 = test.nextInt();
            int t3 = test.nextInt();;
            Edge e = new Edge(t1, t2, t3);
            g1.addEdge(e);  
        }
         
        KruskalMST ms = new KruskalMST(g1);

        for(Edge e : ms.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", ms.weight());




    }
    
}
