import java.util.Scanner;
import java.io.*;

public class driver {
    
    public static void main(String[] args) throws FileNotFoundException{
    
        Scanner dIN = new Scanner( new File("dnums.txt")); // file reader for the double txt file 
        Scanner sIN = new Scanner( new File("strings.txt")); // file reader for the string txt file 

        lab1<Double> DLL = new lab1<Double>(); // making a double linked list called DLL
        lab1<String> SLL = new lab1<String>(); // making a String linked list called SLL 

        double data; // object to hold the data 
        String sdata = ""; // object to hold the string data
       
        int dcounter = 0;
        // inserting each double into the Linked list
        while(dIN.hasNextDouble()) {
            data = dIN.nextDouble(); 
            DLL.insert(data);
            dcounter++;
        }

        int scounter = 0;
        // inserting each String into its Linked List
        while(sIN.hasNext()) {
            sdata = sIN.next();
            SLL.insert(sdata);
            scounter++;
        }

        //System.out.println(DLL.goToHead());
        System.out.println(""); // spacer for print
        DLL.getCurData();
        System.out.println("");
        // print the Linked List full of doubles and delete
        for(int i = 0; i < dcounter; i++) {
        System.out.print("List is ");
        DLL.printLinkedList();
        DLL.Delete();
       } 

       System.out.println(""); // spacer for print

       //System.out.println(SLL.goToHead());
       SLL.getCurData();
       System.out.println("");
       // print the Linked List full of strings and delete 
       for(int i = 0; i < scounter; i++) {
        System.out.print("List is ");
        SLL.printLinkedList();
        SLL.Delete();
       }
    }
   
}