import java.util.*;
import java.io.*;

public class MinPQ<Key extends Comparable<Key>> implements Iterable<Key> {
    
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;



    // MinPQ initializers
    public MinPQ(int initCap) {
        pq = (Key[]) new Comparable[initCap + 1];
        n = 0;
    }

    public MinPQ() {
        this(1);
    }

    public MinPQ(int initCap, Comparator<Key> comparator) {
        comparator = comparator;
        pq = (Key[]) new Comparable[initCap + 1];
        n = 0;
    }
    
    public MinPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }
    

    public MinPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Comparable[keys.length + 1];
        for(int i = 0; i < n; i++) {
            pq[i + 1] = keys[i];
        }
        for(int k = n/2; k >= 1; k--) {
            sink(k);
        }
    }
    // -----------------------

    // returns true if the size is 0, false otherwise
    public boolean isEmpty() {
        return n == 0;
    }

    // returns the size of the MinPQ
    public int size() {
        return n;
    }

    // returns the minimum key in MinPQ
    public Key min() {
        if(isEmpty()) {
            throw new NoSuchElementException("Priority Queue underflow");
        }
        return pq[1];
    }
    
    // resize PQ function
    public void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for(int i = 0; i < n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    // Insert fuction that takes in key, puts it in the back of the array and calls swim to correctly sort it
    public void insert(Key x) {
        if(n == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++n] = x;
        swim(n);
    }

    // delete the minimum key in the PQ
    public Key delMin() {
        if(isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;
        if((n > 0) && (n == (pq.length - 1) / 4)) {
            resize(pq.length - 1);
        }
        return min;
    }

    /*
     * helper functions
     */

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while(2*k <= n) {
            int j = 2*k;
            if(j < n && greater(j, j+1)) {
                j++;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        /*if(comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
        */ 
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // returns a new iterator to print in main function
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    public class HeapIterator implements Iterator<Key> {
        private MinPQ<Key> copy;
        
        public HeapIterator() {
            if(comparator == null) {
                copy = new MinPQ<Key>(size());
            } else {
                copy = new MinPQ<Key>(size());
            }
            for(int i = 0; i < n; i++) {
                copy.insert(pq[i]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        } 

        public Key next() {
            if(!hasNext()) {
                return null;
            }
            return copy.delMin();
        }

    }
}


