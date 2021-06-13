import java.util.*;

// Min Priority Queue
class priorityqueue {
    private ArrayList<Integer> data;
    private boolean flag;
    // flag = true -> it is max pririty
    // flag = false -> it is min priority

    public priorityqueue() {
        data = new ArrayList<>();
        flag = false;
    }

    public priorityqueue(boolean flag) {
        data = new ArrayList<>();
        this.flag = flag;
    }

    private void processArray(int[] arr) {
        // COMPLEXITY MORE THAN O(nlogn)
        // for(int i = 0; i < arr.length; i++) {
        //     add(arr[i]);
        // }


        // COMPLEXITY IS nearly O(n)
        // 1. fill data list from array
        for(int val : arr) {
            // add elements in main element
            data.add(val);
        }

        // 2. call downheapify from end
        for(int i = arr.length - 1; i >= 0; i--) {
            downheapify(i);
        }
    }

    public priorityqueue(int[] arr) {
        data = new ArrayList<>();
        this.flag = false;
        processArray(arr);
    }

    public priorityqueue(int[] arr, boolean flag) {
        data = new ArrayList<>();
        this.flag = flag;
        processArray(arr);
    }

    private int checkPriority(int ci, int pi) {
        // ci -> child index
        // pi -> parent index.

        if(flag == true) {
            // for max priority
            if(data.get(ci) > data.get(pi)) {
                return 1;
            }
        } else {
            // for min priority
            if(data.get(ci) < data.get(pi)) {
                return 1;
            }
        }
        return 0;
    }

    private void swap(int i, int j) {
        int temp = data.get(i);
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

    public void add(int val) {
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

    public int remove() {
        if(data.size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        }

        swap(0, data.size() - 1);
        int val = data.remove(data.size() - 1);
        // maintain heap order property
        downheapify(0);
        return val;
    }

    public int peek() {
        if(data.size() == 0) {
            System.out.println("Queue underflow");
            return -1;
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


public class pq {

    public static void fun() {
        // priorityqueue pq = new priorityqueue(false);
        // pq.add(15);
        // pq.add(25);
        // pq.add(15);
        // pq.add(10);
        // pq.add(15);
        // pq.add(5);
        // pq.add(100);

        int[] arr = {15, 25, 15, 10, 15, 5, 100};
        priorityqueue pq = new priorityqueue(arr, false);

        pq.add(105);
        pq.add(0);
        while(pq.size() > 0) {
            int val = pq.peek();
            pq.remove();
            System.out.println(val);
        }
    }

    public static void main(String[] args) {
        fun();
    }    
}
