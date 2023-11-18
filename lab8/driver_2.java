import java.util.*;
import java.io.*;

public class driver_2 {

    public static final int CAP = 26;

    public static boolean isUnique(String data) {
        boolean[] in = new boolean[CAP];
        boolean[] multiple = new boolean[CAP];
        boolean unique = true;
        for(int i = 0; i < CAP; i++) {
            in[i] = false;
            multiple[i] = false;
        }

        for(int i = 0; i < data.length(); i++) {
            int hashCode = data.charAt(i) % 26;
            if(in[hashCode] == false) {
                in[hashCode] = true;
            } else if(in[hashCode] == true) {
                multiple[hashCode] = true;
                unique = false;
            }
        }
        
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}; 
        char[] uni = new char[CAP];
        for(int i = 0; i < CAP; i++) {
            if(in[i] && !multiple[i]) {
                uni[i] = chars[i];
            }
        }

        System.out.print("Unique characters are " );
        for(int i = 0; i < CAP; i++) {
            System.out.print(uni[i]);
        }
        System.out.println("");

        return unique;
    }


    /**
     * Main driver for lab 8 question 1 
     * provides the right output on whether the string is unique, but the unique characters are wrong on all of strings
     * 
     * @param args
     * @throws FileNotFoundException
     * @since 10/15/2023
     */
    public static void main(String[] args) throws FileNotFoundException {
        

        Scanner sc = new Scanner( new File("inhash.txt"));

        //char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}; 
        
        String data = "";

        while(sc.hasNextLine()) {
            data = sc.nextLine();
            System.out.println("Is string " + data + " unique? " + isUnique(data));
            System.out.println("");
        }


    }
    
}
