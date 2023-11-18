import java.io.*;
import java.util.*;
import java.util.function.BinaryOperator;


public class driver {

    /**
     * Main driver for lab 7 question 1
     * this program is completely wrong
     * 
     * @param args
     * @throws FileNotFoundException
     */


    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner( new File("owid-covid-data.csv"));

        //bst bst = new bst<>();
        binarySearchTree<String, Long> bst = new binarySearchTree<>();
        binarySearchTree.Node root = null;
        MinPQ<String> pq = new MinPQ<String>(); // data structure doesnt work

        String line = "";
        String[] lSplit = new String[6];

        sc.nextLine();
        line = sc.nextLine();
        lSplit = line.split(",");
        root = new binarySearchTree.Node(lSplit[0], lSplit[1], lSplit[2], lSplit[3], lSplit[4], lSplit[5], 1);

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            lSplit = line.split(",");
            String skey = lSplit[5];
            String location = String.valueOf(lSplit[1]);
            bst.insert(root, lSplit[0], lSplit[1], lSplit[2], lSplit[3], lSplit[4], lSplit[5]);

        }

        
        bst.levelOrder(root);


    }
    
}
