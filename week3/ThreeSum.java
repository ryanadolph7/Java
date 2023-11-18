// Taken from Algorithms, 4th ed by Sedgewick and Wayne
// Purpose: to show how this program as time O(n^3) runs slower
// with the bigger the input file
// java ThreeSum 1Kints.txt 1024
// java Threesum 2Kints.txt 2048
// ...

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ThreeSum {

 
    /**
     * Returns the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}.
     *
     * @param  a the array of integers
     * @return the number of triples (i, j, k) with {@code i < j < k}
     *         such that {@code a[i] + a[j] + a[k] == 0}
     */
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    } 

    public static void main(String[] args) throws FileNotFoundException  { 
        
        File file = new File(args[0]); // takes in file argument
        
        Scanner scanner = new Scanner(file); 
        
        int max = Integer.parseInt(args[1]); // takes in how many nums are in the txt file
        int [] a = new int[max];
        int i = 0;
        
        while (scanner.hasNextInt()) {
            a[i++] = scanner.nextInt();
        }

        StopWatch timer = new StopWatch();
        //StopWatchCPU timerCPU = new StopWatchCPU();
        int count = count(a);
        System.out.println("Elapsed wall time = " + timer.elapsedTime());
        //System.out.println("Elapsed CPU time = " + timerCPU.elapsedTime());
        System.out.println(count);
    } 
} 