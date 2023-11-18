import java.io.*;
import java.util.*;

public class minPQ3<E> {

    private Node[] pq;
    private int size;
    private Comparator<E> comparator;

    public minPQ3(int initialCap) {
       pq = new Node[initialCap + 1]; 
       this.size = 0;
    }
    
    public minPQ3() {
        this(1);
    }

    public minPQ3(int initialCap, Comparator<E> comparator) {
        this.comparator = comparator;
        pq = new Node[initialCap + 1];
        size = 0;
    }


    public minPQ3(Comparator<E> comparator) {
        this(1, comparator);
    }

    public void insert(Node newNode) {
        // just assuming that there wont be any overflow 
        pq[++size] = newNode;
        size++;
        swim(newNode, size);
    }

    private void swim(Node newNode, int size) {
        while(size > 1 && greater(size/2, size)) {
            exch(size/2, size);
            size = size/2;
        }
    }
    
    private void sink(Node newNode, int size) {
        while(2*size <= size) {
            int j = 2*size;
            if(j < size && greater(j, j+1)) {
                j++;
            }
            if(!greater(j, size)) {
                break;
            }
            exch(size, j);
            size = j;
        }
    }

    private void exch(int i, int j) {
        Node swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean greater(int j, int i) { 
        if (comparator == null) {
            return (pq[i].new_cases).compareTo(pq[j].new_cases) > 0;
        }
        return (pq[i].new_cases.compareTo(pq[j].new_cases) > 0);
    }

    public Node delMin() {
        Node temp = pq[1];
        exch(1, size--);
        sink(temp, 1);
        pq[size+1] = null;
        size--;
        return temp;
    }
    
}
