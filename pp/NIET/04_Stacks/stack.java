import java.util.*;

class myStack<E> {
    
    private Object[] arr; // data array
    private int tos; // top of stack index/pointer

    public myStack(int cap) {
        this.arr = new Object[cap];
        this.tos = 0;
    }

    public int size() {
        // write ur code here
        return this.tos;
    }
  
    public void display() {
        // write ur code here
        for(int i = this.tos - 1; i >= 0; i--) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    public void push(E val) {
        if(this.tos == this.arr.length) {
            System.out.println("Stack overflow");
            return;
        }
        // write ur code here
        this.arr[this.tos] = val;
        this.tos++;
    }

    public E pop() {
        if(this.tos == 0) {
            System.out.println("Stack underflow");
            return null;
        }
        // write ur code here
        E val = (E) this.arr[this.tos - 1];
        this.tos--;
        return val;
    }

    public E top() {
        if(this.tos == 0) {
            System.out.println("Stack underflow");
            return null;
        }
        // write ur code here
        return (E)this.arr[this.tos - 1];
    }
}

public class stack {

    public static void fun() {
        myStack<Integer> st = new myStack<>(5);
        st.push(100);
        st.display();
        st.push(200);
        st.display();
        st.push(300);
        st.display();
        st.push(400);
        st.display();
        st.push(500);
        st.display();
        st.push(600);
        System.out.println("Size : " + st.size());
        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.pop());
        st.display();
        System.out.println("top : " + st.top());
        System.out.println(st.pop());
        System.out.println(st.size());
    }

    public static void main(String[] args) {
        fun();
    }
}
