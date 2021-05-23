import java.util.*;

class stack<T> {
    private Object[] arr;
    private int tos;

    public stack(int cap) {
        this.arr = new Object[cap];
        tos = 0;
    }

    public void push(T data) {
        if(tos == arr.length) {
            System.out.println("Stack overflow");
            return;
        }
        arr[tos] = data;
        tos++;
    }

    public T pop() {
        if(tos == 0) {
            System.out.println("Stack underflow");
            return null;
        }
        tos--;
        return (T)arr[tos];
    }

    public int size() {
        return tos;
    }

    public T peek() {
        if(tos == 0) {
            System.out.println("Stack underflow");
            return null;
        }
        // type cast
        return (T)arr[tos - 1];
    }

    public void display() {
        System.out.print("[");
        for(int i = 0; i < tos - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        if(tos > 0)
            System.out.print(arr[tos - 1]);
        System.out.println("]");
    }

    @Override
    public String toString() {
        String str = "[";
        for(int i = 0; i < tos - 1; i++) {
            str += arr[i] + ", ";
        }
        if(tos > 0)
            str += arr[tos - 1];
        str += "]";

        return str;
    }
}


public class mystack {

    public static void demo() {
        stack<String> st = new stack<>(5);

        st.push("ABC");
        System.out.println(st);
        st.push("DEF");
        System.out.println(st);        
        st.push("GHI");
        System.out.println(st);
        st.push("JKL");
        System.out.println(st);
        st.push("MNO");
        System.out.println(st);

        stack<Integer> st2 = new stack<>(4);
        Integer i = st2.pop();
        System.out.println(i);
        st2.push(10);
        st2.push(20);
        System.out.println(st2);




        // stack st = new stack(5);

        // // st.display();
        // st.peek();
        // st.push(10);
        // // st.display();
        // st.push(20);
        // // st.display();
        // st.push(30);
        // // st.display();
        // System.out.println(st);
        // System.out.println(st.size());
        // System.out.println(st.pop());
        // st.push(40);
        // // st.display();
        // System.out.println(st.pop());
        // // st.display();
        // System.out.println(st.size());

    }

    public static void main(String[] args) {
        demo();
    }
}
