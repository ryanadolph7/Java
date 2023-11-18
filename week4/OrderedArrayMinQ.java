import java.io.*;
import jav.util.*;


public class OrderedArrayMinQ<Node extends Comparable<Key>> {
    
    private Node[] pq;
    private int n;

    public OrderedArrayMinQ(int capacity) {
        pq = (Node[]) (new comparable[capacity]);
        n = 0;
    }

    public boolean isEmpty() {
        return (n == 0);
    }
    public int size() {
        return n;
    }
    public Key delMin() {
        return pq[--n];
    }

    public void insert(Node key) {
        int i = n - 1;
        while(i >= 0 && less(key, pq[i])) {
            pq[i + 1] = pq[i];
            i--;
        }
        pq[i+1] = key;
        n++;
    }

    private boolean less(Node v, Node w) {
        // returns < 0 if v < w, > if v > w, 0 if v == w 
        return (v.new_cases.compareTo(w.new_cases) < 0);
    }

}
