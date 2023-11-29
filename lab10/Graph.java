import java.util.*;

public class Graph {

    private final int V;
    private int E; 
    private LinkedList<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int V) {
        if(V < 0) {
            throw new IllegalArgumentException("Number of vertices must be non-negative");
        }
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }
    }

    private void validateVertex(int v) {
        if(v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex" + v + " is not between 0 and " + (V-1));
        }
    }

    public void addEdgeOnce(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        sb.append(V + " vertices, " + E + " edges\n");
        for(int v = 0; v < V; v++) {
            sb.append(v + ": ");
            for(int w : adj[v]) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int V() {
        return this.V;
    }

    
}
