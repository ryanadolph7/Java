
import java.util.Scanner;
import java.util.Stack;
import java.io.*;
public class StackExample {
    public static void main(String[] args) throws FileNotFoundException {
                                           // need this instead of try / catch for file
        Scanner in = new Scanner(System.in);
        Scanner infile = new Scanner(new File("tinyTale.txt"));
        
        String item;
        
        Stack<String> stack = new Stack<String>();


        while(infile.hasNext()) {
            item = infile.next();
            if(!item.equals("-")) {
                stack.push(item);
            } else if(stack.isEmpty()) {
                System.out.println("BAD INPUT");
            } else {
                System.out.print(stack.pop() + " ");
            }
        }
        while(in.hasNext()){
           item = in.next();
        
            if (!item.equals("-")) {
                stack.push(item); 
            }
            else if (stack.isEmpty()) {
                System.out.println("BAD INPUT"); 
            }
            else {
                System.out.print(stack.pop() + " ");
            }
        }

        // print what's left on the stack
        System.out.print("\nLeft on stack: ");
        //for (String s : stack) {
        for (Object s : stack) {
            System.out.print(s + " ");
        }
        System.out.println();
        in.close();
    } 
} 
