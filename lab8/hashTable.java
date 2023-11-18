import java.io.*;
import java.util.*;

public class hashTable<Key, Value> {
    
    private static final int CAP = 5;
    private int m;
    private int n;

    private hashTable<Key, Value>[] ht;

    public class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }

    }

    public hashTable() {
        this(CAP);
    }

    public hashTable(int m) {
        this.m = m;
        ht = (hashTable<Key, Value>[]) new hashTable[CAP];
    }


    
}
