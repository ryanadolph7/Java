import java.util.*;
import java.io.*;

/**
 * Data structure called in driver2 file
 * 
 * Used to hold nodes containing data related to covid cases from 2020-2022
 * 
 * Compile - javac driver2.java
 * Execution - java driver2 
 * Dependencies - java.util.*, java.io.*
 * 
 * @author GeeksforGeeks 
 * <a> https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/# 
 * @author Software testing help 
 * <a> https://www.softwaretestinghelp.com/binary-search-tree-in-java/
 * @author Princton.edu
 * <a> https://algs4.cs.princeton.edu/32bst/BST.java.html
 * @since 10/5/2023
 */
public class BinarySearchTree2<E extends Comparable<E>> {

        Node<E> root;

        public static class Node<E extends Comparable<E>> {

            public E continent;
            public E location;
            public E date;
            public E total_cases;
            public E new_cases; // key
            public E population;
            public Node<E> right, left;
            public int size;
    

            public Node(E continent, E location, E date, E total_cases, E new_cases, E population, int size) {
                this.continent = continent;
                this.location = location;
                this.date = date;
                this.total_cases = total_cases;
                this.new_cases = new_cases;
                this.population = population;
                right = left = null;
                size = size;
            }

        }

        public BinarySearchTree2() {
            this.root = null;
        }
        /**
         * Insert function to put a new node into a Binary Search Tree 
         * Takes in as parameters, a root node and data for a new node
         * Recursively adds the node to the tree
         * 
         * @param root
         * @param continent
         * @param location
         * @param date
         * @param total_cases
         * @param new_cases
         * @param population
         */ 
        public void insert(Node<E> root, E continent, E location, E date, E total_cases, E new_cases, E population) {
            if(new_cases.compareTo(root.new_cases) < 0) {
                if(root.left != null) {
                    insert(root.left, continent, location, date, total_cases, new_cases, population);
                } else {
                    root.left = new Node<E>(continent, location, date, total_cases, new_cases, population, 1);
                }
            } else if(new_cases.compareTo(root.new_cases) > 0) {
                if(root.right != null) {
                    insert(root.right, continent, location, date, total_cases, new_cases, population);
                } else {
                    root.right = new Node<E>(continent, location, date, total_cases, new_cases, population, 1);
                }
            } else if(new_cases.compareTo(root.new_cases) == 0) {
                root.continent = continent;
                root.location = location;
                root.date = date;
                root.total_cases = total_cases;
                root.new_cases = new_cases;
                root.population = population;
            }
        }


    public void postOrder(Node<E> node)  
        {  
        if(node != null) {  
            postOrder(node.left);  
            postOrder(node.right);  
            System.out.println("New Cases: " + node.new_cases + " - Locatoin: " + node.location + " - Continent: " + node.continent);
      }  
    }  

    public void inOrder(Node<E> node)  
    {  
      if(node != null){  
        System.out.println("New Cases: " + node.new_cases + " - Locatoin: " + node.location + " - Continent: " + node.continent);
        inOrder(node.left);             
        inOrder(node.right);  
      }  
    }              
    /** Pre order print function for a binary tree
     * if root node is null, function does nothing
     * otherwise prints the binary tree in pre order form 
     * 
     * @param node 
    */

    public void preOrder(Node<E> node)  
    {  
      if(node != null) {  
        preOrder(node.left);  
        System.out.println("New Cases: " + node.new_cases + " - Location: " + node.location + " - Continent: " + node.continent);
        preOrder(node.right);  
      }  
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

    public Iterable<E> keys() {
        if(isEmpty()) {
            return new LinkedList<E>();
        } else {
            return keys(min(), max());
        }
    }

    public Iterable<E> keys(E lo, E hi) {
        if(lo == null) {
            return null;
        }
        if(hi == null) {
            return null;
        }
        Queue<E> q = new LinkedList<E>();
        keys(root, q, lo, hi);
        return q;
    }

    private void keys(Node x, Queue<E> q, E lo, E hi) {
        if(x == null) {
            return;
        }
        int cmplo = lo.compareTo((E)x.new_cases);
        int cmphi = hi.compareTo((E)x.new_cases);
        if(cmplo < 0) {
            keys(x.left, q, lo, hi);
        }
        if(cmplo <= 0 && cmphi >= 0) {
            q.add((E)x.new_cases);
        }
        if(cmphi> 0) {
            keys(x.right, q, lo, hi);
        }
    }

    public E min() {
        if(isEmpty()) {
            return null;
        }
        return min(root).new_cases;
    }
    
    private Node<E> min(Node<E> x) {
        if(x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    public E max() {
        if(isEmpty()) {
            return null;
        } else {
            return max(root).new_cases;
        }
    }

    public Node<E> max(Node x) {
        if(x.right == null){ 
            return x;
        } else {
            return max(x.right);
    }
}

    
}
