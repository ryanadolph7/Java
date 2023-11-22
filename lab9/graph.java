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

    @SuppressWarnings("unchecked")
    public graph(Scanner in) {
        if(in == null) {
            return;
        }
        try {

            this.vertex = in.nextInt();
            this.edges = in.nextInt();
            this.table = (LinkedList<Integer>[]) new LinkedList[vertex];
            if(vertex < 0) {
                System.out.println("Tried to call Graph with 0 vertices");
            }
            for(int i = 0; i < vertex; i++) {
                this.table[i] = new LinkedList<Integer>();
            }
            if(edges < 0) {
                System.out.println("Number of edges in graph must be less than 0");
            }

            for(int i = 0; i < edges; i++) {
                String v1 = in.next();
                String v2 = in.next();
                int h1 = Math.abs(v1.hashCode() % 48);
                int h2 = Math.abs(v2.hashCode() % 48);
                
                //System.out.print(v1);
                //System.out.print(" "  + h1 + "\n");
                //System.out.print(v2);
                //System.out.print(" " + h2 + "\n");

                validateVertex(h1);
                validateVertex(h2);
                addEdge(h1, h2);
            }
            


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int vertex() {
        return this.vertex;
    }

    public int edges() {
        return this.edges;
    }

    private void validateVertex(int v) {
        if(v < 0 || v >= vertex) {
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (vertex-1));
        }
    }

    public void addEdge(int v, int e) {
        validateVertex(v);
        validateVertex(e);
        table[v].add(e);
        table[e].add(v);
        edges++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return table[v];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(vertex + " vertices, " + edges + " edges\n");
        for(int i = 0; i < vertex; i++) {
            sb.append(i + " : ");
            for(int w : table[i]) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }



    
}
