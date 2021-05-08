import java.util.*;

public class ques {

    public static boolean isDuplicate(String str) {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch != ')') {
                st.push(ch);
            } else {
                if(st.peek() == '(') return true;

                while(st.peek() != '(') 
                    st.pop();

                st.pop();
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
                if(st.isEmpty() || st.peek() != '(') return false;
                st.pop();
            } else if(ch == ']') {
                if(st.size() == 0 || st.peek() != '[') return false;
                st.pop();
            } else if(ch == '}') {
                if(st.size() == 0 || st.peek() != '{') return false;
                st.pop();
            } else {
                continue;
            }
        }
        return st.size() == 0;
    }

    // ngr -> next greater on right
    public static int[] ngr(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(int i = 1; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    // ngl -> next greater on left
    public static int[] ngl(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }

        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    } 
    
    // nsr -> next smaller on right
    public static int[] nsr(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(int i = 1; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }
    
    // nsl -> next smaller on left
    public static int[] nsl(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] stockSpan(int[] arr) {
        // based on discussion we conclude that it is next greater on left (index) version
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }

        while(st.size() > 0) {
            res[st.pop()] = -1;
        }

        for(int i = 0; i < res.length; i++) {
            res[i] = i - res[i];
        }
        return res;
    }

    // leetcode 739. Daily Temperature
    public int[] dailyTemperatures(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(int i = 1; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = arr.length;
        }

        for(int i = 0; i < res.length; i++) {
            res[i] = arr.length - res[i];
        }

        return res;
    }

    public static void fun() {
        int[] arr = {10, 6, 12, 5, 3, 2, 4, 8, 1};

        System.out.println("arr : " + Arrays.toString(arr));
        int[] ngr = ngr(arr);
        int[] ngl = ngl(arr);
        int[] nsr = nsr(arr);
        int[] nsl = nsl(arr);
        System.out.println("ngr : " + Arrays.toString(ngr));
        System.out.println("ngl : " + Arrays.toString(ngl));
        System.out.println("nsr : " + Arrays.toString(nsr));
        System.out.println("nsl : " + Arrays.toString(nsl));

        // String str = "(a + b) + ((c + d))";
        // boolean res = isDuplicate(str);
        // System.out.println(res);
    }

    public static void main(String[] args) {
        fun();
    }
}