public class Node<E extends Comparable<E>> {

    public E continent;
    public E location;
    public E date;
    public E total_cases;
    public E new_cases;
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
