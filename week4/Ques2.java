import java.util.Scanner;

public class Ques2 {
public static void main(String[] args) {

    int n = 4;  // don't count
    int num_instr = 0; // don't count

    int sum = 0; 
    ++num_instr;
    System.out.println("sum = 0");

    ++num_instr; // for i = n
    System.out.println("i = n");
    for (int i = n; i > 0; i--) {
        ++num_instr; // i > 0
        System.out.println("i > 0");

        ++num_instr; // j = 0
        System.out.println("j = 0");
	    for (int j = 0; j< i; j++) {
            ++num_instr; // j < i
            System.out.println("j < i");

		    sum++;
            ++num_instr; // sum++
            System.out.println("sum++");

            ++num_instr; // j++
            System.out.println("j++");
        }
        // add 1 for one more condition check for inner loop
        ++num_instr; // j < i
        System.out.println("one more for j < i");

        ++num_instr; // i--
        System.out.println("i--");
    }
    // add 1 for one more condition check in outer loop
    ++num_instr; // i > 0
    System.out.println("one more for i > 0");
    System.out.println("For n = 4, num_instr = " + num_instr);
}
}
