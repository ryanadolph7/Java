import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
// probably dont need one of these <E extends Comarable<E>>...
public class BinarySearchTree<E extends Comparable<E>> {

    // BST root node declaration
    public Node<E> root;

    @SuppressWarnings("rawtypes")
    public static class Node<E extends Comparable> {
        E key;
        E value;
        Node left;
        Node right;

    // Node constructor
        public Node(E key, E value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }
    

    // constructor for binary search tree
    public BinarySearchTree() {
        this.root = null;
    }

    // insert function takes in a root, a new key and new value
    // then inserts the new key - value pair into the bst
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
        }
    }

    // pre order print function
    public void preOrder(Node<E> node)  
    {  
      if(node != null) {  
        preOrder(node.left);  
        System.out.print("Key: " + node.key + " - Value: " + node.value + " ");
        preOrder(node.right);  
      }  
    }  
    
    // in order print function
    public void inOrder(Node<E> node)  
    {  
      if(node != null){  
        System.out.println("Key: " + node.key + " - Value: " + node.value + " ");
        inOrder(node.left);             
        inOrder(node.right);  
      }  
    }              
    
    // post order print function
    public void postOrder(Node<E> node)  
    {  
      if(node != null) {  
        postOrder(node.left);  
        postOrder(node.right);  
        System.out.print("Key: " + node.key + " - Value: " + node.value + " ");
      }  
    }  

    // method that takes in a key and root parameter, 
    // searches for a given key to delete
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


            // i genuinely have no clue what is going on here
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


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner( new File("lab4in.txt"));

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
        //bst.inOrder(root);
        sc.close();
    }

}