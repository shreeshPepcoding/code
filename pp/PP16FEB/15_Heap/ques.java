import java.util.*;

public class ques {

    public static void printKLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 1. add k elements in pq
        for (int i = 0; i < k; i++)
            pq.add(arr[i]);

        // 2. manage peek with greater elements
        for (int i = k; i < arr.length; i++) {
            if (pq.peek() < arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
        }

        // 3. print k elements
        while (pq.size() > 0) {
            int rem = pq.remove();
            System.out.println(rem);
        }
    }

    public static void kSortedArray(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 1. fill k elements ahead from 0
        for (int i = 0; i <= k; i++) {
            pq.add(arr[i]);
        }
        // 2. print removed element and add new element
        for (int i = k + 1; i < arr.length; i++) {
            int rem = pq.remove();
            System.out.println(rem);
            pq.add(arr[i]);
        }
        // 3. print remaining elements from pqueue
        while (!pq.isEmpty()) {
            int rem = pq.remove();
            System.out.println(rem);
        }
    }

    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianPriorityQueue() {
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }

        public void add(int val) {
            if (right.size() > 0 && val > right.peek()) {
                right.add(val);
            } else {
                left.add(val);
            }

            if (left.size() - right.size() == 2) {
                right.add(left.remove());
            } else if (right.size() - left.size() == 2) {
                left.add(right.remove());
            }
        }

        public int remove() {
            if (this.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            if (right.size() > left.size()) {
                return right.remove();
            } else {
                return left.remove();
            }
        }

        public int peek() {
            if (this.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            if (right.size() > left.size()) {
                return right.peek();
            } else {
                return left.peek();
            }
        }

        public int size() {
            return left.size() + right.size();
        }
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
        ArrayList<Integer> rv = new ArrayList<>();
  
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0]));
        int r = lists.size();
        for(int i = 0; i < r; i++) {
            pq.add(new int[]{lists.get(i).get(0), i, 0});
        }
        
        // At index 0-> data, 1-> row number, 2-> col number
        while(pq.size() > 0) {
            int[] rem = pq.remove();
            rv.add(rem[0]);
            if(rem[2] + 1 < lists.get(rem[1]).size()) {
                pq.add(new int[]{lists.get(rem[1]).get(rem[2] + 1), rem[1], rem[2] + 1});
            }
        }
        return rv;
    }

    public static void fun() {

    }

    public static class Car implements Comparable<Car> {
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

        while (!pq.isEmpty()) {
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
        // int rem = pq.peek();
        // pq.remove();

        // System.out.println(rem);
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
        // demo();
        fun();
    }
}