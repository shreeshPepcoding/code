import java.util.*;

class priorityQueue {
    
    private ArrayList<Integer> data;

    public priorityQueue() {
        data = new ArrayList<>();
    }

    private void swap(int i, int j) {
        int temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    private void upheapify(int indx) {
        if(indx == 0) return;

        int pi = (indx - 1) / 2;

        if(data.get(pi) > data.get(indx)) {
            swap(indx, pi);
            upheapify(pi);
        }
    }

    public void add(int val) {
        data.add(val);
        // maitain heap order property
        upheapify(data.size() - 1);
    }

    private void downheapify(int indx) {
        int lci = 2 * indx + 1;
        int rci = 2 * indx + 2;

        int minIndx = indx;
        if(lci < data.size() && data.get(minIndx) > data.get(lci)) {
            minIndx = lci;
        }

        if(rci < data.size() && data.get(minIndx) > data.get(rci)) {
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

public class heapCreation {

    public static void fun() {
        priorityQueue pq = new priorityQueue();

        pq.add(10);
        pq.add(20);
        pq.add(7);
        pq.add(100);
        pq.add(17);
        pq.add(82);
        pq.add(19);

        while(pq.size() > 0) {
            int rem = pq.peek();
            pq.remove();

            System.out.println(rem);
        }
    }

    public static void main(String[] args) {
        fun();
    }
}
