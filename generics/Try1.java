import java.util.*;

public class Try1 {
    public static void main(String args[]) {

        LinkedList list = new LinkedList();
        list.add(new Integer(1)); 
        // will give error because it doesn't know what
        // data type is to be returned
        Integer i = list.listIterator().next();

        return;
    }
}
