import java.util.*;

public class ques {

    public static void demoStack() {
        Stack<Integer> st = new Stack<>();
        System.out.println(st.isEmpty());
        st.push(10);
        System.out.println(st);
        st.push(20);
        System.out.println(st);
        st.push(30);
        System.out.println(st);
        st.push(40);
        System.out.println(st);
        st.push(50);
        System.out.println("Size : " + st.size());
        System.out.println(st);
        System.out.println(st.pop());
        System.out.println(st);
        System.out.println(st.pop());
        System.out.println(st);
        System.out.println(st.isEmpty());
    }

    public static boolean duplicateBrackets(String str) {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == ' ') continue;
            if(ch != ')') {
                st.push(ch);
            } else {
                if(st.peek() == '(') {
                    return true;
                } else {
                    while(st.peek() != '(') {
                        st.pop();
                    }
                    st.pop();
                }
            }
        }
        return false;
    }

    public static boolean balancedBracket(String str) {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if(ch == ')') {
                if(st.size() == 0 || st.peek() != '(') {
                    return false;
                }
                st.pop();
            } else if(ch == '}') {
                if(st.size() == 0 || st.peek() != '{') {
                    return false;
                }
                st.pop();
            } else if(ch == ']') {
                if(st.size() == 0 || st.peek() != '[') {
                    return false;
                }
                st.pop();
            } else {
                continue; // opeartor, operand, space
            }
        }
        return st.size() == 0;
    }

    // ngr -> next greater on right
    public static int[] ngr(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0); // stack hold index of values
        for(int i = 1; i < n; i++) {
            // pop smaller value from stack and mark their next greater
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                int indx = st.pop();
                res[indx] = arr[i]; // place value in res
            }
            st.push(i);
        }
        while(st.isEmpty() != true) {
            res[st.pop()] = -1;
        }
        return res;
    }

    // nsr -> next smaller on right
    public static int[] nsr(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0); // stack hold index of values
        for(int i = 1; i < n; i++) {
            // pop smaller value from stack and mark their next greater
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                int indx = st.pop();
                res[indx] = arr[i]; // place value in res
            }
            st.push(i);
        }
        while(st.isEmpty() != true) {
            res[st.pop()] = -1;
        }
        return res;
    }

    // ngl -> next greater on left
    public static int[] ngl(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push( n - 1); // stack hold index of values
        for(int i = n - 2; i >= 0; i--) {
            // pop smaller value from stack and mark their next greater
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                int indx = st.pop();
                res[indx] = arr[i]; // place value in res
            }
            st.push(i);
        }
        while(st.isEmpty() != true) {
            res[st.pop()] = -1;
        }
        return res;
    }

    // nsl -> next smaller on left
    public static int[] nsl(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push( n - 1); // stack hold index of values
        for(int i = n - 2; i >= 0; i--) {
            // pop smaller value from stack and mark their next greater
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                int indx = st.pop();
                res[indx] = arr[i]; // place value in res
            }
            st.push(i);
        }
        while(st.isEmpty() != true) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] nglIndex(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push( n - 1); // stack hold index of values
        for(int i = n - 2; i >= 0; i--) {
            // pop smaller value from stack and mark their next greater
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                int indx = st.pop();
                res[indx] = i; // place index in res
            }
            st.push(i);
        }
        while(st.isEmpty() != true) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] ngrIndex(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0); // stack hold index of values
        for(int i = 1; i < n; i++) {
            // pop smaller value from stack and mark their next greater
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                int indx = st.pop();
                res[indx] = i; // place value in res
            }
            st.push(i);
        }
        while(st.isEmpty() != true) {
            int indx = st.pop();
            res[st.pop()] = indx;
        }
        return res;
    }

    public static int[] stockSpan(int[] prices) {
        int[] ngl = nglIndex(prices);
        for(int i = 0; i < ngl.length; i++) {
            ngl[i] = i - ngl[i];
        }
        return ngl;
    }

    // leetcode 739, https://leetcode.com/problems/daily-temperatures/
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ngr = ngrIndex(temperatures);
        for(int i = 0; i < ngr.length; i++) {
            ngr[i] = ngr[i] - i;
        }
        return ngr;
    }


    public static void fun() {
        int[] arr = {10, 6, 12, 5, 3, 11, 14, 8, 9};
        System.out.println("Arrays : " + Arrays.toString(arr));
        System.out.println("ngr -> " + Arrays.toString(ngr(arr)));
        System.out.println("nsr -> " + Arrays.toString(nsr(arr)));
        System.out.println("ngl -> " + Arrays.toString(ngl(arr)));
        System.out.println("nsl -> " + Arrays.toString(nsl(arr)));

        // demoStack();
    }    

    public static void main(String[] args) {
        fun();
    }
}