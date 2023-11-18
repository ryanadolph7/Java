import java.util.*;
import java.io.*;
/**
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * 
 * <a href="https://algs4.cs.princeton.edu/24pq/MinPQ.java.html"> 
 * 
 * 
 */

public class minPQ2<Key> implements Iterable<Key> {

    private Key[] pq;
    private int n; // number of items in queue
    private Comparator<Key> comparator;
    
    public minPQ2(int initialCap) {
        pq = (Key[]) new Object[initialCap + 1];
        n = 0;
    }

    public minPQ2() {
        this(1);
    }
   
    public minPQ2(int initialCap, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initialCap + 1];
        n = 0;
    }


    public minPQ2(Comparator<Key> comparator) {
        this(1, comparator);
    }


    // -------------------------------
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        private minPQ2<Key> copy;

        public HeapIterator() {
            if (comparator == null) { 
                copy = new minPQ2<Key>(size());
            }else{                    
                copy = new minPQ2<Key>(size(), comparator);
            }
            for (int i = 1; i <= n; i++) {
                copy.insert((Node)pq[i]);
            }
        }

        public boolean hasNext() { 
            return !copy.isEmpty();               
        }
        public void remove(){ 
            throw new UnsupportedOperationException();  
        }
        
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
    // --------------------------------------------------------

    /**
     * 
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * 
     * @return
     */
    public Key min() {
        if (isEmpty()) { 
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    /**
     * 
     * @param capacity
     */
    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp =  (Key[])new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * 
     * @param Key x
     */
    public void insert(Node x) {
        if (n == pq.length - 1) { 
            resize(2 * pq.length);
        }
        pq[++n] = (Key)x.new_cases; 
        swim(n);
        assert isMinHeap();
    }

    /**
     * 
     * @returns and deletes the smallest Node in the queue
     */
    public Key delMin() {
        if(isEmpty()) {
            throw new NoSuchElementException("Priority Queue Underflow");
        }
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;

        if((n > 0) && (n == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        assert isMinHeap();
        return min;
    }


    // ---------------------------------------------
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    // ----------------------------------------------

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean isMinHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n+1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    private boolean isMinHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))  return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

}
