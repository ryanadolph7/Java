import java.util.*;

/**
 * Variation of Directed graph and undirected graph
 * Gotten from Princeton.edu 
 * <a href="https://algs4.cs.princeton.edu/54regexp/Digraph.java.html">Digraph.java</a>
 * <a href="https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Graph.java.html">Graph.java</a>
 */
public class Graph {

    private final int V;
    private int E; 
    private LinkedList<Integer>[] adj;
    private int[] inDeg;

    // Initializer of graph of size V vertices
    @SuppressWarnings("unchecked")
    public Graph(int V) {
        if(V < 0) {
            throw new IllegalArgumentException("Number of vertices must be non-negative");
        }
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        inDeg = new int[V];
        for(int v = 0; v < V; v++) {
            inDeg[v] = 1;
            adj[v] = new LinkedList<Integer>();
        }
    }

    // validates that a vertex is in the graphs rande 
    private void validateVertex(int v) {
        if(v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    // returns the outdegree of a given vertex
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    // returns the indegree of a given vertex
    public int indegree(int v) {
        validateVertex(v);
        return inDeg[v];
    }

    // revereses the graph 
    public Graph reverse() {
        Graph reverse = new Graph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdgeOnce(w, v);
            }
        }
        return reverse;
    }

    // function to put an edge between two given vertices
    public void addEdgeOnce(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        inDeg[w]++;
        adj[v].add(w);
    }

    // depth first search of a graph starting from a given node
    public void dfs(int node, LinkedList<Integer> adj[], int dp[], boolean visited[]) {
        // Mark as visited
        visited[node] = true;
 
        // Traverse for all its children
        for (int i = 0; i < adj[node].size(); i++) 
        {
 
            // If not visited
            if (!visited[adj[node].get(i)])
                dfs(adj[node].get(i), adj, dp, visited);
 
            // Store the max of the paths
            dp[node] = Math.max(dp[node], 1 + dp[adj[node].get(i)]);
        }
    }

    // function to find the longest path from a given node
    public int findLongestPath( int n)
    {
        LinkedList<Integer> edge[] = adj;
        // Dp array
        int[] dp = new int[n+1];
 
        // Visited array to know if the node
        // has been visited previously or not
        boolean[] visited = new boolean[n + 1];
 
        // Call DFS for every unvisited vertex
        for (int i = 1; i <= n; i++) 
        {
            if (!visited[i])
                dfs(i, edge, dp, visited);
        }
 
        int ans = 0;
 
        // Traverse and find the maximum of all dp[i]
        for (int i = 1; i <= n; i++) 
        {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // returns an iterable of the adjacency list
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // function to print the graph 
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

    // returns the amount of vertices in the graph
    public int V() {
        return this.V;
    }

}    