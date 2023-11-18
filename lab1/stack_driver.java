import java.util.Scanner;
import java.io.*;

public class stack_driver {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        System.out.println("");
        // the code below is for question 2: Hamlet file question
        stack<String> Sstack = new stack<String>();
        
        Scanner hScanner = new Scanner( new File("hamlet.txt")); 
        
        String hamFile = ""; // string to hold the data from the txt file
        String popped = "";
        while(hScanner.hasNext()) {
            hamFile = hScanner.next(); 
            if(hamFile.equals("$")) {
                popped += Sstack.pop() + " ";
                //System.out.print("Current stack: ");
            } else {
                Sstack.push(hamFile);
                //System.out.print("Current stack: ");
            } 
            //Sstack.printStack();
        }
        // System.out.println("Popped: " + popped);
        System.out.print("Left on the stack: ");
        Sstack.printStack();
        System.out.println("-----------------------------------");

        // ----------------------------------------------------
        // the code below is for question 2: Postfix equation
        stack<Integer> Istack = new stack<Integer>();

        Scanner eScanner = new Scanner( new File("equation.txt"));

        String postFile = ""; // string to hold the data from the txt file 
        int num = 0;
        int result = 0;
        while(eScanner.hasNext()) {
            postFile = eScanner.next(); 
            if(!postFile.equals("*") && !postFile.equals("+")) {
                num = Integer.valueOf(postFile);
                Istack.push(num);
            } else {
                if(postFile.equals("*")) {
                    int num1 = num;
                    Istack.stackPop();
                    int num2 = Istack.peek();
                    result = num1 * num2;
                    Istack.stackPop();
                    Istack.push(result);
                } else if(postFile.equals("+")) {
                    int num1 = Istack.peek();
                    Istack.stackPop();
                    int num2 = Istack.peek();
                    Istack.stackPop();
                    result = num1 + num2;
                    Istack.push(result);
                }
            }
        }
        System.out.print("The result of the postfix equation is: ");
        Istack.printStack();
        System.out.println("");
    

    }
}
