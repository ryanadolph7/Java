import java.io.*;
import java.util.*;

public class driver {


    public static void main(String[] args) throws FileNotFoundException {

        int cap = 26;

        Scanner sc = new Scanner( new File("inhash.txt"));
        
        String data = "";

        while(sc.hasNextLine()) {
            data = sc.nextLine();
            int length = data.length();
            boolean[] multiple = new boolean[cap];
            char[] checked = new char[cap];
            boolean isUnique = true;
            for(int j = 0; j < cap; j++) {
                multiple[j] = false;
            }

            for(int i = 0; i < 26; i++) {
                //System.out.println(multiple[i]);
            }
            
            System.out.println(data);

            for(int i = 0; i < length; i++) {
                Character temp = data.charAt(i); 
                
                int hash = temp.hashCode() % 26;   
                // i have the line read in, i need to get the hash value of each char
                // need to check if the current value at chars[i] == Integer.value(i)
                // if yes, do nothing
                // if no, turn unique[i] false && isUnique = false
            }
            
            System.out.println("");

            
        }



    }
}