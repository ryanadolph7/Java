// Example 13-1 - ReverseStrings.java
// Taken from Algorithms 4th ed by Sedgewick and Wayne

import java.util.Scanner;

public class ReverseStrings {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        // how to change this to read in a file??
        
        StackOfStrings stack = new StackOfStrings();
        
        int i = 0;
        while(in.hasNext()){
           stack.push(in.next());
        }
        
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
    
}
