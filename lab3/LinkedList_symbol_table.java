import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LinkedList_symbol_table<Key extends Comparable<Key>, Value> {

    private int size; 
    private Node head;
    
    private class Node {
        private Key key;
        private Value value;
        private Node next;

        // node constructor
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        // helper function to set and get nexts nodes 
        public Node getNext() {
            return this.next;
        }

        public void setNext(Node endE) {
            this.next = endE;
        }
    
    }

    // linked list symbol table constructor
    public LinkedList_symbol_table() {
        head = null;
        size = 0;
    } 
    

    // need a replace function for when i call put
        // need to check if key is already in the st, if yes replace val
    /* 
    public boolean replace(Key key, Value value) {
        Node temp = head;
        if(key == null) {
            System.out.println("Calling replace() with a null key");
            return false;
        } else {
            while(temp.getNext() != null) {
                temp = temp.getNext();
                if(temp.key == key) {
                    temp.value = value;
                }
            }
            return true;
        }
    }
    */

    // inserts a new key value pair into the linked list
    public void put(Key key, Value value) {
        Node oldHead = head;
        head = new Node(key, value);
        head.next = oldHead;
        size++;
        
    }

    // returns null if key is null
    // otherwise returns the value of a given key 
    public Value get(Key key) {
        Node temp = head;
        if(key == null) {
            System.out.println("Calling get() with null value");
            return null;
        } else {
            while(temp.getNext() != null) {
                temp = temp.getNext();
                if(temp.key == key) {
                    return temp.value;
                }
            }
        }
        System.out.println("Key not in list");
        return null;

    }

    // Prints each key - value pair in the linked list
    public void print() {
        Node temp = head;
        while(temp != null) {
            System.out.println(temp.key + " - " + temp.value);
            temp = temp.getNext(); // updating temp to get the next value
        }
    }
    
    // returns the size of the linked list
    public int size() {
        return size;
    }

    public Key min() {
        Node temp = head;
        Key minKey = temp.key; 
        while(temp.getNext() != null) {
            // probably shouldnt cast this since it needs to be generic
            if((Integer)temp.getNext().key < (Integer)minKey) {
                minKey = temp.getNext().key;
            }  
            temp = temp.getNext();
        }
        return minKey;
    }
    
    public Key max() {
        Node temp = head;
        Key maxKey = temp.key; 

        while(temp.getNext() != null) {
            // probably shouldnt cast this since it needs to be generic
            if((Integer)temp.getNext().key > (Integer)maxKey) {
                maxKey = temp.getNext().key;
            } else {
                temp = temp.getNext();
            }
        }
        return maxKey;
    }
    
    // delete was throwing a null pointer error, need to fix this function
    // could just implement an interator??
    public void delete(Key key) {
        Node temp = head;
        if(key == null || temp == null) {
            System.out.println("Calling delete() with a null key");
            return;
        } else if (size == 1) {
            head = null;
            size--;
            return; 
        } else if(size == 2) {
            temp.next = null;
        } else {
            while(temp.getNext() != null) {
                if(temp.key == key) { 
                    temp.next = temp.getNext(); 
                    size--;
                }
                temp = temp.getNext();
            }
        } 

    }
    

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner ( new File("lab3in.txt"));

        LinkedList_symbol_table<Integer, Integer> st = new LinkedList_symbol_table<>();
        
        // should probably check if i already have a key in the list, if yes just change that keys value 

        int val1 = 0, val2 = 0;
        while(sc.hasNext()) {
            val1 = sc.nextInt();
            val2 = sc.nextInt();
            st.put(val1, val2);
            //st.print();
            //System.out.println("");
            if(val1 == 5) {
                System.out.println("Key of " + val1 + " was found with a value of " + val2);
                st.delete(val1);
            }
        }

        System.out.println("");
        System.out.println("Unordered Symbol table");
        st.print();

        System.out.println("Miniumum pair found at " + st.min() + " with a value of " + st.get(st.min()));
        System.out.println("Maximum pair found at " + st.max() + " with a value of " + st.get(st.max()));
        
        sc.close();
    }
}
// this program doesnt run correctly, for some reason it only deletes the first 5 that shows up in the symbol table
// it also doesnt run a replace function to replace all key - value pairs that are already in the symbol table 

