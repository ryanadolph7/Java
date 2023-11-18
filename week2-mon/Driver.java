import java.util.Scanner;

class Driver {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        LinkedList<String> ll = new LinkedList<String>();
        // generic LL with string as its generic

        //int i = 0; // never used
        while(in.hasNext()){
           ll.insert(in.next());
        }
        System.out.print("List is ");
        while (ll.getCurData() != null) {
            System.out.print(ll.getCurData() + " ");
            if (ll.nextData() == false) {
                break;
            }            
        } 
        in.close();
    
    }
}
