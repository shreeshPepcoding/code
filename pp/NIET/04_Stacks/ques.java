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
            res[indx] = indx;
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

    public static int[] nslIndex(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push( n - 1); // stack hold index of values
        for(int i = n - 2; i >= 0; i--) {
            // pop smaller value from stack and mark their next greater
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                int indx = st.pop();
                res[indx] = i; // place value in res
            }
            st.push(i);
        }
        while(st.isEmpty() != true) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] nsrIndex(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0); // stack hold index of values
        for(int i = 1; i < n; i++) {
            // pop smaller value from stack and mark their next greater
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                int indx = st.pop();
                res[indx] = i; // place value in res
            }
            st.push(i);
        }
        while(st.isEmpty() != true) {
            res[st.pop()] = arr.length;
        }
        return res;
    }

    public static int largestAreaHistogram(int[] arr) {
        int[] lsi = nslIndex(arr);
        int[] rsi = nsrIndex(arr);

        int area = 0;
        for(int i = 0; i < arr.length; i++) {
            int ht = arr[i];
            int wd = rsi[i] - lsi[i] - 1;
            area = Math.max(area, ht * wd);
        }
        return area;
    }

    // leetcode 85, https://leetcode.com/problems/maximal-rectangle/
    public int maximalRectangle(char[][] matrix) {
        int n = matrix[0].length;
        int[] ht = new int[n];
        int area = 0;
        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[0].length; c++) {
                if(matrix[r][c] == '0') {
                    ht[c] = 0;
                } else {
                    ht[c] += 1;
                }
            }
            area = Math.max(area, largestAreaHistogram(ht));
        }
        return area;
    }

    public static void slidingWindowMax(int[] arr, int k) {
        int[] ngr = ngrIndex(arr);
        int j = 0;

        for(int i = 0; i <= arr.length - k; i++) {
            if(j < i) {
                j = i;
            }
            while(ngr[j] < i + k) {
                j = ngr[j];
            }
            System.out.println(arr[j]);
        }
    }

    public static void findCelebrity(int[][] arr) {
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < arr.length; i++) {
            st.push(i);
        }

        // eliminate non-celebrity person
        while(st.size() > 1) {
            int i = st.pop();
            int j = st.pop();

            if(arr[i][j] == 1) {
                // i is not celebrity
                st.push(j);
            } else {
                // j is not celebrity
                st.push(i);
            }
        }

        int candidate = st.pop();
        // confirmation for candidate in row
        for(int c = 0; c < arr[0].length; c++) {
            if(arr[candidate][c] == 1) {
                System.out.println("none");
                return;
            }
        }
        // confirmation for candidate in column
        for(int r = 0; r < arr.length; r++) {
            if(r != candidate && arr[r][candidate] == 0) {
                System.out.println("none");
                return;
            }
        }
        System.out.println(candidate);
    }

    public static class Pair implements Comparable<Pair>{
        int start;
        int end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Pair o) {
            return this.start - o.start;
        }
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        Pair[] pairs = new Pair[arr.length];
        for(int i = 0; i < arr.length; i++) {
            pairs[i] = new Pair(arr[i][0], arr[i][1]);
        }
        Arrays.sort(pairs);
        // use linkedlist as stack
        LinkedList<Pair> st = new LinkedList<>(); // addLast, removeLast
        st.addLast(pairs[0]);

        for(int i = 1; i < pairs.length; i++) {
            Pair p = pairs[i];
            if(p.start <= st.getLast().end) {
                // end time update
                if(p.end > st.getLast().end) {
                    st.getLast().end = p.end;
                }
            } else {
                st.addLast(p);
            }
        }

        for(Pair p : st) {
            System.out.println(p.start + " " + p.end);
        }
    }

    public static void smallestNumberFollowingPattern(String str) {
        Stack<Integer> st = new Stack<>();
        int num = 1;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == 'd') {
                st.push(num);
                num++;
            } else {
                st.push(num);
                num++;
                while(st.size() > 0) {
                    System.out.print(st.pop());
                }
            }
        }
        st.push(num);
        while(st.size() > 0) {
            System.out.print(st.pop());
        }
        System.out.println();
    }

    // stack creation
    public static class CustomStack {
        int[] data;
        int tos;
    
        public CustomStack(int cap) {
          data = new int[cap];
          tos = -1;
        }
    
        int size() {
          // write ur code here
        }
    
        void display() {
          // write ur code here
        }
    
        void push(int val) {
          // write ur code here
        }
    
        int pop() {
          // write ur code here
        }
    
        int top() {
           // write ur code here
        }
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