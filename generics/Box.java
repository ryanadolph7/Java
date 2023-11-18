import java.util.*;

public class Box {
    private Object bx;

    public void set(Object object) { this.bx = object; }
    public Object get() { return bx; }

    public static void main(String args[]) {

        Box b = new Box();
        b.set("square");
        String sb = (String) b.get();

        System.out.println("sb is " + sb);
        return;
    }
}
