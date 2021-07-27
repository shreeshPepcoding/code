import java.util.*;

class stack {
    private int[] arr;
    private int size = 0; 

    public stack(int capacity) {
        arr = new int[capacity];
    }

    public void push(int val) {
        if(this.size == arr.length) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[this.size] = val;
        this.size++;
    }

    public int pop() {
        if(this.size == 0) {
            System.out.println("Stack Underflow");
            return -1;
        }
        int val = arr[this.size - 1];
        this.size--;
        return val;
    }

    public int peek() {
        if(this.size == 0) {
            System.out.println("Stack Underflow");
            return -1;
        }
        int val = arr[this.size - 1];
        return val;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void display() {
        System.out.print("[");
        for(int i = 0; i < this.size - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        if(this.size - 1 >= 0)
            System.out.print(arr[this.size - 1]);
        System.out.println("]");
    }

    @Override
    public String toString() {
        String str = "[";
        for(int i = 0; i < this.size - 1; i++) {
            str += arr[i] + ", ";
        }
        if(this.size - 1 >= 0)
            str += arr[this.size - 1];
        str += "]";

        return str;
    }

}

public class mystack {
    public static void main(String[] args) {

        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

 
        // stack st = new stack(10);

        // st.push(10);
        // st.push(20);
        // st.push(30);
        // st.push(40);
        // st.push(50);
        // st.push(10);
        // st.push(20);
        // st.push(30);
        // st.push(40);
        // st.push(50);
        // st.pop();
        // st.push(11);

        // st.display();
        // System.out.println(st);

        // while(st.size() > 0) {
        //     int rem = st.peek();
        //     st.pop();
        //     System.out.print(rem + " stack -> ");
        //     // st.display();
        //     System.out.println(st);
        // }
        // st.peek();
    }    
}
