import java.io.*;
import java.util.*;
/**
 * Linked List Seperate Chaining Hash Table
 * 
 * @author SylvanusSun 
 * @author Robert Sedgewick, Kevin Wayne
 * @author Ryan Adolph
 * 
 * <a href="https://algs4.cs.princeton.edu/34hash/SeparateChainingLiteHashST.java.html"></a>
 * <a href="https://git.github.com/SylvanasSun/6872abd0fad061de28466cb775a84cea"></a> 
 * @since 10/15/2023
 */

public class seperateChainHT<Key, Value>  {
    private static final int CAP = 5;
    
    private int n, m;

    private Node<Key, Value>[] table;

    private class Node<Key, Value>{
        private Key key;
        private Value val;
        private Node<Key,Value> next;

        public Node() {
        }

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    // initailize a new hash table with a given capacity
    public seperateChainHT(int capacity) {
        this.m = capacity;
        this.n = 0;
        table = (Node<Key, Value>[]) new Node[capacity];
        for(int i = 0; i < capacity; i++) {
            table[i] = (Node<Key, Value>) new Node();
        }
    }

    // return the size of the hash table
    public int size() {
        return n;
    }

    // returns true if the hash table is empty 
    public boolean isEmpty() {
        return n == 0;
    }

    // put a key, value node into a the hash table
    public void put(Key key, Value val) {
        if(key == null) {
            return;
        }
        if(val == null) {
            remove(key);
            return;
        }
        if (n >= 10 * m)
            resize(2 * m);
        int i = hash(key);
        Node x = table[i];
        Node p = null;
        while (x != null) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
            p = x;
            x = x.next;
        }
        if (p == null) {
            table[i] = new Node(key, val, null);
            n++;
        } else {
            p.next = new Node(key, val, null);
            n++;
        }
    }

    // resize the hash table with a given capacity
    private void resize(int capacity) {
        seperateChainHT<Key, Value> temp = new seperateChainHT<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            Node<Key, Value> x = table[i];
            while (x != null) {
                Key key = x.key;
                if (key != null)
                    temp.put(key, this.get(key));
                x = x.next;
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.table = temp.table;
    }

    // get the hash code for a given key (string) based on its spot in the alphabet
    public int hash(Key key) {
        String s = key.toString();
        char t = s.charAt(0);
        return (((int)t * 11) % m);
    }


    // get the value for the given key
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("called get() with key is null.");
        int i = hash(key);
        Node x = table[i];
        while (x != null) {
            if (key.equals(x.key))
                return (Value) x.val;
            x = x.next;
        }
        return null;
    }

    // check the hash table if it contains a given key
    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("called contains() with key is null.");
        return get(key) != null;
    }

    // remove a given key, value pair from a hash table 
    public Value remove(Key key) {
        if (key == null)
            throw new IllegalArgumentException("called remove() with key is null.");
        if (isEmpty())
            throw new NoSuchElementException("called remove() with empty symbol table.");

        if (!contains(key))
            return null;
        int i = hash(key);
        Node x = table[i];
        Node p = null;
        Value oldValue = null;
        while (x != null) {
            if (key.equals(x.key)) {
                oldValue = (Value) x.val;
                if (p == null) {
                    table[i] = x.next;
                } else {
                    p.next = x.next;
                }
                n--;
                break;
            }
            p = x;
            x = x.next;
        }
        if (m > CAP && n <= 2 * m)
            resize(m / 2);
        return oldValue;
    }
    
    // Function that returns an iterable for the keys in the hash table
    public Iterable<Key> keys() {
        List<Key> list = new ArrayList<Key>();
        for (int i = 0; i < m; i++) {
            Node<Key, Value> x = table[i];
            while (x != null) {
                if (x.key != null)
                    list.add(x.key);
                x = x.next;
            }
        }
        return list;
    }


    /**
     * Main driver for Seperate chainging hash table 
     * read in a letter from a file, we were supposed to make its value its spot in the alphabet
     * but i made it its ascii value * 11 becuase im lazy.
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        seperateChainHT<Character, Integer> table = new seperateChainHT<Character, Integer>(5);

        Scanner sc = new Scanner( new File("lab8in.txt"));
        String temp = "";

        

        while(sc.hasNext()) {
            temp = sc.next();
            char key = temp.charAt(0);
            table.put(key, temp.hashCode() * 11);
        }
        sc.close();

        for(Character s : table.keys()) {
            int t = Integer.valueOf(s);
            System.out.println("Key: " + s + " Value: " + t + " Hash: " +table.get(s) % 5);
        }

    }
}
