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

        for(int i = 0; i < ngr.length; i++) {
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

        for(int i = 0; i < arr.length; i++) {
            int width = rsi[i] - lsi[i] - 1;
            int ht = arr[i];
            area = Math.max(area, width * ht);
        }
        return area;
    }

    // leetcode 85 Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] arr = new int[matrix[0].length];
        int area = 0;
        for(int i = 0; i < matrix.length; i++) {
            // prepare arr for largest area histogram
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '0') {
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

    public static void quest() {
        int[] arr = { 10, 6, 12, 5, 3, 2, 4, 8, 1 };

        System.out.println("arr : " + Arrays.toString(arr));
        System.out.println("ngr : " + Arrays.toString(ngr(arr)));
        System.out.println("ngl : " + Arrays.toString(ngl(arr)));
        System.out.println("nsr : " + Arrays.toString(nsr(arr)));
        System.out.println("nsl : " + Arrays.toString(nsl(arr)));

    }

    public static void main(String[] args) {
        quest();
    }
}
