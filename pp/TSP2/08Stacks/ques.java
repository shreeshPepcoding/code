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

    public static int[] leftSmallerIndex(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] rightSmallerIndex(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(int i = 1; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
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

        for(int i = 0; i < lsi.length; i++) {
            int width = rsi[i] - lsi[i] - 1;
            int height = arr[i];

            // area = Math.max(area, width * height);
            if(area < width * height) {
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
        if(matrix.length == 0 || matrix[0].length == 0) return 0; 
        int[] arr = new int[matrix[0].length];

        int res = 0;

        for(int i = 0; i < matrix.length; i++) {
            // prepare arr for largest area histogram
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '0') {
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

        for(int i = 1; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = arr.length;
        }
        return res;
    }

    public static void slidingWindowMax(int[] arr, int k) {
        int[] rgi = rightGreaterIndex(arr);

        int j = 0;
        for(int i = 0; i <= arr.length - k; i++) {
            if(i > j) {
                j = i;
            }

            while(i + k > rgi[j]) {
                j = rgi[j];
            }
            System.out.println(arr[j]);
        }
    }
    
    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < arr.length; i++) {
            st.push(i);
        }

        // elimination
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

        // check its row
        for(int c = 0; c < arr[0].length; c++) {
            if(arr[candidate][c] == 1) {
                System.out.println("none");
                return;
            }
        }
        // check its colum
        for(int r = 0; r < arr.length; r++) {
            if(r != candidate && arr[r][candidate] == 0) {
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
        for(int i = 0; i < arr.length; i++) {
            pairs[i] = new Pair(arr[i][0], arr[i][1]);
        }
        Arrays.sort(pair);
        Stack<Pair> st = new Stack<>();
        st.push(pairs[0]);
        for(int i = 1; i < pairs.length; i++) {
            Pair p = pairs[i];
            if(p.st <= st.peek().end) {
                // end time may be update
                if(p.end > st.peek().end) {
                    st.peek().end = p.end;
                }
            } else {
                st.push(p);
            }
        }
        Stack<Pair> rst = new Stack<>(); // reverse stack
        while(st.size() > 0)
            rst.push(st.pop());
        while(rst.size() > 0) {
            Pair rem = rst.pop();
            System.out.println(rem.st + " " + rem.end);
        }
    }

    public static void smallestNumberFollowingPattern(String str) {
        int count = 1;
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            st.push(count);
            count++;
            if(strcharAt(i) == 'i') {
                while(st.size() > 0) {
                    System.out.print(st.pop());
                }
            }
        }
        st.push(count);
        while(st.size() > 0) {
            System.out.print(st.pop());
        }
    }

    public static void fun() {

        int[][] arr = {
            {5, 8},
            {1, 7}
        };
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