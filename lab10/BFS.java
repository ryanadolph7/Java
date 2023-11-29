import java.util.*;

/**
 * Breadth First Search Class for a given undirected graph
 * 
 * <a href="https://algs4.cs.princeton.edu/41graph/BreadthFirstPaths.java.html">Princeton.edu</a>
 * @author Robert Sedgewick, Keivin Wayne
 */
public class BFS {
    private static final int MAX = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    /** Takes in a graph and a given index then computes the shortest path
     * 
     * @param g
     * @param s
     */
    public BFS(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        validateVertex(s);
        bfs(g, s);
    }

    // search from a single source 
    private void bfs(Graph g, int s) {
        Queue<Integer> q = new LinkedList<Integer>();
        for(int v = 0; v < g.V(); v++) {
            distTo[v] = MAX;
        }
        distTo[s] = 0;
        marked[s] = true;
        q.add(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
    }    

    // validate that the vertex is in a given range
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    // validate that there is a path from the vertex to another vertex
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    // returns the distance from one vertex to another 
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    // returns an iterable with the path from one state to another
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }

}
