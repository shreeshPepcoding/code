import java.util.*;

public class ques {

    public static boolean duplicateBrakets(String exp) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (ch == ' ') {
                continue;
            } else if (ch == ')') {
                if (st.peek() == '(') {
                    return true;
                } else {
                    while (st.peek() != '(') {
                        st.pop();
                    }
                    st.pop(); // for opening bracket
                }
            } else {
                st.push(ch);
            }
        }
        return false;
    }

    public static boolean balancedBracket(String exp) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if (ch == ')') {
                if (st.size() == 0 || st.peek() != '(')
                    return false;
                st.pop();
            } else if (ch == ']') {
                if (st.size() == 0 || st.peek() != '[')
                    return false;
                st.pop();
            } else if (ch == '}') {
                if (st.size() == 0 || st.peek() != '{')
                    return false;
                st.pop();
            }
        }

        return st.size() == 0;
    }

    // ngr -> next greater on right
    public static int[] ngr(int[] arr) {
        Stack<Integer> st = new Stack<>();

        st.push(0);
        int[] res = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    // ngl -> next greater on left
    public static int[] ngl(int[] arr) {
        Stack<Integer> st = new Stack<>();

        st.push(arr.length - 1);
        int[] res = new int[arr.length];
        for (int i = arr.length - 2; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    // nsr -> next smaller on right
    public static int[] nsr(int[] arr) {
        Stack<Integer> st = new Stack<>();

        st.push(0);
        int[] res = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    // nsl -> next smaller on left
    public static int[] nsl(int[] arr) {
        Stack<Integer> st = new Stack<>();

        st.push(arr.length - 1);
        int[] res = new int[arr.length];
        for (int i = arr.length - 2; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] nglIndex(int[] arr) {
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);
        int[] res = new int[arr.length];
        for (int i = arr.length - 2; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] stockSpan(int[] arr) {
        int[] ngl = nglIndex(arr);
        for (int i = 0; i < ngl.length; i++) {
            ngl[i] = i - ngl[i];
        }
        return ngl;
    }

    public int[] ngrIndex(int[] arr) {
        Stack<Integer> st = new Stack<>();

        st.push(0);
        int[] res = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while (st.size() > 0) {
            int indx = st.peek();
            res[st.pop()] = indx;
        }
        return res;
    }

    // leetcode 739
    public int[] dailyTemperatures(int[] arr) {
        int[] ngr = ngrIndex(arr);

        for (int i = 0; i < ngr.length; i++) {
            ngr[i] = ngr[i] - i;
        }
        return ngr;
    }

    public static int[] nslIndex(int[] arr) {
        Stack<Integer> st = new Stack<>();

        st.push(arr.length - 1);
        int[] res = new int[arr.length];
        for (int i = arr.length - 2; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] nsrIndex(int[] arr) {
        Stack<Integer> st = new Stack<>();

        st.push(0);
        int[] res = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = arr.length;
        }
        return res;
    }

    public static int largestAreaHistogram(int[] arr) {
        int[] lsi = nslIndex(arr);
        int[] rsi = nsrIndex(arr);

        int area = 0;

        for (int i = 0; i < arr.length; i++) {
            int width = rsi[i] - lsi[i] - 1;
            int ht = arr[i];
            area = Math.max(area, width * ht);
        }
        return area;
    }

    // leetcode 85 Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int[] arr = new int[matrix[0].length];
        int area = 0;
        for (int i = 0; i < matrix.length; i++) {
            // prepare arr for largest area histogram
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    arr[j] = 0;
                } else {
                    arr[j] += 1;
                }
            }
            // find largest area histogram
            area = Math.max(area, largestAreaHistogram(arr));
        }
        return area;
    }

    public static int[] ngriForSlidingWindow(int[] arr) {
        Stack<Integer> st = new Stack<>();

        st.push(0);
        int[] res = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = arr.length;
        }
        return res;
    }

    public static void slidingWindowMax(int[] arr, int k) {
        int[] ngri = ngriForSlidingWindow(arr);

        int j = 0;
        for (int i = 0; i <= arr.length - k; i++) {
            if (j < i)
                j = i;

            while (i + k > ngri[j]) {
                j = ngri[j];
            }
            System.out.println(arr[j]);
        }
    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"
        int n = arr.length;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            st.push(i);
        }

        while (st.size() > 1) {
            int i = st.pop();
            int j = st.pop();

            if (arr[i][j] == 1) {
                // i knows j -> i is not celebrity
                st.push(j);
            } else {
                // i don't know j -> j is not celebrity
                st.push(i);
            }
        }

        int candidate = st.pop(); // potential candidate for celebrity
        // check within row, arr[candidate][c] == 0
        for (int c = 0; c < n; c++) {
            if (arr[candidate][c] == 1) {
                System.out.println("none");
                return;
            }
        }
        // check within col, arr[r][candidate] == 1, except for
        // arr[candidate][candidate];
        for (int r = 0; r < n; r++) {
            if (r != candidate && arr[r][candidate] == 0) {
                System.out.println("none");
                return;
            }
        }
        System.out.println(candidate);
    }

    public static class Pair implements Comparable<Pair> {
        int st;
        int end;

        public Pair(int st, int end) {
            this.st = st;
            this.end = end;
        }

        public int compareTo(Pair other) {
            return this.st - other.st;
        }
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        // merge overlapping intervals and print in increasing order of start time

        Pair[] pairs = new Pair[arr.length];

        for (int i = 0; i < pairs.length; i++) {
            int st = arr[i][0];
            int end = arr[i][1];

            pairs[i] = new Pair(st, end);
        }

        Arrays.sort(pairs);
        // stack

        Pair res = pairs[0];
        for (int i = 1; i < pairs.length; i++) {
            Pair p = pairs[i];
            if (res.end >= p.st) {
                if (res.end < p.end) {
                    res.end = p.end;
                }
            } else {
                System.out.println(res.st + " " + res.end);
                res = p;
            }
        }
        System.out.println(res.st + " " + res.end);

        // Stack<Pair> st = new Stack<>();
        // st.push(pairs[0]);

        // for(int i = 1; i < pairs.length; i++) {
        // Pair p = pairs[i];
        // if(st.peek().end >= p.st) {
        // if(st.peek().end < p.end) {
        // st.peek().end = p.end;
        // }
        // } else {
        // st.push(p);
        // }
        // }

        // Stack<Pair> st2 = new Stack<>();
        // while(st.size() > 0)
        // st2.push(st.pop());

        // // print intervals
        // while(st2.size() > 0) {
        // Pair rem = st2.pop();
        // System.out.println(rem.st + " " + rem.end);
        // }
    }

    public static void smallestNumberFollowingPattern(String pattern) {
        Stack<Integer> st = new Stack<>();
        int count = 1;

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (ch == 'd') {
                st.push(count);
                count++;
            } else {
                st.push(count);
                count++;
                // print stack
                while (st.size() > 0) {
                    System.out.print(st.pop());
                }
            }
        }
        st.push(count);
        // print stack
        while (st.size() > 0) {
            System.out.print(st.pop());
        }
    }

    public static class MinStack {
        Stack<Integer> st;
        Stack<Integer> minSt;

        public MinStack() {
            st = new Stack<>();
            minSt = new Stack<>();
        }

        int size() {
            return st.size();
        }

        void push(int val) {
            st.push(val);
            if (minSt.size() == 0) {
                minSt.push(val);
            } else {
                minSt.push(Math.min(minSt.peek(), val));
            }
        }

        int pop() {
            if (st.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            int val = st.peek();
            st.pop();
            minSt.pop();
            return val;
        }

        int top() {
            if (st.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return st.peek();
        }

        int min() {
            if (st.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return minSt.peek();
        }
    }

    public static class MinStackContantSpace {
        Stack<Integer> st;
        int min;

        public MinStackContantSpace() {
            st = new Stack<>();
        }

        int size() {
            return st.size();
        }

        void push(int val) {
            if (st.size() == 0) {
                st.push(val);
                min = val;
                return;
            }

            if (val > min) {
                st.push(val);
            } else {
                st.push(2 * val - min);
                min = val;
            }
        }

        int pop() {
            if (st.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }

            int data = 0;
            if (st.peek() > min) {
                data = st.pop();
            } else {
                data = min;
                min = 2 * min - st.pop();
            }
            return data;
        }

        int top() {
            if (st.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            if (st.peek() > min) {
                return st.peek();
            } else {
                return min;
            }
        }

        int min() {
            if (st.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return min;
        }
    }

    public static class TwoStack {
        int[] data;
        int i;
        int j;

        public TwoStack(int cap) {
            data = new int[cap];
            i = 0;
            j = cap - 1;
        }

        int size1() {
            return i;
        }

        int size2() {
            return data.length - j - 1;
        }

        void push1(int val) {
            if(i <= j) {
                data[i] = val;
                i++;
            } else {
                System.out.println("Stack overflow");
            }
        }

        void push2(int val) {
            if(i <= j) {
                data[j] = val;
                j--;
            } else {
                System.out.println("Stack overflow");
            }
        }

        int pop1() {
            if(i == 0) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                int val = data[i - 1];
                i--;
                return val;
            }
        }

        int pop2() {
            if(j == data.length - 1) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                int val = data[j + 1];
                j++;
                return val;
            }
        }

        int top1() {
            if(i == 0) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                int val = data[i - 1];
                return val;
            }
        }

        int top2() {
            if(j == data.length - 1) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                int val = data[j + 1]; 
                return val;
            }
        }
    }
    
    public static void quest() {
        String pattern = "ddddiiii";
        smallestNumberFollowingPattern(pattern);

        // int[][] arr = {
        // {5, 12},
        // {14, 19},
        // {22, 28},
        // {1, 8},
        // {25, 27},
        // {27, 30}
        // };

        // mergeOverlappingIntervals(arr);
        // int[] arr = {2, 9, 3, 8, 1, 7, 12, 6, 14, 4, 32, 0, 7, 19, 8, 12, 6};
        // int k = 4;
        // slidingWindowMax(arr, k);

        // int[] arr = { 10, 6, 12, 5, 3, 2, 4, 8, 1 };

        // System.out.println("arr : " + Arrays.toString(arr));
        // System.out.println("ngr : " + Arrays.toString(ngr(arr)));
        // System.out.println("ngl : " + Arrays.toString(ngl(arr)));
        // System.out.println("nsr : " + Arrays.toString(nsr(arr)));
        // System.out.println("nsl : " + Arrays.toString(nsl(arr)));

    }

    public static void main(String[] args) {
        quest();
    }
}
