import java.io.*;
import java.util.*;

@SuppressWarnings("rawtypes")
public class driver<E extends Comparable<E>> {
    
    // need to read from the file saving the top 3 new cases of each new location
    // save top 3 cases for each location in minq
    // after 3 are found, put them into a bst
    // print the bst from least to greatest of new cases

    public static void main(String[] args) throws FileNotFoundException {

        String file = "owid-covid-data.csv";
        int size = 1;

        BinarySearchTree bst = new BinarySearchTree<>();
        BinarySearchTree.Node root = null; 
        BinarySearchTree.Node node_temp = null;
        minPQ2 minpq = new minPQ2<>();

        Node tester = null;

        String temp = "";

        Queue<Node> q = new LinkedList<Node>();

        test_minpq<Node> testq = new test_minpq<>();

        Scanner sc = new Scanner( new File(file));

        sc.nextLine();
        temp = sc.nextLine();
        String[] root_split = temp.split(",");

        root = new BinarySearchTree.Node(root_split[0], root_split[1], root_split[2], root_split[3], root_split[4], root_split[5]);
        node_temp = new BinarySearchTree.Node(root_split[0], root_split[1], root_split[2], root_split[3], root_split[4], root_split[5]);

        tester = new Node(root_split[0], root_split[1], root_split[2], root_split[3], root_split[4], root_split[5]);

        PriorityQueue<Node> fuck = new PriorityQueue<>(10000);

        while(sc.hasNextLine()) {
            size++;

            temp = sc.nextLine();
            String[] temp_split = temp.split(",");

            Node shit_node = new Node(temp_split[0], temp_split[1], temp_split[2], temp_split[3], temp_split[4], temp_split[5]);
            
            //fuck.add(shit_node.new_cases);
            //testq.insert((Node)shit_node.new_cases);
             

            q.add(shit_node);
             
            //minpq.insert(shit_node);
            
            //q.add(shit_node);
        }




        while(!q.isEmpty()) {
            Node xx = q.poll();
            System.out.println("Continent: " + xx.continent + " - Location: " + xx.location + " - New Cases: " + xx.new_cases + " - Total Cases: " + xx.total_cases + " - Population: " + xx.population);
        }


    }
}