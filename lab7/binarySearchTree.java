import java.util.*;
import java.io.*;

public class binarySearchTree<Key extends Comparable<Key>, E> {

    private Node root;

    public class Node {

        private E continent;
        private E location;
        private E date;
        private E total_cases;
        private E new_cases;
        private Key population;

        private Node left, right;
        private int size;

        public Node(E continent, E location, E date, E total_cases, E new_cases, Key population, int size) {
            continent = continent;
            location = location;
            date = date;
            total_cases = total_cases;
            new_cases = new_cases;
            population = population;
            size = size;
            right = left = null;
        } 
    }

    
    public binarySearchTree() {
    }
    
    public boolean isEmpty()  {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if(x == null) {
            return 0;
        }
        return x.size;
    }

    public E get(Key population) {
        return get(root, population);
    }

    private E get(Node x, Key population) {
        if(population == null) {
            return null;
        } 
        if(x == null) {
            return null;
        }
        int cmp = population.compareTo(x.population);
        if(cmp < 0) {
            return get(x.left, population);
        }
        if(cmp > 0) {
            return get(x.right, population);
        }
        else {
            return x.total_cases;
        }
    }

    public void delete(Key population) {
        if(population == null) {
            return;
        }
        root = delete(root, population);
    }
    
    private Node delete(Node x, Key population) {
        if(x == null) {
            return null;
        }
        int cmp = population.compareTo(x.population);
        if(cmp < 0) {
            x.left = delete(x.left, population);
        }else if(cmp > 0) {
            x.right = delete(x.right, population);
        } else {
            if(x.right == null) {
                return x.left;
            }else if(x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right);
        return x;
    }

    public void deleteMin() {
        if(isEmpty()) {
            return;
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if(x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right);
        return x;
    }

    public Key min() {
        if(isEmpty()) {
            return null;
        }
        return min(root).population;
    } 

    private Node min(Node x) {
        if(x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    public void put(E continent, E location, E date, E total_cases, E new_cases, Key population, int size) {
        if(population == null) {
            return;
        }
        root = put(root, continent, location,  date, total_cases, new_cases,  population, size);
    }

    private Node put(Node x, E contintent, E location, E date, E total_cases, E new_cases, Key population, int size) {
        if(x == null) {
            return new Node(contintent, location, date, total_cases, new_cases, population, size);
        }
        int cmp = population.compareTo(x.population);
        if(cmp < 0) {
            x.left = put(x.left, contintent, location, date, total_cases, new_cases, population, size);
        }
        if(cmp > 0) {
            x.right = put(x.right, contintent, location, date, total_cases, new_cases, population, size);
        }
        if(cmp == 0) {
            x.continent = contintent; 
            x.location = location; 
            x.date = date;
            x.new_cases = new_cases;
            x.population = population;
            x.size = size;
        }
        else {
            x.size = 1 + size(x.left) + size(x.right);
        }
        return x;
    }

    // this doesnt work, for some reason root is null when i call this
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new LinkedList<Key>();
        Queue<Node> nodes = new LinkedList<Node>();
        nodes.add(root);
        while(!nodes.isEmpty()) {
            Node temp = nodes.poll();
            if(temp == null) {
                return null;
            }
            keys.add(temp.population);
            nodes.add(temp.left);
            nodes.add(temp.right);
        }
        return keys;
    }

    
}
