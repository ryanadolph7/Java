// Taken from Algorithms, 4th edition by Segewick and Wayne

public class ThreeSumDoubling {

     private static final int MAXIMUM_INTEGER=1000000;
     
     /**
     * Returns the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}.
     *
     * @param  a the array of integers
     * @return the number of triples (i, j, k) with {@code i < j < k}
     *         such that {@code a[i] + a[j] + a[k] == 0}
     */
    public static int count(int[] a) {
        int n = a.length; // new int n is size of array a
        int count = 0;
        for (int i = 0; i < n; i++) { // loop 1
            for (int j = i+1; j < n; j++) { // loop 2
                for (int k = j+1; k < n; k++) { // loop 3
                    if (a[i] + a[j] + a[k] == 0) { // check if a at 3 values added together equals 0
                        count++; // if yes, count gets +1
                    }
                }
            }
        }
        return count; // return count
    } 
    /*
     * returns the time taken to run count(a)
     */
    
    public static double timeTrial(int n) { 
        int[] a = new int[n]; // make a new int array 
        for (int i = 0; i < n; i++) { 
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER); // call StdRandom.uniform(-1000000, 1000000))
                   // this is a file found in the book
        }
        StopWatchCPU timer = new StopWatchCPU(); // make a timer of type StopWatchCPU
        count(a); // call count(with the new array a)
        return timer.elapsedTime(); // return the timers public function .elapsedTime()
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()}
     * for arrays of size 250, 500, 1000, 2000, and so forth.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) { 
        double time = 0.0; // creating a time variable
        for (int n = 250; n < 8001; n += n) { // starting the list size of n at 250, then doubling until i hit 8000
            time = timeTrial(n); // setting time = to TimeTrial(size n), which calls count
            System.out.printf("%7d %f\n", n, time); // print n and the time it took to read random numbers into a list of size N
            
        }
        double running_time_with_n = (time/(8000.0 * 8000.0 * 8000.0)); // gets the time since we know we have 8k ints read, we need to divide time by 8000^3
        System.out.println("running time compared with N = " +
                                running_time_with_n + " x N cubed");
    } 
    
}
