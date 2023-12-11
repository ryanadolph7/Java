import java.util.*;

public class graph {

    private int vertex;
    private int edges;
    private LinkedList<Integer>[] table;
    private LinkedList<String>[] borders;


    /*private class Node<Key, Value> {
        private Key key;
        private Value val;
        private Node<Key, Value> next;

        public Node() {
        }

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = null;
        }

    }
    */


    /* 
    @SuppressWarnings("unchecked")
    public graph(int vertex) {
        if(vertex == 0) {
            System.out.println("Graph initialized with 0 vertexes");
            return;
        }
        this.vertex = vertex;
        this.edges = 0;
        this.table = (LinkedList<Integer>[]) new LinkedList[48];
        for(int i = 0; i < vertex; i++) {
            this.table[i] = new LinkedList<Integer>();
            this.borders[i] = new LinkedList<String>();
        }
    }
    */

    // function to fill a graph from a scanner input
    // validates each vertex and adds the vertex to a new adjacency list
    @SuppressWarnings("unchecked")
    public graph(Scanner in) {
        if(in == null) {
            return;
        }
        try {

            this.vertex = in.nextInt();
            this.edges = in.nextInt();
            this.table = (LinkedList<Integer>[]) new LinkedList[vertex];
            this.borders = (LinkedList<String>[]) new LinkedList[vertex];
            if(vertex < 0) {
                System.out.println("Tried to call Graph with 0 vertices");
            }
            for(int i = 0; i < vertex; i++) {
                this.table[i] = new LinkedList<Integer>();
                this.borders[i] = new LinkedList<String>();
            }
            if(edges < 0) {
                System.out.println("Number of edges in graph must be less than 0");
            }

            for(int i = 0; i < edges; i++) {
                String v1 = in.next().toLowerCase();
                String v2 = in.next().toLowerCase();
                int h1 = hash(v1) % vertex;
                int h2 = hash(v2) % vertex;
                borders[h1].add(v2);
                borders[h2].add(v1);
                
                // debug statements
                //System.out.print(v1);
                //System.out.print(" "  + h1 + "\n");
                //System.out.print(v2);
                //System.out.print(" " + h2 + "\n");

                validateVertex(h1);
                validateVertex(h2);
                fillBorders(v1, v2);
                addEdge(h1, h2);
            }
            


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // hash function i found online that supposedly good for hasing strings 
    public int hash(String v) {
        int hash = 5381;
        for(int i = 0; i < v.length(); i++) {
            hash += ((hash << 5) + hash) + v.charAt(i);
        }
        return Math.abs(hash);
    }


    // function that validates a given vertex
    // meaning it checks if the vertex is in the table between 0 and Vertices - 1
    private void validateVertex(int v) {
        if(v < 0 || v >= vertex) {
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (vertex-1));
        }
    }

    // function that fills a a graph with the String names of each vertex
    public void fillBorders(String v, String e) {
        int h1 = Math.abs(v.hashCode() % 48);
        int h2 = Math.abs(e.hashCode() % 48);
        borders[h1].add(v);
        borders[h2].add(e);
    }

    // function to ad edges between two vertices
    // validates the vertices then adds each Vertex to the adjacent vertex's edge set
    public void addEdge(int v, int e) {
        validateVertex(v);
        table[v].add(e);
        edges++;
    }


    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return table[v];
    }

    // function to print the graph
    // prints Vertex : Edges...

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb.append(vertex + " vertices, " + edges + " edges\n");
        for(int i = 0; i < vertex; i++) {
            //sb2.append(borders[i] + " : "); 
            sb.append(borders[i] + " : ");
            //sb.append(i + " : ");
            //for(int w : table[i]) {
                //sb.append(w + " ");
            //}
            for(String w : borders[i]) {
                sb.append(w + " ");
            }
            sb2.append("\n");
            sb.append("\n\n");
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }
    
}
