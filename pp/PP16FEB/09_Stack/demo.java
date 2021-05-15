import java.util.*;

public class demo {


    public static void fun() {
        Stack<Integer> st = new Stack<>();
        st.push(10);
        System.out.println(st);
        st.push(20);
        System.out.println(st);
        st.push(30);
        System.out.println(st);


        while(st.size() > 0) {
            int rem = st.pop();
            System.out.print(rem + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        fun();
    }
}