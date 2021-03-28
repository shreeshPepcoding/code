import java.util.*;

public class demoHeap {

    public static int kthLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < arr.length; i++) {
            if(i < k) {
                pq.add(arr[i]);
            } else {
                if(pq.peek() < arr[i]) {
                    pq.remove();
                    pq.add(arr[i]);
                }
            }
        }

        return pq.peek();
    }
    
    public static class Cars implements Comparable<Cars>{
        String name;
        int price;

        public Cars(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public int compareTo(Cars o) {
            return this.price - o.price;
        }
    }

    public static void demo2() {
        PriorityQueue<Cars> pq = new PriorityQueue<>(Collections.reverseOrder());


        pq.add(new Cars("A", 9));
        pq.add(new Cars("B", 10));
        pq.add(new Cars("C", 7));
        pq.add(new Cars("D", 12));
        pq.add(new Cars("E", 13));
        pq.add(new Cars("F", 8));

        // print price of cars with detail in sorted order
        while(pq.size() > 0) {
            Cars rem = pq.remove();

            System.out.println(rem.name + " {" + rem.price + "}");
        }   
    }

    public static void demo1() {

        // by default java have min priority
        // PriorityQueue<Integer> pq = new PriorityQueue<>();


        // how to convert max priority
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // pq.add(16);
        // pq.add(7);
        // pq.add(9);
        // pq.add(13);
        // pq.add(15);
        // pq.add(12);
        // pq.add(11);
        // pq.add(14);
        // pq.add(20);

        int[] arr = {16, 7, 9, 13, 15, 12, 11, 14, 20};
        System.out.println(kthLargest(arr, 3));
        

        // while(pq.size() > 0) {
        //     int val = pq.peek();
        //     pq.remove();
        //     System.out.println(val);
        // }

    }

    public static void main(String[] args) {
        // demo1();
        demo2();
    }
}
