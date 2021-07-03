import java.util.*;
class priority_queue {
    private ArrayList<Integer> data;

    public priority_queue() {
      data = new ArrayList<>();
    }

    public priority_queue(int[] arr) {
        data = new ArrayList<>();
        // high complexity ways -> more than nlogn
        // for(int val : arr) {
        //     add(val);
        // }


        // optimise approach
        for(int val : arr) {
            data.add(val);
        }

        for(int i = data.size() -1; i >= 0; i--) {
            downHeapify(i);
        }
    }

    private void swap(int i, int j) {
        int ival = data.get(i);
        int jval = data.get(j);

        data.set(i, jval);
        data.set(j, ival);
    }

    private void upheapify(int indx) {
        int pi = (indx - 1) / 2;
        if(pi >= 0 && data.get(indx) < data.get(pi)) {
            swap(indx, pi);
            upheapify(pi);
        }
    }

    public void add(int val) {
        data.add(val);
        upheapify(data.size() - 1);
    }

    private void downHeapify(int i) {

        int lci = 2 * i + 1;
        int rci = 2 * i + 2;

        int minIndx = i;
        if(lci < data.size() && data.get(lci) < data.get(minIndx)) {
            minIndx = lci;
        }

        if(rci < data.size() && data.get(rci) < data.get(minIndx)) {
            minIndx = rci;
        }

        if(minIndx != i) {
            swap(i, minIndx);
            downHeapify(minIndx);
        }
    }

    public int remove() {
        if(data.size() == 0) {
            System.out.println("Underflow");
            return -1;
        }

        int val = data.get(0);

        swap(0, data.size() - 1);
        data.remove(data.size() - 1);

        downHeapify(0);

        return val;
    }

    public int peek() {
        if(data.size() == 0) {
            System.out.println("Underflow");
            return -1;
        }
        return data.get(0);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }

    public void display() {
        System.out.println(data);;
    }
}
public class pq {
    

    public static void fun() {

        int[] arr = { 2, 5, 8, 9, 6, 3, 12, 4, 45, 78, 1, 45, 6, 2 };
        priority_queue pq = new priority_queue(arr);

        while(pq.size() > 0) {
            int val = pq.remove();
            System.out.println(val);
        }
    

        // priority_queue pq = new priority_queue();
        // pq.add(10);
        // pq.add(40);
        // pq.add(50);
        // pq.add(70);
        // pq.add(1);
        // pq.add(11);
        // pq.add(17);

        
        // while(pq.size() > 0) {
        //     pq.display();
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
        fun();    
    }
}
