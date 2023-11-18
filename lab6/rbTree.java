import java.io.*;
import java.util.*;


/**
 * Driver and data structure for lab 6 problem 2
 * 
 * Compilation: javac driver2.java
 * Execution: java driver2
 * Dependencies: java.util.*, java.io.*
 * Data Files: lab6in.txt
 * 
 * % lab6in.txt
 * int xvar;
int yvar;
int zvar;
int max;
int var;
int min;
int rbtree_size;
int dimension;
float rfl;
float sfl;
float tfl;
float temperature;
float area;
float circumference;
xvar = 5;
yvar = 7;
rfl = 5.5;
sfl = 6.5;
max = 1000;
var = 12;
min = 1;
rbtree_size = 10000;
temperature = 98.6;
area = 32.8;
 * 
 * 
 * @author Robert Sedgewick, Kevin Wayne
 * @author Ryan Adolph, CSCI 232, lab 6 question 3
 * <a href="https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html">Princten university</a>
 * @since 10/24
 */
public class rbTree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root; 

     
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }

    }

    // create an empty red-black tree
    public rbTree() {
    }

    // ---------------------------

    /**
     * Node helper function 
     * 
     * @param x
     * @return true if the nodes color is red, false otherwise
     */
    private boolean isRed(Node x) {
        if(x == null) {
            return false;
        }
        return x.color == RED;
    }
    
    /**
    * Function that takes in data and creates a node  
    * @param key
    * @param val
    */
    public void put(Key key, Value val) {
        if(key == null) {
            return;
        }
        if(val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;

    }

    // helper function to put
    // takes in the root node and returns it with updated children
    private Node put(Node h, Key key, Value val) {
        if(h == null) {
            return new Node(key, val, RED, 1);
        }
        int cmp = key.compareTo(h.key);
        if(cmp < 0) {
            h.left = put(h.left, key, val);
        } else if(cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }
        // fix up any right leaning links
        if(isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if(isRed(h.left) && !isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if(isRed(h.left) && isRed(h.right)) { 
            flipColors(h);
        }
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    /**
     * function to delete a node based off of a key parameter
     * @param key
     */
    public void delete(Key key) {
        if(key == null) {
            return;
        }
        if(!contains(key)) {
            return;
        }
        if(!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if(!isEmpty()) {
            root.color = BLACK;
        }
    }

    
    // helper function to delete()
    private Node delete(Node h, Key key) {
        if(key.compareTo(h.key) < 0) {
            if(!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else{
            if(isRed(h.left)) {
                h = rotateRight(h);
            }
            if(key.compareTo(h.key) == 0 && (h.right == null)) {
                return null;
            }
            if(!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }
            if(key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    /* --------------------------------------------- 
        helper functions
    */

    /**
     * Function that helps balance the Red-Black tree
     * @param h node
     * @return a balanced node
     */
    private Node balance(Node h) {
        if(isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if(isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if(isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    // flip the colors 
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // rotate a left node to the right 
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right; 
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right);
        return x;
    }

    // rotate a right node to the left
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED; 
        x.size = h.size;
        h.size = size(h.left) + size(h.right);
        return x;
    }

    // move a red node to the left
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if(isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // move a red node to the right
    private Node moveRedRight(Node h) {
        flipColors(h);
        if(isRed(h.left.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // get the color of the node
    public boolean getColor(Node h) {
        return h.color;
    }
     
    // get the size of the RB tree
     public int size() {
        return size(root);
    }
    // helper function to size
    private int size(Node h) {
        if(h == null) {
            return 0;
        }
        return h.size;
    }

    // returns true if the RB tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    // returns the minimum key-value pair
    public Key min() {
        if(isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    // return the minimum node
    public Node min(Node h) {
        if(h.left == null) {
            return h;
        } else {
            return min(h.left);
        }
    }

    // return the max key 
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    // return the max node
    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }

    // returns a value of a given key 
    public Value get(Key key) {
        if(key == null) {
            return null;
        } else {
            return get(root, key);
        }
    }

    // helper function to get()
    private Value get(Node x, Key key) {
        while(x != null) {
            int cmp = key.compareTo(x.key);
            if(cmp < 0) {
                x = x.left;
            } else if(cmp > 0) {
                x = x.right; 
            } else {
                return x.val;
            }
        }
        return null;
    }

    // returns true if a given key is in the RB tree
    public boolean contains(Key key) {
        return get(key) != null;
    }


    /**
     * Delete the minimum key - value pair in the Red-Black tree
     */
    public void deleteMin() {
        if(isEmpty()) {
            return;
        }
        if(!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if(!isEmpty()) {
            root.color = BLACK;
        }
    }
    
    private Node deleteMin(Node h) {
        if(h.left == null) {
            return null;
        }
        if(!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    // ---------------------------------------

    

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
     * Calls inOrder() with the root node as paramater
     */
    public void printTree() {
        System.out.println("");
        inOrder(root);
    }
     
    public Iterable<String> keys() {
        if(isEmpty()) {
            return new LinkedList<String>();
        }
        return keys(min(), max());
        
    }

    public Iterable<String> keys(Key min, Key max) {
        if(min == null) {
            return null;
        }
        if(max == null) {
            return null;
        }

        Queue<String> q = new LinkedList<String>();
        keys(root, q, min, max);
        return q;
    }

    private void keys(Node root, Queue<String> q, Key min, Key max) {
        if(root == null) {
            return;
        }
        int cmplo = min.compareTo(root.key);
        int cmphi = max.compareTo(root.key);
        if(cmplo < 0) {
            keys(root.left, q, min, max);
        }
        String temp = "";
        if(root.color == RED) {
            temp = "Red";
        }else if(root.color == BLACK) {
            temp = "Black";
        }
        String sentence = "";
        if(cmplo <= 0 && cmphi >= 0) {
             sentence = root.key + " " + root.val  + " " + temp;
            q.add(sentence);
        }
    }
    
     /* ---------------------------------
      */


    /**
     * Test driver for lab 6 question 2
     * Reads in a file and places each line of data in the file into a Red-Black tree. If the varaiable is being declared
     * then it puts that variable into the RB tree with a value of 0.0
     * otherwise puts the value into the correct variable
     * @param args
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException{

        
        String file = "lab6in.txt";

        Scanner sc = new Scanner( new File(file));

        redBlackTree<String, Float> st = new redBlackTree<String, Float>();

        String temp = "";
        
        while(sc.hasNextLine()) {
            temp = sc.nextLine();
            String[] split = temp.split(" ");

            if(split[0].equals("int")) {
                String stringTemp = split[1].substring(0, split[1].length() - 1);
                st.put(stringTemp, 0f);
            } else if(split[0].equals("float")) {
                String stringTemp = split[1].substring(0, split[1].length() - 1);
                st.put(stringTemp, 0f);
            } else {
                //Float valTemp = Float.valueOf(split[2]);
                float valTemp = Float.valueOf(split[2].substring(0, split[2].length() - 1));
                //System.out.println(valTemp);
                st.put(split[0], valTemp);
            }
        }        

        st.printTree();
        

    }

}
