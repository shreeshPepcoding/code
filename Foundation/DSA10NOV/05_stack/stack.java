import java.util.*;

public class stack {

    // next greater in right 
    public static int[] ngr(int[] arr) {
        // step 1 - Array create and make a stack
        int n = arr.length;
        int[] nge = new int[n];
        Stack < Integer > st = new Stack < > ();

        st.push(0);
        // step - 2 make a loop and push / pop in stack to make ans
        for (int i = 1; i < n; i++) {
            if (arr[st.peek()] < arr[i]) {
                // remove from stack and mark in nge until top is smaller && stack have sufficient values
                while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                    nge[st.peek()] = arr[i];
                    st.pop();
                }
            }
            st.push(i);
        }
        // process the remaining stack and fill nge according to rq.
        while (st.size() > 0) {
            nge[st.peek()] = -1;
            st.pop();
        }
        return nge;
    }

    // next greater in left
    public static int[] ngl(int[] arr) {
        // step 1 - Array create and make a stack
        int n = arr.length;
        int[] nge = new int[n];
        Stack < Integer > st = new Stack < > ();

        st.push(n - 1);
        // step - 2 make a loop and push / pop in stack to make ans
        for (int i = n - 2; i >= 0; i--) {
            if (arr[st.peek()] < arr[i]) {
                // remove from stack and mark in nge until top is smaller && stack have sufficient values
                while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                    nge[st.peek()] = arr[i];
                    st.pop();
                }
            }
            st.push(i);
        }
        // process the remaining stack and fill nge according to rq.
        while (st.size() > 0) {
            nge[st.peek()] = -1;
            st.pop();
        }
        return nge;
    }

    // next smaller in right 
    public static int[] nsr(int[] arr) {
        // step 1 - Array create and make a stack
        int n = arr.length;
        int[] nge = new int[n];
        Stack < Integer > st = new Stack < > ();

        st.push(0);
        // step - 2 make a loop and push / pop in stack to make ans
        for (int i = 1; i < n; i++) {
            if (arr[st.peek()] > arr[i]) {
                // remove from stack and mark in nge until top is smaller && stack have sufficient values
                while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                    nge[st.peek()] = arr[i];
                    st.pop();
                }
            }
            st.push(i);
        }
        // process the remaining stack and fill nge according to rq.
        while (st.size() > 0) {
            nge[st.peek()] = -1;
            st.pop();
        }
        return nge;
    }

    // next smaller in left
    public static int[] nsl(int[] arr) {
        // step 1 - Array create and make a stack
        int n = arr.length;
        int[] nge = new int[n];
        Stack < Integer > st = new Stack < > ();

        st.push(n - 1);
        // step - 2 make a loop and push / pop in stack to make ans
        for (int i = n - 2; i >= 0; i--) {
            if (arr[st.peek()] > arr[i]) {
                // remove from stack and mark in nge until top is smaller && stack have sufficient values
                while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                    nge[st.peek()] = arr[i];
                    st.pop();
                }
            }
            st.push(i);
        }
        // process the remaining stack and fill nge according to rq.
        while (st.size() > 0) {
            nge[st.peek()] = -1;
            st.pop();
        }
        return nge;
    }


    public static int[] stockSpan(int[] arr) {
        int[] span = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);
        for(int i = arr.length - 2; i >= 0; i--) {
            if(arr[i] > arr[st.peek()]) {
                while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                    span[st.peek()] = st.peek() - i;
                    st.pop();
                }
            }
            st.push(i);
        }

        // manage remaining elements of stack
        while(st.size() > 0) {
            span[st.peek()] = st.peek() + 1;
            st.pop();
        }
        return span;
    }

    public static int largestAreaHisto(int[] arr) {
        int ans = 0;
        int n = arr.length;
        // prepare left smaller index
        int[] lsi = new int[n];
        Stack<Integer> st1 = new Stack<>();
        st1.push(n - 1);
        for(int i = n - 2; i >= 0; i--) {
            if(arr[i] < arr[st1.peek()]) {
                while(st1.size() > 0 && arr[i] < arr[st1.peek()]) {
                    lsi[st1.peek()] = i;
                    st1.pop();
                }
            }
            st1.push(i);
        }

        while(st1.size() > 0) {
            lsi[st1.peek()] = -1;
            st1.pop();
        }

        // prepare right smaller index
        int[] rsi = new int[n];
        Stack<Integer> st2 = new Stack<>();
        st2.push(0);
        for(int i = 1; i < n; i++) {
            if(arr[i] < arr[st2.peek()]) {
                while(st2.size() > 0 && arr[i] < arr[st2.peek()]) {
                    rsi[st2.peek()] = i;
                    st2.pop();
                }
            }
            st2.push(i);
        }

        while(st2.size() > 0) {
            rsi[st2.peek()] = n;
            st2.pop();
        }

        // prepare answer
        for(int i = 0; i < n; i++) {
            ans = Math.max(ans, (rsi[i] - lsi[i] - 1) * arr[i]);
        }
        return ans;
    }

    public static void kWindowMax(int[] arr, int k) {
        // prepare for next greater on right(index)
        int n = arr.length;
        int[] nge = new int[n];
        Stack<Integer> st = new Stack<>();

        st.push(0);
        for(int i = 1; i < n; i++) {
            while(st.size() > 0 && arr[i] > arr[st.peek()]) {
                nge[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }

        while(st.size() > 0) {
            nge[st.peek()] = n;
            st.pop();
        }

        // print answer
        int j = 0;
        for(int i = 0; i <= n - k; i++) {
            if(j < i)
                j = i;

            while(nge[j] < i + k) {
                j = nge[j];
            }
            // print answer
            System.out.println(arr[j]);
        }
    }

    // leetcode - 739 (Daily Temperature) - code is upto you

    // leetcode - 503
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        
        // next greater
        for(int i = 0; i < 2 * nums.length; i++) {
            int indx = i % nums.length;
            
            while(st.size() > 0 && nums[indx] > nums[st.peek()]) {
                res[st.peek()] = nums[indx];
                st.pop();
            }
            if(i == indx)
                st.push(indx);
        }
        
        while(st.size() > 0) {
            res[st.peek()] = -1;
            st.pop();
        }
        
        return res;
    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"
        Stack<Integer> st = new Stack<>();

        // fill stack with persons
        for(int i = 0; i < arr.length; i++) {
            st.push(i);
        }

        // make a loop with stack have more than 1 people
        while(st.size() >= 2) {
            int p1 = st.pop();
            int p2 = st.pop();

            if(arr[p1][p2] == 1) {
                // p1 knows p2
                // eliminate p1 and push p2 in stack
                st.push(p2); 
            } else {
                // p1 don't know p2
                // p2 is not celebrity
                // eliminate p2 and push p1
                st.push(p1);
            }
        }

        int p = st.pop(); // optimal candidate for celeb
        for(int i = 0; i < arr.length; i++) {
            if(i != p) {
                if(arr[i][p] == 0 || arr[p][i] == 1) {
                    // p is not celebrity
                    System.out.println("none");
                    return;
                }
            }
        }
        System.out.println(p);
    }

    public static class Pair implements Comparable<Pair>{
        int st;
        int en;

        public Pair(int st, int en) {
            this.st = st;
            this.en = en;
        }

        public int compareTo(Pair other) {
            if(this.st != other.st) {
                return this.st - other.st;
            } else {
                return this.en - other.en;
            }
        }
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        Pair[] pairs = new Pair[arr.length];
        // fill pairs from array
        for(int i = 0; i < arr.length; i++) {
            pairs[i] = new Pair(arr[i][0], arr[i][1]);
        }
        // sort Pair array
        Arrays.sort(pairs);
        // Make stack and apply algo
        Stack<Pair> st = new Stack<>();
        st.push(pairs[0]);

        for(int i = 1; i < pairs.length; i++) {
            if(st.peek().en >= pairs[i].st) {
                // merging will happern
                // end updatation??
                st.peek().en = Math.max(st.peek().en, pairs[i].en);
            } else {
                // make a new interval
                st.push(pairs[i]);
            }
        }

        // make another stack to get proper order of answer
        Stack<Pair> nst = new Stack<>(); // new stack
        while(st.size() > 0) {
            nst.push(st.pop());
        }

        while(nst.size() > 0) {
            // print answer
            Pair rem = nst.pop();
            System.out.println(rem.st + " " + rem.en);
        }
    }

    // smallest number following pattern
    public static void SmallestNumberFP(String str) {
        Stack<Integer> st = new Stack<>();
        int count = 1;
        
        st.push(count);
        count++;

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == 'd') {
                st.push(count);
                count++;
            } else {
                // print stack
                while(st.size() > 0) {
                    System.out.print(st.pop());
                }
                st.push(count);
                count++;
            }
        }
        while(st.size() > 0) {
            System.out.print(st.pop());
        }
        System.out.println();
    }
	
	// duplicate bracket
	public static boolean isDuplicate(String str) {
        Stack<Character> st = new Stack<>();
    
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == ')') {
                if(st.peek() == '(') {
                    return true;
                } else {
                    // pop untill '(' is not encounter
                    while(st.peek() != '(')
                        st.pop();
                
                    st.pop();
                }
            } else {
                st.push(ch);
            }
        }
        return false;
    }	

    // balanced bracket
    public static boolean isBalanced(String str) {
        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if(ch == ')') {
                if(st.size() == 0 || st.peek() != '(') {
                    return false;
                } else {
                    st.pop();
                }
                
            } else if(ch == '}') {
                if(st.size() == 0 || st.peek() != '{') {
                    return false;
                } else {
                    st.pop();
                }
            } else if(ch == ']') {
                if(st.size() == 0 || st.peek() != '[') {
                    return false;
                } else {
                    st.pop();
                }
            } else {
                // nothing to do
            }
        }
        return st.size() == 0;
    }
	
    public static void main(String[] args) {
        int[] arr = {2, 5, 9, 3, 1, 12, 6, 8, 7};
        int[] res = nsl(arr);

        System.out.println(Arrays.toString(res));
    }
}