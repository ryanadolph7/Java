import java.util.*;
import java.io.*;


/**
 *  Driver function to process real world covid data 
 *  
 *  Compile - javac driver2.java
 *  Execution - java driver2
 *  Data files - "owid-covid-data.csv"
 * 
 *  Other files - BinarySerachTree2.java, OrderedArrayMinQ.java, Node.java 
 * 
 * 
 * @since 10/18/2023    
 * 
 */
public class driver2 {

    // None of this is correct, I impemented a Binary Tree to hold every single line of the CSV
    // The BST isnt even in order, I plan on going to an office hour to see if I can figure something out

    public static void main(String[] args) throws FileNotFoundException {


        String file = "owid-covid-data.csv";
        String temp = "";

        BinarySearchTree2 bst = new BinarySearchTree2();
        BinarySearchTree2.Node root = null;
        LinkedList<Node> ll = new LinkedList<Node>();

        PriorityQueue<Long> testq = new PriorityQueue<>(100000);


        Scanner testsc = new Scanner( new File(file));
        Scanner newSc = new Scanner( new File(file));
        String newTemp = "";

        temp = testsc.nextLine();
        newTemp = newSc.nextLine();
        newTemp = newSc.nextLine();

        String[] root_split = newTemp.split(",");

        root = new BinarySearchTree2.Node(root_split[0], root_split[1], root_split[2], root_split[3], root_split[4], root_split[5], 1);

        while(testsc.hasNextLine()) {

            temp = testsc.nextLine();
            String[] split = temp.split(",");

            
            Node tNode = new Node(split[0], split[1], split[2], split[3], split[4], split[5]);

            Long cases = Long.parseLong(split[4]);

            bst.insert(root, split[0], split[1], split[2], split[3], split[4], split[5]);

            testq.add(cases);

        }


        bst.preOrder(root);


        while(!ll.isEmpty()) {
            Node xx = ll.poll();
        }        

    

        /**
         * need a minq to hold data
         * need a bst to hold the data after filtering through a minq 
         * how do i get the data? 
         * read in from the csv, need to skip the first line
         * 
         * save a line into a temp var, split the temp var into a String[]
         * should make a new node with the split var, 
         * instead, add new_cases per location to minq
         * add each node to Linked List 
         * 
         * gonna need a for loop and counter to index from
         * call ll.get() with the index of A node with a same # of new_cases as polled node.new_cases 
         *      it wont return the correct date / population / total_cases  
         * 
         * from getting the node with CORRECT new_cases
         * add that node into a BST by sorting by new_cases
         * 
         * finally, print the BST in postOrder() 
         * print the Continent: Location: New_cases: Total_case: Popoulation:
         * 
         * 
         */



    }

    

    
}
