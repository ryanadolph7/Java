import java.lang.Math;
import java.io.*;
import java.util.*;

public class driver {

    public static void main(String[] args) throws FileNotFoundException {

        int size = 8000; // can i hard code this in?
        double[] arr = new double[size];
        Scanner sc = new Scanner( new File("lab2in.txt"));
        int i = 0;
        while(sc.hasNextDouble()) {
            arr[i] = sc.nextDouble();
            i++;    
        }
        // not counting run time of reading in the file, right?
        // ----------------------------------------------------------

        Arrays.sort(arr); // O(n * log(n))
        int j = 0; // O(1)
        double val1 = arr[j]; // O(1)
        double val2 = arr[j + 1]; // O(1)
        double difference = Math.abs(val1 - val2); // O(1)

        for(j = 0; j < 7998; j++) { // O(n)
            if(Math.abs(arr[j + 1] - arr[j + 2]) < difference) { // O(1)
                val1 = arr[j + 1]; // O(1)
                val2 = arr[j + 2]; // O(1)
                difference = Math.abs(val1 - val2); // O(1)
            } 
        }
        
        // for some reason, changing from println to printf automatically rounds it to 6 decimals ?? weird but nice
        System.out.printf("The numbers %f and %f with a difference of %f\n", val1, val2, difference); // O(1)

        // T(n) = nLog(n) + 1 + 1 + 1 + 1 + n (1 + 1 + 1 + 1) + 1
        // Big O = O(n * log(n))

    }

}