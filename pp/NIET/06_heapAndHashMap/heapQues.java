import java.util.*;

public class heapQues {

    public static void kLargest(int n,int[] arr,int k){
        // min priority
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 1. add k elements in pq
        for(int i = 0; i < k ; i++) {
            pq.add(arr[i]);
        }
        // 2. add one more element is it is greater than peek element of pq and remove 
        //      peek element from pq
        for(int i = k; i < arr.length; i++) {
            if(arr[i] > pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }

        // 3. to achieve decreasing order, fill element in stack and then remove and print
        Stack<Integer> st = new Stack<>();
        while(pq.size() > 0) {
            st.push(pq.remove());
        }

        // 4. print stack
        while(st.size() > 0) {
            System.out.print(st.pop() + " ");
        }
    }

    public static void sortKSortedArray(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // add k element
        for(int i = 0; i < k; i++)
            pq.add(arr[i]);

        for(int i = k; i < arr.length; i++) {
            pq.add(arr[i]);
            System.out.println(pq.remove());
        }
        while(pq.size() > 0) {
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
            if(right.size() > 0 && val > right.peek()) {
                right.add(val);
            } else {
                left.add(val);
            }

            if(left.size() - right.size() == 2) {
                right.add(left.remove());
            } else if(right.size() - left.size() == 2) {
                left.add(right.remove());
            }
        }
    
        public int remove() {
            if(this.size() == 0) {
                System.out.println("Underflow");
                return -1;
            } else if(left.size() >= right.size()) {
                return left.remove();
            } else {
                return right.remove();
            }
        }
    
        public int peek() {
            if(this.size() == 0) {
                System.out.println("Underflow");
                return -1;
            } else if(left.size() >= right.size()) {
                return left.peek();
            } else {
                return right.peek();
            }
        }
    
        public int size() {
            return left.size() + right.size();
        }
    }




    public static class Car implements Comparable<Car> {
        int price;
        String name;

        public Car(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public int compareTo(Car o) {
            return this.price - o.price;
        }

        public void printDetails() {
            System.out.println(this.name + " : " + this.price);
        }

    }

    public static class Pair implements Comparable<Pair> {
        int data;
        int li; // list index
        int di; // data index

        public Pair(int data, int li, int di) {
            this.data = data;
            this.li = li;
            this.di = di;
        }

        public int compareTo(Pair o) {
            return this.data - o.data;
        }

    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int r = 0; r < lists.size(); r++) {
            pq.add(new Pair(lists.get(r).get(0), r, 0));
        } 
        while(pq.size() > 0) {
            Pair rem = pq.remove();
            res.add(rem.data);
            if(lists.get(rem.li).size() > rem.di + 1) {
                pq.add(new Pair(lists.get(rem.li).get(rem.di + 1), rem.li, rem.di + 1));
            }
        }
        return res;
    }


    public static void demo2() {
        Car c1 = new Car("A", 102547);
        Car c2 = new Car("B", 102157);
        Car c3 = new Car("D", 102477);
        Car c4 = new Car("F", 102598);
        Car c5 = new Car("R", 202547);
        Car c6 = new Car("T", 101964);

        // priority -> min priority
        // PriorityQueue<Car> pq = new PriorityQueue<>();

        // max priority
        PriorityQueue<Car> pq = new PriorityQueue<>(Collections.reverseOrder());

        pq.add(c1);
        pq.add(c2);
        pq.add(c3);
        pq.peek().printDetails();
        pq.add(c4);
        pq.peek().printDetails();
        pq.add(c5);
        pq.add(c6);

        while(pq.size() > 0) {
            Car rem = pq.remove();
            rem.printDetails();
        }
    }

    public static void demo() {
        // priority -> min (Default priority)
        // PriorityQueue<Integer> pq = new PriorityQueue<>();

        // priority -> max
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        pq.add(10);
        pq.add(15);
        pq.add(70);
        pq.add(100);
        pq.add(21);
        System.out.println("peek : " + pq.peek());
        pq.add(700);
        System.out.println("peek : " + pq.peek());

        while(pq.isEmpty() != true) {
            int rem = pq.remove();
            System.out.println(rem);
        }
    }

    public static void main(String[] args) {
        // demo();
        demo2();
    }
}
