import java.io.*;
import java.util.*;


public class bst<Key extends Comparable<Key>, E> {

    private bst.Node root; 

    public class Node {

        public E location;
        public Key population;
        public Node left, right;
        public int size;
    
        public Node(Key population, E location) {
            population = population;
            location = location;
            left = right = null;
        }

        
    };



    public bst() { 
        root = null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if(x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    public void put(Key key, E val) {
        if(key == null) {
            return;
        }
        if(val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, E val) {
        if(x == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.population);
        if(cmp < 0) {
            x.left = put(x.left, key, val);
        }
        if(cmp > 0) {
            x.right = put(x.right, key, val);
        }
        else {
            x.location = val;
        }

        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key) {
        if(key == null) {
            return;
        }
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if(x == null) {
            return null;
        }
        int cmp = key.compareTo(x.population);
        if(cmp < 0) { 
            x.left = delete(x.left, key);
        } else if(cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if(x.right == null) {
                return x.left;
            }
            if(x.left == null) {
                return x.right;
            }
            Node temp = x;
            x = min(temp.right);
            x.right = deleteMin(temp.right);
            x.left = temp.left;
        }
        x.size = size(x.left) + size(x.right);
        return x;
    }

    private Node deleteMin(Node x) {
        if(x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right);
        return x;
    }

    public Node min(Node x) {
        if(x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }


}