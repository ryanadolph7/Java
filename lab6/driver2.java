import java.util.*;
import java.io.*;

/**
 * Driver and data structure for lab 6 problem 3
 * 
 * Compilation: javac driver2.java
 * Execution: java driver2
 * Dependencies: java.util.*, java.io.*
 * Data Files: lab6in2.txt
 * 
 * % lab6in2.txt
 * D E S I G N C O D E T E S T
 * 
 * 
 * @author Robert Sedgewick, Kevin Wayne
 * @author Ryan Adolph, CSCI 232, lab 6 question 3
 * <a href="https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html">Princten university</a>
 * @since 10/24
 */
public class driver2<Key extends Comparable<Key>, Value> {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    public driver2() {
    }

    /**
     * Node helper function 
     * 
     * @param x
     * @return true if the nodes color is red, false otherwise
     */
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    /**
     * Node helper function
     * @param x
     * @return the size of the Red-Black tree
     */
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }
    /**
     * Public function to return the size of the Red-Black tree
     * @return size(root) function
     */
    public int size() {
        return size(root);
    }

    /**
     * Tree helper function
     * @return true if the tree is empty 
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     *  
     * @param key
     * @return a value for a given key
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // gets the value for a given key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }
    

   /**
    * Function that takes in data and creates a node  
    * @param key
    * @param val
    */ 
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    // helper function to put
    // takes in the root node and returns it with updated children
    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else              h.val   = val;

        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    /**
     * function to delete a node based off of a key parameter
     * @param key
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    // helper function to delete()
    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }


    /**
     * Delete the minimum key - value pair in the Red-Black tree
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // helper function to delete()
    // deletes the minimum key value pair 
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    /*
     * Helper Functions
     */
    
     // rotate a left node to the right
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // rotate a right node to the left
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flips the colors of a given node
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // moves a red node left
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // moves a red node right
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    //---------------------------

    /**
     * Function that helps balance the Red-Black tree
     * @param h node
     * @return a balanced node
     */
    private Node balance(Node h) {

        if (isRed(h.right) && !isRed(h.left))    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * Function to get the minimum key in teh Red-Black tree
     * @return the minimum key
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    /**
     * Function to get the maximum key in the Red-Black tree 
     * @return the maximum key 
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }
    private Node max(Node x) {
        // assert x != null;
        if (x.right == null) return x;
        else                 return max(x.right);
    }

    /**
     * These are the iterable functions taken from the book. 
     * I didnt use these, instead i made my own printTree() function
     * 
     * @return a queue of keys
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return new LinkedList<Key>();
        return keys(min(), max());
    }
    /**
     * helper function to keys()
     * makes a new queue to add the keys into 
     * @param lo
     * @param hi
     * @return a queue of keys
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    /**
     * adds all of the keys in the Red-Black tree to a Queue<Key>
     *  
     * 
     * @param x
     * @param queue
     * @param lo key in the RB tree
     * @param hi key in the RB tree
     */
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    //-------------------------------------------


    /**
     * Calls inOrder() with the root node as paramater
     */
    public void printTree() {
        inOrder(root);
    }

    /**
     * prints the Red-Black tree with an inOrder traversal
     * @param root node
     */
    public void inOrder(Node node)  {  
        if(node != null){  
          String temp = "";
          if(node.color == RED) {
              temp = "Red";
          } else if(node.color == BLACK) {
              temp = "Black";
          }
          System.out.println(node.key + "  " + node.val + " " + temp);
          inOrder(node.left);             
          inOrder(node.right);  
        }  
    }


    /**
     * 
     * test driver for lab 6 question 3. 
     * reads in a file and puts the data from a file into a Red-Black tree with a value starting at 1 and increasing at each letter read
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        driver2<String, Integer> st = new driver2<String, Integer>();

        Scanner sc = new Scanner( new File("lab6in2.txt"));

        String temp = "";
        int counter = 1;
        
        while(sc.hasNext()) {
            temp = sc.next();
            st.put(temp, counter);
            counter++;
        }

        st.printTree();

    }
}
