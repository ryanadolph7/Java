import java.util.*;
import java.io.*;


public class MinPQ<E extends Comparable> {
    

    // cant use a binary heap
    // can i use an array list to resize?????
    // probably have to use array (check the slides i guess) 
    // need insert(), delMin()
    // needs to be generic so i can hold nodes

    // should i build it so that I compare off of some node value? 
    public ArrayList<Node> queue; 
    private int size;

    public MinPQ() {
        this.queue = new ArrayList<Node>(200);
        this.size = 0;
    }

    public void insert(Node node) {

        if(node == null) {
            //throw new NoSuchElementException("Calling insert with null element");
            System.out.println("what the hell");
            return;
        } else if(size == 1) { // if size is 1, just insert it at the end of the list
            queue.add(size, node);
            size++;
        }
        
        for(int i = 0; i < size; i++) {
           Node temp = queue.get(i + 1); // get the next node 
           if(temp.new_cases.compareTo(node.new_cases) < 0 ) { // check the next nodes new_cases and compare to new_nodes cases 
            temp = queue.get(i+2);
           } 
        }
        size++;
    }

    public void print() {
        for(int i = 0; i < size; i++) {
            System.out.println(queue.remove(i));
        }
    }



    /*
     * insert(Node, key) {
     *      if(key != null) {
     *          for(int i = 0; i < size + 1; i++) 
     *              if(array[i].key > key) 
     *                  keep iterating, need to get to spot where array[i + 1].key !> key
     *          }
     *      resize arraylist to be size + 1
     *      insert new node into correct spot
     * }
     *
     * delMin()  {
     *      need to just delete the first node and return it 
     * }
     * 
     * 
     */




}
