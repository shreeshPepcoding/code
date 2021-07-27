import java.util.*;

public class ques {

    public static boolean isDuplicate(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch != ')') {
                st.push(ch);
            } else {
                if (st.peek() == '(')
                    return true;

                while (st.peek() != '(')
                    st.pop();

                st.pop();
            }
        }
        return false;
    }

    public static boolean balancedBracket(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if (ch == ')') {
                if (st.isEmpty() || st.peek() != '(')
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
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

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
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

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
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

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

    public static int[] stockSpan(int[] arr) {
        // based on discussion we conclude that it is next greater on left (index)
        // version
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

        for (int i = arr.length - 2; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }

        while (st.size() > 0) {
            res[st.pop()] = -1;
        }

        for (int i = 0; i < res.length; i++) {
            res[i] = i - res[i];
        }
        return res;
    }

    // leetcode 739. Daily Temperature
    public int[] dailyTemperatures(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (int i = 1; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = arr.length;
        }

        for (int i = 0; i < res.length; i++) {
            res[i] = arr.length - res[i];
        }

        return res;
    }

    public static int[] leftSmallerIndex(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

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

    public static int[] rightSmallerIndex(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

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
        int area = 0;

        int[] lsi = leftSmallerIndex(arr);
        int[] rsi = rightSmallerIndex(arr);

        int st = 0;
        int end = 0;

        for (int i = 0; i < lsi.length; i++) {
            int width = rsi[i] - lsi[i] - 1;
            int height = arr[i];

            // area = Math.max(area, width * height);
            if (area < width * height) {
                area = width * height;
                st = lsi[i];
                end = rsi[i];
            }
        }

        System.out.println("max area exist in " + (st + 1) + " - " + (end - 1) + " index");
        return area;
    }

    // leetcode 85. maximal rectangle
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int[] arr = new int[matrix[0].length];

        int res = 0;

        for (int i = 0; i < matrix.length; i++) {
            // prepare arr for largest area histogram
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    arr[j] = 0;
                } else {
                    arr[j] += matrix[i][j] - '0';
                }
            }

            res = Math.max(res, largestAreaHistogram(arr));
        }

        return res;
    }

    public static int[] rightGreaterIndex(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

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
        int[] rgi = rightGreaterIndex(arr);

        int j = 0;
        for (int i = 0; i <= arr.length - k; i++) {
            if (i > j) {
                j = i;
            }

            while (i + k > rgi[j]) {
                j = rgi[j];
            }
            System.out.println(arr[j]);
        }
    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            st.push(i);
        }

        // elimination
        while (st.size() > 1) {
            int i = st.pop();
            int j = st.pop();

            if (arr[i][j] == 1) {
                // i is not celebrity
                st.push(j);
            } else {
                // j is not celebrity
                st.push(i);
            }
        }

        int candidate = st.pop();

        // check its row
        for (int c = 0; c < arr[0].length; c++) {
            if (arr[candidate][c] == 1) {
                System.out.println("none");
                return;
            }
        }
        // check its colum
        for (int r = 0; r < arr.length; r++) {
            if (r != candidate && arr[r][candidate] == 0) {
                System.out.println("none");
                return;
            }
        }

        System.out.println(candidate);
    }

    public static class Pair implements Comparable<Pair> {
        int st = 0;
        int end = 0;

        public Pair(int st, int end) {
            this.st = st;
            this.end = end;
        }

        public int compareTo(Pair o) {
            return this.st - o.st;
        }
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        // merge overlapping intervals and print in increasing order of start time
        Pair[] pairs = new Pair[arr.length];
        for (int i = 0; i < arr.length; i++) {
            pairs[i] = new Pair(arr[i][0], arr[i][1]);
        }
        Arrays.sort(pair);
        Stack<Pair> st = new Stack<>();
        st.push(pairs[0]);
        for (int i = 1; i < pairs.length; i++) {
            Pair p = pairs[i];
            if (p.st <= st.peek().end) {
                // end time may be update
                if (p.end > st.peek().end) {
                    st.peek().end = p.end;
                }
            } else {
                st.push(p);
            }
        }
        Stack<Pair> rst = new Stack<>(); // reverse stack
        while (st.size() > 0)
            rst.push(st.pop());
        while (rst.size() > 0) {
            Pair rem = rst.pop();
            System.out.println(rem.st + " " + rem.end);
        }
    }

    public static void smallestNumberFollowingPattern(String str) {
        int count = 1;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            st.push(count);
            count++;
            if (str.charAt(i) == 'i') {
                while (st.size() > 0) {
                    System.out.print(st.pop());
                }
            }
        }
        st.push(count);
        while (st.size() > 0) {
            System.out.print(st.pop());
        }
    }

    // gfg -> generateBinary link :
    // https://practice.geeksforgeeks.org/problems/generate-binary-numbers-1587115620/1
    public static ArrayList<String> generate(int n) {
        ArrayList<String> res = new ArrayList<>();

        Queue<String> qu = new ArrayDeque<>();

        qu.add("1");

        for (int i = 0; i < n; i++) {
            String rem = qu.remove();

            res.add(rem);
            qu.add(rem + "0");
            qu.add(rem + "1");
        }
        return res;
    }

    // push Efficient - Stack from Queue
    public static class QueueToStackAdapterPushEfficient {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;

        public QueueToStackAdapter() {
            mainQ = new ArrayDeque<>();
            helperQ = new ArrayDeque<>();
        }

        int size() {
            return mainQ.size();
        }

        void push(int val) {
            mainQ.add(val);
        }

        int pop() {
            if (mainQ.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }

            // n - 1 remove from mainQ, add it in helper Q
            while (mainQ.size() > 1) {
                helperQ.add(mainQ.remove());
            }
            // get the value of nth element from mainQ
            int val = mainQ.peek();
            // remove nth element
            mainQ.remove();
            // change reference
            Queue<Integer> temp = mainQ;
            mainQ = helperQ;
            helperQ = temp;
            // return val
            return val;
        }

        int top() {
            if (mainQ.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }

            // n - 1 remove from mainQ, add it in helper Q
            while (mainQ.size() > 1) {
                helperQ.add(mainQ.remove());
            }
            // get the value of nth element from mainQ
            int val = mainQ.peek();
            // add nth value in helperQ
            helperQ.add(mainQ.remove());
            // change reference
            Queue<Integer> temp = mainQ;
            mainQ = helperQ;
            helperQ = temp;
            // return val
            return val;
        }
    }

    // pop Efficient - Stack from Queue
    public static class QueueToStackAdapterPopEfficient {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;

        public QueueToStackAdapter() {
          mainQ = new ArrayDeque<>();
          helperQ = new ArrayDeque<>();
        }

        int size() {
            return mainQ.size();
        }

        void push(int val) {
            // // 1. fill helperQ from mainQ
            // while(mainQ.size() > 0) {
            // helperQ.add(mainQ.remove());
            // }
            // // 2. add new element in mainQ
            // mainQ.add(val);
            // // 3. refill mainQ from helperq
            // while(helperQ.size() > 0) {
            // mainQ.add(helperQ.remove());
            // }

            helperQ.add(val);

            while (mainQ.size() > 0) {
                helperQ.add(mainQ.remove());
            }

            mainQ = helperQ;
            helperQ = new ArrayDeque<>();
        }

        int pop() {
            if (mainQ.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }

            return mainQ.remove();
        }

        int top() {
            if (mainQ.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }

            return mainQ.peek();
        }
    }

    // add Efficient - Queue from Stack
    public static class StackToQueueAdapterAddEfficient {
        Stack<Integer> mainS;
        Stack<Integer> helperS;

        public StackToQueueAdapter() {
          mainS = new Stack<>();
          helperS = new Stack<>();
        }

        int size() {
            return mainS.size();
        }

        void add(int val) {
            mainS.push(val);
        }

        int remove() {
            if (mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            // 1. move n-1 elements from main stack to helper stack
            while (mainS.size() > 1) {
                helperS.push(mainS.pop());
            }
            // 2. get last element + remove last element
            int val = mainS.pop();
            // 3. move n - 1 element from helper to main
            while (helperS.size() > 0) {
                mainS.push(helperS.pop());
            }
            // 4. return value
            return val;
        }

        int peek() {
            if (mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            // 1. move n-1 elements from main stack to helper stack
            while (mainS.size() > 1) {
                helperS.push(mainS.pop());
            }
            // 2. get last element + remove last element
            int val = mainS.peek();
            // 3. move n - 1 element from helper to main
            while (helperS.size() > 0) {
                mainS.push(helperS.pop());
            }
            // 4. return value
            return val;
        }
    }

    // remove efficient - queue from stack
    public static class StackToQueueAdapterRemoveEfficient {
        Stack<Integer> mainS;
        Stack<Integer> helperS;

        public StackToQueueAdapter() {
            mainS = new Stack<>();
            helperS = new Stack<>();
        }

        int size() {
            return mainS.size();
        }

        void add(int val) {
            while (mainS.size() > 0) {
                helperS.push(mainS.pop());
            }

            mainS.push(val);

            while (helperS.size() > 0) {
                mainS.push(helperS.pop());
            }
        }

        int remove() {
            if (mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return mainS.pop();
        }

        int peek() {
            if (mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return mainS.peek();
        }
    }

    public static class TwoStack {
        int[] data;
        int tos1;
        int tos2;

        public TwoStack(int cap) {
            data = new int[cap];
            tos1 = -1;
            tos2 = cap;
        }

        int size1() {
            return tos1 + 1;
        }

        int size2() {
            return data.length - tos2;
        }

        void push1(int val) {
            if(tos1 + 1 < tos2) {
                data[tos1 + 1] = val;
                tos1++;
            } else {
                System.out.println("Stack overflow");
            }
        }

        void push2(int val) {
            if(tos1 + 1 < tos2) {
                data[tos2 - 1] = val;
                tos2--;
            } else {
                System.out.println("Stack overflow");
            }
        }

        int pop1() {
            if(tos1 == -1) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                int val = data[tos1];
                tos1--;
                return val;
            }
        }

        int pop2() {
            if(tos2 == data.length) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                int val = data[tos2];
                tos2++;
                return val;
            }
        }

        int top1() {
            if(tos1 == -1) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                int val = data[tos1];
                return val;
            }
        }

        int top2() {
            if(tos2 == data.length) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                int val = data[tos2];
                return val;
            }
        }
    }
    
    public static void fun() {

        int[][] arr = { { 5, 8 }, { 1, 7 } };
        mergeOverlappingIntervals(arr);
        // int[] arr = {10, 6, 12, 5, 3, 2, 4, 8, 1};

        // int[] arr = {6, 2, 6, 5, 5, 6, 1, 7};

        // int res = largestAreaHistogram(arr);
        // System.out.println(res);

        // System.out.println("arr : " + Arrays.toString(arr));
        // int[] ngr = ngr(arr);
        // int[] ngl = ngl(arr);
        // int[] nsr = nsr(arr);
        // int[] nsl = nsl(arr);
        // System.out.println("ngr : " + Arrays.toString(ngr));
        // System.out.println("ngl : " + Arrays.toString(ngl));
        // System.out.println("nsr : " + Arrays.toString(nsr));
        // System.out.println("nsl : " + Arrays.toString(nsl));

        // String str = "(a + b) + ((c + d))";
        // boolean res = isDuplicate(str);
        // System.out.println(res);
    }

    public static void main(String[] args) {
        fun();
    }
}