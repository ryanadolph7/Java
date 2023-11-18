
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class StackExampleFile {
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner infile = new Scanner(new File("tobe.txt")); 

        String item;
        
        StackOfStrings stack = new StackOfStrings();
        
        while(infile.hasNext()){
           item = infile.next();
        
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
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    } 
} 
