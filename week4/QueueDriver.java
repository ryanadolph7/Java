// Example13-6:QueueDriver.java
// Taken from Algorithms, 4th ed by Sedgewick and Wayne

import java.util.Scanner;

public class QueueDriver {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();

        while (in.hasNext()) {
            String item = in.next();
            if (!item.equals("-")) {
                queue.enqueue(item);
            }
            else if (!queue.isEmpty()) {
                System.out.print(queue.dequeue() + " ");
            }
        }
        System.out.println("(" + queue.size() + " left on queue)");
    }
} 
