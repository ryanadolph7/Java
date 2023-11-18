import java.io.*;
import java.util.*;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;


    public BST() {

    }
    
    public boolean isEmpty() {
        return size() == 0;
    }

    private int size() {
        return size(root);
    }
    
    private int size(Node x) {
        if(x == null) {
            return null;
        } else {
            return x.size;
        }
    }

    public void put(E continent, Value location, E date,  E total_cases, E new_cases, Key population) {
        if(population == null) {
            return;
        }
        if(location == null) {
            delete(population);
            return;
        }

        root = put(root, continent, location, date, total_cases, new_cases, population);
    }

    private Node put(Node x, E continent, Value location, E date, E total_cases, E new_cases, Key population) {
        if(x == null) {
            return new Node(continent, location, date, total_cases, new_cases, population);
        }
        int cmp = population.compareTo(x.population);
        if(cmp < 0) {
            x.left = put(x.left, continent, location, date, total_cases, new_cases, population);
        } else if(cmp > 0) {
            x.right = put(x.right, continent, location, date, total_cases, new_cases, population);
        } else if(cmp == 0) {
            x.continent = continent; 
            x.location = location;
            x.total_cases = total_cases;
            x.date = date;
            x.new_cases = new_cases;
            x.population = population;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Iterable<Key> keys() {
        if(isEmpty()) {
            return new Queue<Key>();
        }
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        keys(root, q, lo, hi);
        return q;
    }

    private void keys(Node x, Queue<Key> q, Key lo, Key hi) {
        if(x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.population);
        int cmphi = hi.compareTo(x.population);
        if(cmplo < 0) {
            keys(x.left, q, lo, hi);
        } if(cmplo <=0 && cmphi >= 0) {
            q.add(x.population);
        }
        if(cmphi > 0) {
            keys(x.right, q, lo, hi);
        }
    }

    public int size(Key lo, Key size) {
        if(lo == null) {
            return null;
        }
        if(hi == null) {
            return null;
        }
        if(lo.compareTo(hi) > 0 ) {
            return 0;
        } 
        if(contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        }
    }

    // need rank(), delete(), isEmpty()




}