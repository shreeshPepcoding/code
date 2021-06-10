import java.util.*;

public class ques {

    public static void printKLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 1. add k elememnts
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }
        // 2. process remaining element
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        // 3. print k elements from priority queue
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }
    }

    public static void printKSorted(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 1. add k elememnts
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        // 2. manage remaining elements
        for (int i = k; i < arr.length; i++) {
            // add
            pq.add(arr[i]);
            // remove
            System.out.println(pq.remove());
        }

        while (pq.size() > 0) {
            System.out.println(pq.remove());
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
            if(left.size() > 0 && left.peek() < val) {
                right.add(val);
            } else {
                left.add(val);
            }

            // differenece management
            if(left.size() - right.size() == 2) {
                right.add(left.remove());
            } else if(right.size() - left.size() == 2) {
                left.add(right.remove());
            }
        }

        public int remove() {
            if(left.size() + right.size() == 0) {
                System.out.println("Underflow");
                return -1;
            } else if(right.size() > left.size()) {
                int data = right.remove();
                return data;
            } else {
                int data = left.remove();
                return data;
            }
        }

        public int peek() {
            if(left.size() + right.size() == 0) {
                System.out.println("Underflow");
                return -1;
            } else if(right.size() > left.size()) {
                return right.peek();
            } else {
                return left.peek();
            }
        }

        public int size() {
            return left.size() + right.size();
        }
    }

    public static void Question() {

    }

    public static void demo() {
        // default priority -> min
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(20);
        pq.add(9);
        System.out.println("peek : " + pq.peek());
        pq.add(7);
        pq.add(11);
        System.out.println("peek : " + pq.peek());
        while (pq.size() > 0) {
            int rem = pq.remove();
            System.out.println(rem);
        }

        System.out.println();
        // max prioority
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        pq1.add(10);
        pq1.add(9);
        System.out.println("peek : " + pq1.peek());
        pq1.add(7);
        pq1.add(11);
        pq1.add(20);
        System.out.println("peek : " + pq1.peek());
        while (pq1.size() > 0) {
            // System.out.println(pq1);
            int rem = pq1.remove();
            System.out.println(rem);
        }

    }

    public static void main(String[] args) {
        // demo();
        Question();
    }
}