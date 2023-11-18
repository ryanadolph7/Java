import java.util.TreeMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;


public class symbol_table<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> symbolTable; // underlying data structure

    public symbol_table() {
        symbolTable = new TreeMap<Key, Value>(); // initializing data structure
    }

    // returns if key is null
    // returns if value is null
    // inserts a new key, value pair into a tree map 
    public void put(Key key, Value value) {
        if(key == null) { // should throw illegalArgument error instead
            System.out.println("Calls put() with null value");
            return;
        } else if(value == null) {
            System.out.println("Calls put() with null value");
            return;
        } else {
            symbolTable.put(key, value);
        }
    }

    // returns if key is null
    // return the value of a specified key
    public Value get(Key key) {
        if(key == null) {
            System.out.println("Calls get() with null value");
            return null;
        } else {
            return symbolTable.get(key);
        }
    }

    // returns if key is null
    // otherwise removes a specified key
    public void delete(Key key) {
        if(key == null) {
            System.out.println("Calls delete() with null value");
            return;
        } else {
            symbolTable.remove(key);
        }
    }

    // returns the size of the symbol table
    public int size() {
        return symbolTable.size();
    }

    // returns if size == 0
    // otherwise returns the smallest key
    public Key min() {
        if(size() == 0) {
            System.out.println("Empty symbol table");
            return null;
        } else {
            return symbolTable.firstKey();
        }
    }

    // returns if size == 0
    // otherwise returns the largest key
    public Key max() {
        if(size() == 0) {
            System.out.println("Empty symbol table");
            return null;
        } else {
            return symbolTable.lastKey();
        }
    }


    public Iterable<Key> keys() {
        return symbolTable.keySet();
    }

    // iterator method 
    public Iterator<Key> iterator() {
        return symbolTable.keySet().iterator();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(""); // spacer 

        Scanner sc = new Scanner ( new File("lab3in.txt"));

        symbol_table<Integer, Integer> st = new symbol_table<>();

        int val1 = 0, val2 = 0;

        while(sc.hasNext()) {
            val1 = sc.nextInt();
            val2 = sc.nextInt();
            st.put(val1, val2);

            if(val1 == 5) {
                System.out.println("Key of " + val1  + " was found with a value of: " + st.get(5));
                st.delete(val1);
            } 
              
        }

        System.out.println("");
        System.out.println("Final table");
        for(Integer data : st.keys()) {
            System.out.println(data + " - " + st.get(data));
        }


        // need to get their values instead of the keys
        System.out.println("Minimun pair is: " + st.min() + " - " + st.get(st.min()));
        System.out.println("Maximum pair is: " + st.max() + " - " + st.get(st.max()));



        sc.close();
    }
    
}
