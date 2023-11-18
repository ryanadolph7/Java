// some of this code is edited from my previous semester in CSCI132 from a random stack class to a generic stack class
public class stack<E> {

    private lab1<E> data = new lab1<E>(); // used the linked list class from the first question and just added onto it
    private E top_of_stack;
    private int size;

    // stack constructor
    public stack() {
        this.data = new lab1<E>(); // using the linked list class from the first question as the underlying data type
        this.top_of_stack = null;
        this.size = 0;
    }

    public void push(E newE) { // insert the new node into the stack
        data.insert(newE); 
        size++;
        top_of_stack = data.goToHead();
    }
    
    public void stackPop() { // wanted a different print output from the original pop() function that prints what was just popped 
        if(size == 0) {      // instead i wanted to just keep that hidden and print the result for postfix
            return;
        } else {
            data.Delete();
            top_of_stack = data.goToHead();
            size--;
        }
    }

    public String pop() { // takes the head node, prints the data then deletes it 
        if(size == 0) {
            return null;
        } else {
            String temp = "";
            this.data.Delete();
            top_of_stack = data.goToHead();
            size--;
            temp = (String) top_of_stack;
            return temp;
        }
    }
    
    public E peek() { // returns the data of the top node
        if(size != 0) {
            return top_of_stack;
        } else {
            return null;
        }
    }

    public void printStack() { // printing function to print the stack data
        if(size == 0) {
            return;
        } else {
            // need to add a getAt() function in lab1 LL
            data.printLinkedList();
        }

    }
}
