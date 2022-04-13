import java.util.*;

class queue {
    private int size;
    private int front;
    private int[] data;

    public queue(int capacity) {
        this.data = new int[capacity];
        this.front = 0;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void display() {
        for(int i = 0; i < this.size; i++) {
            int indx = (i + this.front) % this.data.length;
            System.out.print(this.data[indx] + " ");
        }
        System.out.println();
    }

    public void add(int val) {
        if(this.size == this.data.length) {
            System.out.println("Queue overflow");
            return;
        }
        int indx = (this.size + this.front) % this.data.length;
        this.data[indx] = val;
        this.size++;
    }

    public int remove() {
        if(this.size == 0) {
            System.out.println("Queue underflow");
            return -1;
        }

        int val = this.data[this.front];
        this.front = (this.front + 1) % this.data.length;
        this.size--;
        return val;
    }

    public int peek() {
        if(this.size == 0) {
            System.out.println("Queue underflow");
            return -1;
        }
        return this.data[this.front];
    }
}


public class myQueue {

    public static void fun() {

    }

    public static void main(String[] args) {
        fun();
    }
}
