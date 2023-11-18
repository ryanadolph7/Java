import java.util.*;
import java.io.*;
/**
 * Program that takes in input and returns a binary tree in a key - value node style 
 * Compile - javac BinarySerachTree.java
 * Execution - java BinarySearchTree
 * Dependencies - java.util.*, java.io.*
 * Data files - lab5in.txt
 * 
 * lab5in.txt - V E R Y E A S Y Q U E S T I O N
 * 
 * @author GeeksforGeeks 
 * <a> https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/# 
 * @author Software testing help 
 * <a> https://www.softwaretestinghelp.com/binary-search-tree-in-java/
 * @author Princton.edu
 * <a> https://algs4.cs.princeton.edu/32bst/BST.java.html
 * @since 10/5/2023
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<E extends Comparable<E>> {

    // BST root node declaration
    public Node<E> root;

    @SuppressWarnings("rawtypes")
    public static class Node<E extends Comparable<E>> {
        E key;
        E value;
        Node left;
        Node right;

        // Node constructor
        /**
         * 
         * @param key
         * @param value
         */
        public Node(E key, E value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }
    
    /** constructor for binary search tree
     * 
     */
    public BinarySearchTree() {
        this.root = null;
    }


    /** insert function takes in a root node, a new key and new value
     * then inserts the new key - value pair into the bst
     * 
     * @param node
     * @param key
     * @param value
     */

    // need to check if the key is already in the tree, if yes just replace value 
    // if no then we can make a new node in correct tree spot
    @SuppressWarnings("rawtypes")
    public void insert(Node<E> node, E key, E value) {
        if(key.compareTo(node.key) < 0) {
            if(node.left != null) {
                insert(node.left, key, value);
            } else {
                node.left = new Node(key, value);
            }
        } else if(key.compareTo(node.key) > 0) {
            if(node.right != null) {
                insert(node.right, key, value);
            } else {
                node.right = new Node(key, value);
            }
        } else if(key.compareTo(node.key) == 0) {
            node.value = value;
        }
    }

    /** Pre order print function for a binary tree
     * if root node is null, function does nothing
     * otherwise prints the binary tree in pre order form 
     * 
     *@param node 
     */
    public void preOrder(Node<E> node)  
    {  
      if(node != null) {  
        preOrder(node.left);  
        System.out.println("Key: " + node.key + " - Value: " + node.value);
        preOrder(node.right);  
      }  
    }  
    
    /** In Order print function for a binary tree
     * if root node is null, function does nothing
     * otherwise prints the binary tree in in order form
     * 
     * @param node
     */
    public void inOrder(Node<E> node)  
    {  
      if(node != null){  
        System.out.println("Key: " + node.key + " - Value: " + node.value);
        inOrder(node.left);             
        inOrder(node.right);  
      }  
    }              
    
    /** Post order print function for a binary tree
     * if root node is null, function does nothing
     * otherwise prints the binary tree in post order form
     * 
     * @param node
     */
    public void postOrder(Node<E> node)  
    {  
      if(node != null) {  
        postOrder(node.left);  
        postOrder(node.right);  
        System.out.println("Key: " + node.key + " - Value: " + node.value);
      }  
    }  


    /** Level order print functoin for a binary tree
     * if root node is null, function does nothing
     * otherwise prints the binary tree in level order form
     * 
     * @param root
     */
    public void levelOrder(Node<E> root) {
        Queue<Node<E>> q = new LinkedList<Node<E>>();
        q.add(root);
        while(!q.isEmpty()) {
            Node<E> temp = q.poll();
            System.out.println("Key: " + temp.key + " - Value: " + temp.value);
            if(temp.left != null) {
                q.add(temp.left);
            }
            if(temp.right != null) {
                q.add(temp.right);
            }
        }
    }

    /** Delete method that takes in a root and Key to search for
     * removes a node based on key comparison, if keys match, then the node gets removed
     * otherwise, the function returns the root node
     * 
     * @param node
     * @param key
     * @return
     */
    public Node<E> delete(Node<E> node, E key) {
        // if the node is null, just return the node
        if(node == null) {
            return node;
        }
        // recursively calling the function to get to the node we want 
        if(node.key.compareTo(key) > 0) {
            node.left = delete(node.left, key);
            return node;
        } else if(node.key.compareTo(key) < 0) {
            node.right = delete(node.right, key);
            return node;
        }
        // we are at the node we want 
        if(node.left == null) { // if the left child is null, right is the only leaf
            Node<E> temp = node.right;
            return temp;
        } else if(node.right == null) { // if the right child is null, left is the only leaf
            Node<E> temp = node.left;
            return temp;
        } else { // current leaf has both children
            Node<E> succesorParent = node; // we're on the current node we want to delete
            Node<E> succ = node.right; // get the deletion nodes right leaf key

            while(succ.left != null) {
                succesorParent = succ;
                succ = succ.left;
            }
            if(succesorParent != node) {
                succesorParent.left = succ.right;
            } else {
                succesorParent.right = succ.right;
            }
            node.key = succ.key;
            return node;
        }
    }

    /** Main "driver" function that reads in data from a file "lab5in.txt", then inserts each string into a binary tree
     * Calling 4 print functions in main, postOrder, preOrder, levelOrder, inOrder 
     * 
     * throws FileNotFoundException if file is not found in told directory
     * 
     * @param args
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner( new File("lab5in.txt"));

        @SuppressWarnings("rawtypes")
        BinarySearchTree bst = new BinarySearchTree(); // construct an empty Binary Search Tree

        String text = "";
        int valueCounter = 1;
        text = sc.next();
        @SuppressWarnings("rawtypes")
        Node root = new Node(text, valueCounter * 5); // create the root node
        valueCounter++;
        while(sc.hasNext()) { // fill the BST with values from input file
            text = sc.next();
            bst.insert(root, text, valueCounter * 5);
            valueCounter++;
        }
        
        System.out.println("In order traversal");
        bst.inOrder(root);
        System.out.println("");
        System.out.println("Post Order traversal");
        bst.postOrder(root);
        System.out.println("");
        System.out.println("Pre order traversal");
        bst.preOrder(root);
        System.out.println("");
        System.out.println("Level order traversal");
        bst.levelOrder(root);
        System.out.println("");
        sc.close();
    }

}