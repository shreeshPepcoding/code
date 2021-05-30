import java.util.*;

public class demo {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
 
        list.addFirst(10);
        System.out.println(list);
        list.addFirst(20);
        System.out.println(list);
        list.addFirst(30);
        System.out.println(list);
        list.addFirst(40);
        System.out.println(list);
        list.addFirst(50);
        System.out.println(list);
        list.addFirst(60);
        System.out.println(list);
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list.size());
        System.out.println(list);
    }
}