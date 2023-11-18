import java.util.*;


/**
 * Red Black Tree Data Structure for program 2
 * 
 * Compilation : javac driver.java
 * Execution : java driver
 * Dependencies : java.util.*, java.io.*
 * Data Files : co2.csv, sea_level.csv, temperature_anomaly.csv
 * 
 * @author Robert Sedgewick 
 * @author Kevin Wayne
 * <a href="https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html">Princeton.edu</a>
 * 
 */
public class redBlackTree<Key extends Comparable<Key>, Value extends Comparable<Value>> {

    private static boolean RED = true;
    private static boolean BLACK = false;

    private Node root;

    public class Node {
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

    // redBlackTree initializer
    public redBlackTree() {
    }

    // helper functions 
    // ------------------
    private boolean isRed(Node x) {
        if(x == null) {
            return false;
        }
        return x.color == RED;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private int size(Node x) {
        if(x == null) {
            return 0;
        }
        return x.size;
    }

    public int size() {
        return size(root);
    }

    // -------------------------

    // BST / RB Tree insert 

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

    private Node put(Node x, Key key, Value val) {
        if(x == null) {
            return new Node(key, val, RED, 1);
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0) {
            x.left = put(x.left, key, val);
        } else if(cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        if(isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if(isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if(isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    // insertion helper functions

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }



    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

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

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right) && !isRed(h.left))    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }


    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }


    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    // my attempt at trying to do the whole assignment with only 3 trees

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    public Iterable<Value> values() {
        if(isEmpty()) {
            return new LinkedList<Value>();
        }
        return values(min(), max());
    }

    private Iterable<Value> values(Key lo, Key hi) {
        if(lo == null) {
            return null;
        }
        if(hi == null) {
            return null;
        }
        Queue<Value> q = new LinkedList<Value>();
        values(root, q, lo, hi);
        return q;
    }

    private void values(Node x, Queue<Value> q, Key lo, Key hi) {
        if(x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) {
            values(x.left, q, lo, hi);
        }
        if((cmplo <= 0) && (cmphi >= 0)) {
            q.add(x.val);
        }
        if(cmphi > 0) {
            values(x.right, q, lo, hi);
        }
    }
    
    // -------------------------------

    public Iterable<Key> keys() {
        if (isEmpty()) return new LinkedList<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

}
