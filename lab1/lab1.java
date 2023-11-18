
// Generic Linked List for lab 1 
public class lab1<E> {
             // i need to change the name of this from lab1 to LinkedListGenerics
    
    private Node<E> head;
    private Node<E> curr;
    private int size;

    private static class Node<E> {
        private E node;
        private Node<E> next; 
        
        public Node(E newE) { // Node constructor
            this.node = newE;
            this.next = null;
        }

        // two basic functions that help set next pointers 
        public Node<E> getNext() {
            return this.next;
        }
        
        public void setNext(Node<E> endE) {
            this.next = endE;
        }

    }

    public lab1() {
        head = null;
        curr = head;
        size = 0;
    }
 
    // some code I changed to a generic LL data type from CSCI132, I can send in the driver file for that as well
	public void addToBack(Node<E> newNode) {
		
		if(this.head == null) { // check if the linked list is empty, if yes, the head == the new Node
			head = newNode;
            size++;
		}
		else {
			Node<E> temp = this.head;
			while(temp.getNext() != null) {
				temp = temp.getNext();	
		} // loop until we reach null, then set the next of the last node to the new node 
		temp.setNext(newNode); // setting the back of LL to the new node
        size++;
		}
		
	}

    public void getCurData() {
        System.out.println("The current data type of the data in this linked list is: " + curr.node.getClass().getSimpleName());
    }

    public void insert(E item) {
        Node<E> oldhead = head; // temp variable to hold the old head node 
        head = new Node<E>(item);
        head.next = oldhead; // pointer the original head node 
        curr = head;
        size++;
    }

    public void printLinkedList() {
		
		Node<E> curr = this.head; // new pointer that points to the first Node in the linked list
        String temp = "";
		while(curr != null) {
            temp += curr.node + " ";
			curr = curr.getNext(); // setting current Node to the next Node so while loop isn't infinite
		}
        System.out.println(temp);
	}

    public void Delete(){  
        if(this.head != null) {
           this.head = this.head.getNext(); // updating the head to whatever is next 
           size--;
        }
    }
    
    public void printFirst() {
        if(head != null) { // check if the first node is null
            System.out.println(head.node); // there is a node, so ill print the data in that node 
        }
    }

    public E goToHead() {      
        if(size != 0) {
            return head.node;
        } else {
            return null;
        }
        
    }
}