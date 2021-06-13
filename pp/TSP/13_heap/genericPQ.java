import java.util.*;

// Min Priority Queue
class priority_queue<K extends Comparable> {
    private ArrayList<K> data;
    private boolean flag;
    // flag = true -> it is max pririty
    // flag = false -> it is min priority

    public priority_queue() {
        data = new ArrayList<>();
        flag = false;
    }

    public priority_queue(boolean flag) {
        data = new ArrayList<>();
        this.flag = flag;
    }

    private void processArray(K[] arr) {
        // COMPLEXITY MORE THAN O(nlogn)
        // for(int i = 0; i < arr.length; i++) {
        //     add(arr[i]);
        // }


        // COMPLEXITY IS nearly O(n)
        // 1. fill data list from array
        for(K val : arr) {
            // add elements in main element
            data.add(val);
        }

        // 2. call downheapify from end
        for(int i = arr.length - 1; i >= 0; i--) {
            downheapify(i);
        }
    }

    public priority_queue(K[] arr) {
        data = new ArrayList<>();
        this.flag = false;
        processArray(arr);
    }

    public priority_queue(K[] arr, boolean flag) {
        data = new ArrayList<>();
        this.flag = flag;
        processArray(arr);
    }

    private int checkPriority(int ci, int pi) {
        // ci -> child index
        // pi -> parent index.

        if(flag == true) {
            // for max priority
            if(data.get(pi).compareTo(data.get(ci)) < 0) {
                return 1;
            }
        } else {
            // for min priority
            if(data.get(ci).compareTo(data.get(pi)) < 0 ) {
                return 1;
            }
        }
        return 0;
    }

    private void swap(int i, int j) {
        K temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    private void upheapify(int indx) {
        if(indx == 0) return;

        int pi = (indx - 1) / 2;
        if(checkPriority(indx, pi) > 0) { // priority of children is max than parent, swap
            swap(indx, pi);
            upheapify(pi);
        }
    }

    public void add(K val) {
        if(val == null) {
            System.out.println("Null pointer exception");
            return;
        }
        data.add(val);
        // maintain heap order property
        upheapify(data.size() - 1);
    }

    private void downheapify(int indx) {
        int minIndx = indx;
        int lci = 2 * indx + 1;
        int rci = 2 * indx + 2;
        // if(lci < data.size() && data.get(minIndx) > data.get(lci)) {
        if(lci < data.size() && checkPriority(lci, minIndx) > 0) {
            minIndx = lci;
        }
        // if(rci < data.size() && data.get(minIndx) > data.get(rci)) {
        if(rci < data.size() && checkPriority(rci, minIndx) > 0) {
            minIndx = rci;
        }
        if(minIndx != indx) {
           swap(indx, minIndx);
           downheapify(minIndx); 
        }
    }

    public K remove() {
        if(data.size() == 0) {
            System.out.println("Queue underflow");
            return null;
        }

        swap(0, data.size() - 1);
        K val = data.remove(data.size() - 1);
        // maintain heap order property
        downheapify(0);
        return val;
    }

    public K peek() {
        if(data.size() == 0) {
            System.out.println("Queue underflow");
            return null;
        }
        return data.get(0);
    }

    public int size() {
        return data.size();
    }

    public void display() {
        System.out.println(data);
    }
}


public class genericPQ {

    public static class Pair implements Comparable<Pair>{
        int val;

        public Pair(int val) {
            this.val = val;
        }

        public int compareTo(Pair other) {
            return this.val - other.val;
        }
    }

    public static void fun() {
        priority_queue<Pair> pq = new priority_queue<>(false);
        pq.add(new Pair(15));
        pq.add(new Pair(25));
        pq.add(new Pair(15));
        pq.add(new Pair(10));
        pq.add(new Pair(15));
        pq.add(new Pair(5));
        pq.add(new Pair(100));

        // int[] arr = {15, 25, 15, 10, 15, 5, 100};
        // priorityqueue pq = new priorityqueue(arr, false);

        pq.add(new Pair(105));
        pq.add(new Pair(0));
        while(pq.size() > 0) {
            Pair val = pq.peek();
            pq.remove();
            System.out.println(val.val);
        }
    }

    public static void main(String[] args) {
        fun();
    }
}
