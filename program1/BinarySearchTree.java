import java.util.*;


public class BinarySearchTree<E extends Comparable<E>> {

    public Node<E> root;

    public static class Node<E extends Comparable<E>> {

        public E continent;
        public E location;
        public E date;
        public E total_cases;
        public E new_cases; // key
        public E population;
        public Node<E> right, left;
    
    
        public Node(E continent, E location, E date, E total_cases, E new_cases, E population) {
            this.continent = continent;
            this.location = location;
            this.date = date;
            this.total_cases = total_cases;
            this.new_cases = new_cases;
            this.population = population;
            right = left = null;
        }

        public void getContinent() {
            System.out.println("Continent: " + continent);
        }
        public void getLocation() {
            System.out.println("Location: " + location);
        }
        public void getDate() {
            System.out.println("Total cases: " + total_cases);
        }
        public void getNewCases() {
            System.out.println("New Cases: " + new_cases);
        }
        public void getPopuliation() {
            System.out.println("Population: " + population);
        }

    }
    
    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(Node<E> root, Node<E> key) {
        if(root.new_cases.compareTo(key.new_cases) < 0) {
            if(root.left != null) {
                insert(root.left, key);
            } else {
                root.left = key;
            }
        } else if(root.new_cases.compareTo(key.new_cases) > 0) {
            if(root.right != null) {
                insert(root.left, key);
            } else {
                root.right = key;
            }
        } else if(root.new_cases.compareTo(key.new_cases) == 0) {

            root.continent = key.continent;
            root.location = key.location;
            root.date = key.date;
            root.total_cases = key.total_cases;
            root.population = key.population;
        }
    }

    public void insert(Node<E> root, E new_cases, E continent, E location, E date, E total_cases, E population) {
        if(new_cases.compareTo(root.new_cases) < 0) {
            if(root.left != null) {
                insert(root.left, new_cases, continent, location, date, total_cases, population);
            } else {
                root.left = new Node<E>(continent, location, date, total_cases, new_cases, population);
            }
        } else if(new_cases.compareTo(root.new_cases) > 0) {
            if(root.right != null) {
                insert(root.right, new_cases, continent, location, date, total_cases, population);
            } else {
                root.right = new Node<E>(continent, location, date, total_cases, new_cases, population);
            }
        } else if(new_cases.compareTo(root.new_cases) == 0) {
            root.continent = continent;
            root.location = location;
            root.date = date;
            root.total_cases = total_cases;
            root.population = population;
        }
    }

    public Node<E> delete(Node<E> node, E new_cases) {
        if(node == null) {
            return node;
        }
        
        if(node.new_cases.compareTo(new_cases) > 0) {
            node.left = delete(node.left, new_cases);
            return node;
        } else if(node.new_cases.compareTo(new_cases) < 0) {
            node.right = delete(node.right, new_cases);
            return node;
        }
        
        if(node.left == null) { 
            Node<E> temp = node.right;
            return temp;
        } else if(node.right == null) { 
            Node<E> temp = node.left;
            return temp;
        } else {
            Node<E> succesorParent = node; 
            Node<E> succ = node.right; 
            while(succ.left != null) {
                succesorParent = succ;
                succ = succ.left;
            }
            if(succesorParent != node) {
                succesorParent.left = succ.right;
            } else {
                succesorParent.right = succ.right;
            }
            node.new_cases = succ.new_cases;
            return node;
        }
    }

    public void postOrder(Node<E> node)  
    {  
      if(node != null) {  
        postOrder(node.left);  
        postOrder(node.right);  
        System.out.println("Continent: " + node.continent + " - Location: " + node.location);
      }  
    }  

    public void levelOrder(Node<E> root) {
        Queue<Node<E>> q = new LinkedList<Node<E>>();
        q.add(root);
        while(!q.isEmpty()) {
            Node<E> temp = q.poll();
            System.out.println("Continenent: " + temp.continent + " - Location: " + temp.location + " - Date: " + temp.date + " - Total Cases: " + temp.total_cases + " - New Cases: " + temp.new_cases + " - Population: " + temp.population);
            if(temp.left != null) {
                q.add(temp.left);
            }
            if(temp.right != null) {
                q.add(temp.right);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public E getGeneric(String text) {
        return (E)text;
    }
    
}