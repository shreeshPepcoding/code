import java.io.*;
import java.util.*;

public class minStack {

    public static class MinStack {
        Stack<Integer> data;
        int min;

        public MinStack() {
            data = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        int size() {
            // write your code here
            return data.size();
        }

        void push(int val) {
            // write your code here
            if(min > val) {
                // min. update
                // 2 * val - oldMin
                data.push(val + val - min);
                // update min
                min = val;
            } else {
                data.push(val);
            }
        }

        int pop() {
            // write your code here
            if(data.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }

            if(data.peek() < min) {
                // get the value of stack
                int val = min;
                // update oldmin
                min = 2 * val - data.pop();
                return val;
            } else {
                return data.pop();
            }
        }

        int top() {
            // write your code here
            if(data.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }

            if(data.peek() < min) {
                return min;
            } else {
                return data.peek();
            }
        }

        int min() {
            // write your code here
            if(data.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return min;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MinStack st = new MinStack();

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("push")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                st.push(val);
            } else if (str.startsWith("pop")) {
                int val = st.pop();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("top")) {
                int val = st.top();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("size")) {
                System.out.println(st.size());
            } else if (str.startsWith("min")) {
                int val = st.min();
                if (val != -1) {
                    System.out.println(val);
                }
            }
            str = br.readLine();
        }
    }
}