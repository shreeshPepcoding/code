import java.util.ArrayList;

public class arrayListDemo {

    public static void demo() {
        ArrayList<Integer> list = new ArrayList<>();
        // ArrayList<According to requirement> alName = new ArrayList<>();

        // Addition / push in arraylist
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(0, -10);
        // display
        System.out.println(list + " " + list.size());

        // removal / pop
        // case 1 : first remove
        int num = list.remove(0);
        System.out.println(list);
        // case 2 : last remove
        list.remove(list.size() - 1);
        System.out.println(list);
        // case 3 : remove from ith index
        int indx = 2;
        list.remove(indx);
        System.out.println(list);

        // manual printing = how to get in arrayList
        for (int i = 0; i < list.size(); i++) {
            int val = list.get(i); // arr[i]
            System.out.print(val + " ");
        }
        System.out.println();
        
        System.out.println(list.isEmpty());
    }

    public static void main(String[] args) {
        demo();
    }
}
