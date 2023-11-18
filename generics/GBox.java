import java.util.*;

public class GBox<Item> {
    private Item bx;

    public void set(Item item) { this.bx = item; }
    public Item get() { return bx; }

    public static void main(String args[]) {

        GBox<String> b = new GBox<String>();
        b.set("square");
        String sb = b.get();

        System.out.println("sb is " + sb);
        return;
    }
}
