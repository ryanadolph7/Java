import java.util.*;
import java.io.*;

public class driver {

    public static class Node{

        private String continent, location, date;
        private long total_cases, new_cases, population;
        Node left, right;

        public Node(String continent, String location, String date, long total_cases, long new_cases, long population) {
            continent = continent;
            location = location;
            date = date;
            total_cases = total_cases;
            new_cases = new_cases; 
            population = population;
            left = right = null;
        }


    }


    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner sc = new Scanner( new File("owid-covid-data.csv"));
        binarySearchTree bst = new binarySearchTree<>();
        bst bsst = new bst<>();

        String temp = "";
        String[] temp_split = new String[6]; 
        sc.nextLine();
        while(sc.hasNextLine()) {
            temp = sc.nextLine();
            temp_split = temp.split(",");
            int key = Integer.parseInt(temp_split[5]);
            System.out.println(temp_split[0] + " " + temp_split[1] + " " + temp_split[2] + " " + temp_split[3] + " " + temp_split[4] + " " + temp_split[5]);  
            bsst.put(key, temp_split[1]); 
        }
    }
    
}
