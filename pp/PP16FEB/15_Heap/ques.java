import java.util.*;

public class ques {


    public static class Car implements Comparable<Car>{
        int price;
        String name;
        int rating;

        public Car(String name, int price, int rating) {
            this.name = name;
            this.price = price;
            this.rating = rating;
        }

        public int compareTo(Car other) {
            // return this.price - other.price;
            return this.name.compareTo(other.name);
        }
    }

    public static void demo() {

        PriorityQueue<Car> pq = new PriorityQueue<>();

        pq.add(new Car("A", 100000, 8));
        pq.add(new Car("B", 254000, 8));
        pq.add(new Car("C", 745700, 7));
        pq.add(new Car("D", 112300, 6));
        pq.add(new Car("E", 354560, 9));

        while(!pq.isEmpty()) {
            Car rem = pq.peek();
            pq.remove();

            System.out.println(rem.name + " " + rem.price + " " + rem.rating);
        }


        // default -> min pririty
        // PriorityQueue<Integer> pq = new PriorityQueue<>();

        // for max priority
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // pq.add(10);
        // pq.add(40);
        // pq.add(50);
        // pq.add(70);
        // pq.add(1);
        // pq.add(11);
        // pq.add(17);

        // while(pq.size() > 0) {
        //     int rem = pq.peek();
        //     pq.remove();

        //     System.out.println(rem);
        // }



        // pq.add(10);
        // System.out.println(pq);
        // pq.add(20);
        // System.out.println(pq);
        // pq.add(9);
        // System.out.println(pq);
        // pq.add(7);
        // System.out.println(pq);
        
        // System.out.println(pq.remove());
        // pq.add(3);
        // System.out.println(pq.peek());
        // System.out.println(pq.remove());
        // System.out.println(pq.peek());
        // System.out.println(pq.remove());
        // System.out.println(pq.remove());
        // System.out.println(pq.remove());
        // System.out.println(pq.peek());
        // pq.add(10);
        // pq.add(10);
        // System.out.println(pq.peek());
        // System.out.println(pq.remove());
        // System.out.println(pq.remove());
    }

    public static void main(String[] args) {
        demo();
        // fun();
    }
}