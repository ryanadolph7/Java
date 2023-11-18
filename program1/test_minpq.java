import java.util.*;
import java.io.*;

public class test_minpq<Key> implements Iterable<Key> {
    
    private Node[] pq;
    private int size;
    private Comparator<Key> comparator;


    public test_minpq(int initialSize) {
        pq = new Node[initialSize + 1];
        size = 0;
    }

    public test_minpq() {
        this(1);
    }


    public test_minpq(int initialCap, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Node[]) new Object[initialCap + 1];
        size = 0;
    }


    public test_minpq(Comparator<Key> comparator) {
        this(1, comparator);
    }

    // -------------------------------
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        private test_minpq<Key> copy;

        public HeapIterator() {
            if (comparator == null) { 
                copy = new test_minpq<Key>(size());
            }else{                    
                copy = new test_minpq<Key>(size(), comparator);
            }
            for (int i = 1; i <= size; i++) {
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


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }


    public void insert(Node newNode) {
        if(size == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++size] = newNode;
        swim(size);
        assert isMinHeap();
    } 

    // -------------------------------

    // helper functions 

    public void swim(int k) {
        while(k > 1 && greater(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    public void sink(int k) {
        while(2 * k <= size) {
            int j = 2*k;
            if(j < size && greater(j, j + 1)) {
                j++;
            }
            if(!greater(j, j + 1)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable) pq[i].new_cases).compareTo(pq[j].new_cases) > 0;
        }
        else {
            return comparator.compare((Key)pq[i], (Key)pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        Node swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean isMinHeap() {
        for (int i = 1; i <= size; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = size + 1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    private boolean isMinHeapOrdered(int k) {
        if (k > size) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= size && greater(k, left))  return false;
        if (right <= size && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    public Node min() {
        if (isEmpty()) { 
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    public Key delMin() {
        if(isEmpty()) {
            throw new NoSuchElementException("Priority Queue Underflow");
        }
        Key min = (Key)pq[1];
        exch(1, size--);
        sink(1);
        pq[size+1] = null;

        if((size > 0) && (size == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        assert isMinHeap();
        return min;
    }

    private void resize(int capacity) {
        assert capacity > size;
        Node[] temp = (Node[])new Object[capacity];
        for (int i = 1; i <= size; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


    
}
