import java.util.*;

public class queue {

    public static void demo() {
        Queue<Integer> que = new ArrayDeque<>();
        // functionality of queue
        que.add(10);
        que.add(20);
        que.add(30);
        que.add(40);
        que.add(50);

        System.out.println(que);

        System.out.println(que.remove());
        System.out.println(que.peek());
        System.out.println(que);
    }

    public static void main(String[] args) {
         demo();
    }
}