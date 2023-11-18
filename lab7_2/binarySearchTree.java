import java.util.LinkedList;
import java.util.Queue;

public class binarySearchTree<E extends Comparable<E>, Value> {

        Node<E> root;

        // Node class 
        public static class Node<E extends Comparable<E>> {

            public E continent;
            public E location;
            public E date;
            public E total_cases;
            public E new_cases; 
            public E population; // key
            public Node<E> right, left;
            public int size;
    

            public Node(E continent, E location, E date, E total_cases, E new_cases, E population, int size) {
                this.continent = continent;
                this.location = location;
                this.date = date;
                this.total_cases = total_cases;
                this.new_cases = new_cases;
                this.population = population;
                right = left = null;
                this.size = size;
            }

        }

        // initializing an empty BST
        public binarySearchTree() {
            this.root = null;
        }

        /**
         * Insert function for BST
         * retuns new Node in BST, sorted on new_cases 
         * 
         * @param root
         * @param continent
         * @param location
         * @param date
         * @param total_cases
         * @param new_cases
         * @param population
         */
        public void insert(Node<E> root, E continent, E location, E date, E total_cases, E new_cases, E population) {
            if(new_cases.compareTo(root.new_cases) < 0) {
                if(root.left != null) {
                    insert(root.left, continent, location, date, total_cases, new_cases, population);
                } else {
                    root.left = new Node<E>(continent, location, date, total_cases, new_cases, population, 1);
                }
            } else if(new_cases.compareTo(root.new_cases) > 0) {
                if(root.right != null) {
                    insert(root.right, continent, location, date, total_cases, new_cases, population);
                } else {
                    root.right = new Node<E>(continent, location, date, total_cases, new_cases, population, 1);
                }
            } else if(new_cases.compareTo(root.new_cases) == 0) {
                root.continent = continent;
                root.location = location;
                root.date = date;
                root.total_cases = total_cases;
                root.new_cases = new_cases;
                root.population = population;
            }
        }



    // Level order print function for BST 
    public void levelOrder(Node<E> root) {
        Queue<Node<E>> q = new LinkedList<Node<E>>();
        q.add(root);
        System.out.println("");
        while(!q.isEmpty()) {
            Node<E> temp = q.poll();
            
            System.out.println("Population: " + temp.population +  " - New Cases: " + temp.new_cases + " - Location: " + temp.location + " - Date: " + temp.date + " - Total Cases: " + temp.total_cases);
            if(temp.left != null) {
                q.add(temp.left);
            }
            if(temp.right != null) {
                q.add(temp.right);
            }
        }
    }

}