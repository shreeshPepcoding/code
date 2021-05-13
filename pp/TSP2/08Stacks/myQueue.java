import java.util.*;

class queue {
    private int[] data;
    private int front = 0;
    private int size = 0;

    public queue(int cap) {
        this.data = new int[cap];
    }

    public void add(int val) {
        if(this.size == data.length) {
            System.out.println("Queue overflow");
            return;
        }

        int indx = (this.front + this.size) % data.length;
        data[indx] = val;
        this.size++;
    }

    public int remove() {
        if(this.size == 0) {
            System.out.println("Queue underflow");
            return -1;
        }
        int val = data[front];
        this.front = (this.front + 1) % data.length;
        this.size--;
        return val;
    }

    public int peek() {
        if(this.size == 0) {
            System.out.println("Queue underflow");
            return -1;
        }
        return data[this.front];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        String str = "";
        str += "[";
        for(int i = 0; i < size - 1; i++) {
            int indx = (i + front) % data.length;
            str += data[indx] + ", ";
        }
        if(this.size > 0)
            str += data[(size - 1 + front) % data.length] + "]";
        else
            str += "]";

        return str;
    }
}


public class myQueue {


    public static void demo() {
        // Stack<Integer> st = new Stack<>();
        // Queue<Integer> qu = new ArrayDeque<>();

        // ArrayList<Integer> list = new ArrayList<>();

        queue qu = new queue(5);

        System.out.println(qu);
        qu.add(10);
        qu.add(20);

        qu.add(30);
        qu.add(40);
        System.out.println(qu);
        qu.add(50);
        qu.add(60);
        System.out.println(qu.remove());
        System.out.println(qu);
        qu.add(60);
        System.out.println(qu.peek());
        System.out.println(qu.size());
        System.out.println(qu.toString());
    }

    public static void main(String[] args) {
        demo();

        // Dynamic Queue -> add when size == data.length
        // int[] temp = data;
        // data = new int[2 * temp.length];
        // for(int i = 0; i < temp.length; i++) {
        //     // index for get the value
        //     int indx = (this.front + i) % temp.length;
        //     // fill new data array
        //     data[i] = temp[indx];
        // }
        // front = 0;
        // data[this.size] = val;
        // this.size++;

    }
}
